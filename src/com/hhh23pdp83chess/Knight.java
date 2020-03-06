package com.hhh23pdp83chess;

public class Knight extends ChessPiece {
    public Knight(String color) {
        super(color);
    }

    public boolean move(Cell start, Cell end){
        //allow possible Knight moves
        return false;
    }

    @Override
    public String toString(){
        return this.getColor() + "N";
    }
}
