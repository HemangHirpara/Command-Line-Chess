package com.hhh23pdp83chess;

//board is made up of Cells which can hold ChessPieces
public class Board {
    private Cell[][] board;

    //initialize board
    public Board(Cell[][] board){
        this.board = board;
        initializeBoard();
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
    }

    //how to print the board in terminal
    public void printBoard(){
        for(int i = 7; i > 0; i--) {
            for(int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print(i+1);
            System.out.println();
        }
        System.out.print(" a  b  c  d  e  f  g  h\n");
    }
}
