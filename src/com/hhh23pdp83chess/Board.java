package com.hhh23pdp83chess;

import java.util.ArrayList;
import java.util.List;

//board is made up of Cells which can hold ChessPieces
public class Board {
    private Cell[][] board;
    private String promotionPiece;
    //initialize board
    public Board(){
        this.board = new Cell[8][8];
        initializeBoard();
    }

    public Cell[][] getBoard() {
        return board;
    }

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
        /*board[3][3] = new Cell(3, 3, new Bishop("w"));
        board[7][1] = new Cell(0, 1, null);
        board[7][2] = new Cell(0, 2, null);
        board[7][3] = new Cell(0, 3, null);
*/
        //board[4][4] = new Cell(4, 4, new Pawn("w", true));
        //board[4][6] = new Cell(4, 6, new Pawn("w", true));
    }

    //make move allows player to move the piece on start cell to the end cell
    //true if p can move the piece
    //false if p cannot move the piece
    public boolean makeMove(Player p, Cell start, Cell end) {
        if(start.getPiece() == null)
            return false;
        //MAKE SURE P IS ABLE TO MOVE PIECE ON START CELL I.E START.GETPIECE.GETCOLOR == 'B'
        if(start.getPiece().getColor().equals(p.getPlayerID())) {
            //USE THE SPECIFIC PIECES MOVE METHOD TO CHECK/MOVE THE PIECE SINCE ALL PIECES MOVE DIFFERENTLY
            //call move method of tempPiece = start.getPiece()
            // move tempPiece to specified file,rank
            // boolean result = move(end.getFile(),end.getRank(),tempPiece)
            //return false if move cannot be made (resulting location invalid, move not allowed by piece, outofbounds)

            ChessPiece tempPiece = start.getPiece();
            boolean promSpecified = false;
            boolean isProm = false;
            if(tempPiece.validateMove(board, start, end)){
                //set start cell to null since piece will no longer be there
                board[start.getFile()][start.getRank()].setPiece(null);
                //set end cell to temp piece, basically move the piece from start cell to end cell
                board[end.getFile()][end.getRank()].setPiece(tempPiece);
                //check if pawn is on opposite rank to promote
                if(board[end.getFile()][end.getRank()].getPiece() instanceof Pawn && (end.getFile() == 7 || end.getFile() == 0)) {
                    isProm = true;
                    System.out.println("Change pawn into promotion piece");
                    if (promotionPiece == null) {
                        //System.out.println("Promotion not specified, promote to queen");
                        board[end.getFile()][end.getRank()].setPiece(new Queen(start.getPiece().getColor()));
                    } else {
                        //System.out.println("Promotion piece is: " + getPromotionPiece());
                        promSpecified = true;
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

                King king = null;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if(board[i][j].getPiece() instanceof King && board[i][j].getPiece().getColor().equals(p.getPlayerID()))
                            king = (King)board[i][j].getPiece();
                    }
                }

                if(isCheck(p, start, end)) {
                    king.setCheck(false);
                    System.out.println("not in check");
                }
                else {
                    king.setCheck(true);
                    System.out.println("in check");
                    return false;
                }

                if(isProm) {
                    if(promSpecified)
                        System.out.println("Promotion piece is: " + getPromotionPiece());
                    else
                        System.out.println("Promotion not specified, promote to queen");
                }
                resetHasMoved(p, "w", "b");
                resetHasMoved(p, "b", "w");
                //check if this current move made, puts the other player in check
                Player p2 = new Player((p.getPlayerID().equals("b")) ? "w" : "b");
                List<Cell> temp = new ArrayList<>();
                King king2 = null;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if(board[i][j].getPiece() instanceof King && board[i][j].getPiece().getColor().equals(p2.getPlayerID()))
                            king2 = (King)board[i][j].getPiece();
                    }
                }
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (board[i][j].getPiece() != null && board[i][j].getPiece().getColor().equals(p2.getPlayerID()))
                            temp.add(board[i][j]);
                    }
                }
                if(isCheck(p2, temp.get(0), temp.get(1)))
                {
                    king2.setCheck(false);
                    System.out.println(p2.getPlayerID()+ " is not in check");
                }
                else
                {
                    king2.setCheck(true);
                    System.out.println(p2.getPlayerID()+ " is in check");
                }

                return true;
            }
            return false;
        }
        return false;
    }

    private void resetHasMoved(Player p, String b, String w) {
        if(p.getPlayerID().equals(b))
        {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if(board[i][j].getPiece() instanceof Pawn && board[i][j].getPiece().getColor().equals(w))
                    {
                        board[i][j].getPiece().setHasMoved(false);
                    }
                }
            }
        }
    }

    private boolean isCheck(Player p, Cell start, Cell end)
    {
        ChessPiece tempPiece = start.getPiece();
        ChessPiece tempEnd = end.getPiece();
        //check if doing so does not kill king
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

    public String getPromotionPiece() {
        return promotionPiece;
    }

    public void setPromotionPiece(String promotionPiece) {
        this.promotionPiece = promotionPiece;
    }


    public boolean checkWin(){

        return true;
    }

    //how to print the board in terminal
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
