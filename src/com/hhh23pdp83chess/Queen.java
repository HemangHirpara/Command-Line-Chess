package com.hhh23pdp83chess;

public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    public boolean move(int file, int rank){
        //allow possible Queen moves
        return false;
    }

    @Override
    public String toString(){
        return this.getColor() + "Q";
    }
}
