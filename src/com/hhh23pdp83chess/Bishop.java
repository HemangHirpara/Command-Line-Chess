package com.hhh23pdp83chess;

public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    public boolean move(Cell start, Cell end){
        //allow possible Bishop moves
        return false;
    }

    @Override
    public String toString(){
        return this.getColor() + "B";
    }
}
