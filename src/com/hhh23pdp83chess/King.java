package com.hhh23pdp83chess;

public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    public boolean move(int file, int rank){
        //allow possible King moves
        return false;
    }

    @Override
    public String toString(){
        return this.getColor() + "K";
    }
}

