package com.hhh23pdp83chess;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation and visualization of a Chess board
 * Each space of the board is a Cell type
 * @author Hemang Hirpara hhh23
 * @author Poojan Patel pdp83
 */
public class Board {
    private Cell[][] board;
    private String promotionPiece; // the piece a pawn promotes to

    /**
     * Constructor for Board
     * Sets size as 8x8
     */
    public Board(){
        this.board = new Cell[8][8];
        initializeBoard();
    }

    /**
     * Initializes the original location of all the chesspieces by populating the 2d array of Cells
     */
    private void initializeBoard(){
        //white pieces
        board[0][0] = new Cell(0, 0, new Rook("w", false));
        board[0][1] = new Cell(0, 1, new Knight("w"));
        board[0][2] = new Cell(0, 2, new Bishop("w"));
        board[0][3] = new Cell(0, 3, new Queen("w"));
        board[0][4] = new Cell(0, 4, new King("w", false));
        board[0][5] = new Cell(0, 5, new Bishop("w"));
        board[0][6] = new Cell(0, 6, new Knight("w"));
        board[0][7] = new Cell(0, 7, new Rook("w", false));
        board[1][0] = new Cell(1, 0, new Pawn("w", false));
        board[1][1] = new Cell(1, 1, new Pawn("w", false));
        board[1][2] = new Cell(1, 2, new Pawn("w", false));
        board[1][3] = new Cell(1, 3, new Pawn("w", false));
        board[1][4] = new Cell(1, 4, new Pawn("w", false));
        board[1][5] = new Cell(1, 5, new Pawn("w", false));
        board[1][6] = new Cell(1, 6, new Pawn("w", false));
        board[1][7] = new Cell(1, 7, new Pawn("w", false));
        //black pieces
        board[7][0] = new Cell(7, 0, new Rook("b", false));
        board[7][1] = new Cell(7, 1, new Knight("b"));
        board[7][2] = new Cell(7, 2, new Bishop("b"));
        board[7][3] = new Cell(7, 3, new Queen("b"));
        board[7][4] = new Cell(7, 4, new King("b", false));
        board[7][5] = new Cell(7, 5, new Bishop("b"));
        board[7][6] = new Cell(7, 6, new Knight("b"));
        board[7][7] = new Cell(7, 7, new Rook("b", false));
        board[6][0] = new Cell(6, 0, new Pawn("b", false));
        board[6][1] = new Cell(6, 1, new Pawn("b", false));
        board[6][2] = new Cell(6, 2, new Pawn("b", false));
        board[6][3] = new Cell(6, 3, new Pawn("b", false));
        board[6][4] = new Cell(6, 4, new Pawn("b", false));
        board[6][5] = new Cell(6, 5, new Pawn("b", false));
        board[6][6] = new Cell(6, 6, new Pawn("b", false));
        board[6][7] = new Cell(6, 7, new Pawn("b", false));
        //Rest of board is null
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Cell(i, j, null);
            }
        }
    }

    /**
     * Execute move on chess board and set appropriate flags
     * @param p player b or w
     * @param start start piece
     * @param end end piece
     * @return true if the move is valid and is successfully made, else false
     */
    public boolean makeMove(Player p, Cell start, Cell end) {
        if(start.getPiece() == null)
            return false;
        if(start.getPiece().getColor().equals(p.getPlayerID())) {
            ChessPiece tempPiece = start.getPiece();
            if(tempPiece.validateMove(board, start, end)){
                //set start cell to null since piece will no longer be there
                board[start.getFile()][start.getRank()].setPiece(null);
                //set end cell to temp piece, basically move the piece from start cell to end cell
                board[end.getFile()][end.getRank()].setPiece(tempPiece);
                //check if pawn is on opposite rank to promote
                if(board[end.getFile()][end.getRank()].getPiece() instanceof Pawn && (end.getFile() == 7 || end.getFile() == 0)) {
                    if (promotionPiece == null) {
                        board[end.getFile()][end.getRank()].setPiece(new Queen(start.getPiece().getColor()));
                    } else {
                        if (getPromotionPiece().equals("Q"))
                            board[end.getFile()][end.getRank()].setPiece(new Queen(start.getPiece().getColor()));
                        if (getPromotionPiece().equals("N"))
                            board[end.getFile()][end.getRank()].setPiece(new Knight(start.getPiece().getColor()));
                        if (getPromotionPiece().equals("R"))
                            board[end.getFile()][end.getRank()].setPiece(new Rook(start.getPiece().getColor(), true));
                        if (getPromotionPiece().equals("B"))
                            board[end.getFile()][end.getRank()].setPiece(new Bishop(start.getPiece().getColor()));
                    }
                }

                King king = findKing(p);

                if(isCheck(p, start, end)) { king.setCheck(false); }
                else {
                    king.setCheck(true);
                    return false;
                }
                resetHasMoved(p, "w", "b");
                resetHasMoved(p, "b", "w");
                //check if this current move made, puts the other player in check
                Player p2 = new Player((p.getPlayerID().equals("b")) ? "w" : "b");
                List<Cell> temp = new ArrayList<>();
                King king2 = findKing(p2);

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (board[i][j].getPiece() != null && board[i][j].getPiece().getColor().equals(p2.getPlayerID()))
                            temp.add(board[i][j]);
                    }
                }

                if(isCheck(p2, temp.get(0), temp.get(1))) { king2.setCheck(false); }
                else { king2.setCheck(true); }

                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Find the King on the board
     * @param p player's king to find
     * @return King object found
     */
    private King findKing(Player p) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j].getPiece() instanceof King && board[i][j].getPiece().getColor().equals(p.getPlayerID()))
                    return (King)board[i][j].getPiece();
            }
        }
        return null;
    }

    /**
     * Reset move flags for en passant purposes
     * @param p player
     * @param b white or black
     * @param w white or black
     */
    private void resetHasMoved(Player p, String b, String w) {
        if(p.getPlayerID().equals(b)) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if(board[i][j].getPiece() instanceof Pawn && board[i][j].getPiece().getColor().equals(w)) {
                        board[i][j].getPiece().setHasMoved(false);
                    }
                }
            }
        }
    }

    /**
     * Checks if the current player is in check
     * @param p player who just played
     * @param start start piece of the move
     * @param end end location
     * @return true, if player in check
     */
    private boolean isCheck(Player p, Cell start, Cell end) {
        ChessPiece tempPiece = start.getPiece();
        ChessPiece tempEnd = end.getPiece();
        //check if doing so does not capture king
        List<Cell> alive = new ArrayList<>();
        // create a list of alive pieces
        Cell e = board[0][0];
        String opp = "b";
        if(p.getPlayerID().equals("b"))
            opp = "w";
        for (int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(board[i][j].getPiece() != null && board[i][j].getPiece().getColor().equals(opp))
                    alive.add(board[i][j]);
                if(board[i][j].getPiece() instanceof King && board[i][j].getPiece().getColor().equals(p.getPlayerID()))
                    e = board[i][j];
            }
        }

        // check if moving will make the king in check, if so return false;
        for(Cell piece : alive) {
            if(piece.getPiece().validateMove(board, piece, e)) {
                //set start cell to null since piece will no longer be there
                board[start.getFile()][start.getRank()].setPiece(tempPiece);
                //set end cell to temp piece, basically move the piece from start cell to end cell
                board[end.getFile()][end.getRank()].setPiece(tempEnd);
                return  false;
            }
        }
        return true;
    }

    /**
     * Get promotion piece specified by input
     * @return gets the promotion piece
     */
    public String getPromotionPiece() {
        return promotionPiece;
    }

    /**
     * Set promotion piece specified by input
     * @param promotionPiece promotion piece
     */
    public void setPromotionPiece(String promotionPiece) {
        this.promotionPiece = promotionPiece;
    }

    /**
     * Get chess board
     * @return returns the 2d array board
     */
    public Cell[][] getBoard() {
        return board;
    }

    /**
     * Prints the chess gameboard
     */
    public void printBoard(){
        for(int i = 7; i >= 0; i--) {
            for(int j = 0; j < 8; j++) {
                if ( (i % 2) == (j % 2) && board[i][j].getPiece() == null)
                    System.out.print("## ");
                else if((i % 2) != (j % 2) && board[i][j].getPiece() == null)
                    System.out.print("   ");
                else
                    System.out.print(board[i][j].getPiece()+" ");
            }
            System.out.print(i+1);
            System.out.println();
        }
        System.out.print(" a  b  c  d  e  f  g  h\n");
    }

}
