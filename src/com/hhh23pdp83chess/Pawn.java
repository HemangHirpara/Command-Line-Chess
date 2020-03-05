package com.hhh23pdp83chess;

public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    public boolean move(int file, int rank){
        //allow possible Pawn moves
        return false;
    }

    @Override
    public String toString(){
        return this.getColor() + "P";
    }
}
