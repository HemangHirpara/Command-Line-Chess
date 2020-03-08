package com.hhh23pdp83chess;

public class King extends ChessPiece {

    public King(String color) { super(color); }
    public boolean validateMove(Cell[][] board, Cell start, Cell end){
        //allow possible King moves
        //K can move 1 position in all direction iff valid position
        // (x,y) -> (x+1,y)OR(x,y+1)OR(x-1,y)OR(x,y-1)OR(x+1,y+1)OR(x-1,y-1)
        return false;
    }

    @Override
    public String toString(){
        return this.getColor() + "K";
    }
}

