package com.hhh23pdp83chess;

//set up game, chess runs from here
public class Chess {

    private static Cell[][] board;
    public static void main(String[] args){
        Board gameBoard = new Board(board);
        gameBoard.printBoard();
    }
}
