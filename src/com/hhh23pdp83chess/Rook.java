package com.hhh23pdp83chess;

public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    public boolean move(int file, int rank){
        //allow possible Rook moves
        return false;
    }

    @Override
    public String toString(){
        return this.getColor() + "R";
    }
}
