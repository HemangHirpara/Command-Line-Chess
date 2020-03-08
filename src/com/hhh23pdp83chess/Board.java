package com.hhh23pdp83chess;

//board is made up of Cells which can hold ChessPieces
public class Board {
    private Cell[][] board;

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
        board[0][0] = new Cell(0, 0, new Rook("w"));
        board[0][1] = new Cell(0, 1, new Knight("w"));
        board[0][2] = new Cell(0, 2, new Bishop("w"));
        board[0][3] = new Cell(0, 3, new Queen("w"));
        board[0][4] = new Cell(0, 4, new King("w"));
        board[0][5] = new Cell(0, 5, new Bishop("w"));
        board[0][6] = new Cell(0, 6, new Knight("w"));
        board[0][7] = new Cell(0, 7, new Rook("w"));
        board[1][0] = new Cell(1, 0, new Pawn("w"));
        board[1][1] = new Cell(1, 1, new Pawn("w"));
        board[1][2] = new Cell(1, 2, new Pawn("w"));
        board[1][3] = new Cell(1, 3, new Pawn("w"));
        board[1][4] = new Cell(1, 4, new Pawn("w"));
        board[1][5] = new Cell(1, 5, new Pawn("w"));
        board[1][6] = new Cell(1, 6, new Pawn("w"));
        board[1][7] = new Cell(1, 7, new Pawn("w"));
        //black pieces
        board[7][0] = new Cell(0, 0, new Rook("b"));
        board[7][1] = new Cell(0, 1, new Knight("b"));
        board[7][2] = new Cell(0, 2, new Bishop("b"));
        board[7][3] = new Cell(0, 3, new Queen("b"));
        board[7][4] = new Cell(0, 4, new King("b"));
        board[7][5] = new Cell(0, 5, new Bishop("b"));
        board[7][6] = new Cell(0, 6, new Knight("b"));
        board[7][7] = new Cell(0, 7, new Rook("b"));
        board[6][0] = new Cell(1, 0, new Pawn("b"));
        board[6][1] = new Cell(1, 1, new Pawn("b"));
        board[6][2] = new Cell(1, 2, new Pawn("b"));
        board[6][3] = new Cell(1, 3, new Pawn("b"));
        board[6][4] = new Cell(1, 4, new Pawn("b"));
        board[6][5] = new Cell(1, 5, new Pawn("b"));
        board[6][6] = new Cell(1, 6, new Pawn("b"));
        board[6][7] = new Cell(1, 7, new Pawn("b"));
        //Rest of board is null
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Cell(i, j, null);
            }
        }
        //board[3][3] = new Cell(3, 3, new Bishop("w"));
    }

    //make move allows player to move the piece on start cell to the end cell
    //true if p can move the piece
    //false if p cannot move the piece
    public boolean makeMove(Player p, Cell start, Cell end) {
        //MAKE SURE P IS ABLE TO MOVE PIECE ON START CELL I.E START.GETPIECE.GETCOLOR == 'B'
        if(start.getPiece().getColor().equals(p.getPlayerID())) {
            //USE THE SPECIFIC PIECES MOVE METHOD TO CHECK/MOVE THE PIECE SINCE ALL PIECES MOVE DIFFERENTLY
            //call move method of tempPiece = start.getPiece()
            // move tempPiece to specified file,rank
            // boolean result = move(end.getFile(),end.getRank(),tempPiece)
            //return false if move cannot be made (resulting location invalid, move not allowed by piece, outofbounds)

            ChessPiece tempPiece = start.getPiece();
            if(tempPiece.validateMove(board, start, end)){
                //set start cell to null since piece will no longer be there
                board[start.getFile()][start.getRank()].setPiece(null);
                //set end cell to temp piece, basically move the piece from start cell to end cell
                board[end.getFile()][end.getRank()].setPiece(tempPiece);
                //check win conditions
                return true;
            }
            return false;
        }
        return false;
    }

    public ChessPiece getPieceFromBoard(int file, int rank){
        return board[file][rank].getPiece();
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
