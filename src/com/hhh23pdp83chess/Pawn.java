package com.hhh23pdp83chess;

public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    public boolean move(Cell start, Cell end){
        //allow possible Pawn moves
        //pawn can move 2 on first move or 1 on all other moves
        //pawn can capture one move forward diagonally
        //if pawn move is its first move allow 2 move ahead else 1
        if(false)
        {
            //allow 2 position move ahead
        }
        else
        {
            //allow 1 position move ahead
        }
        return false;
    }

    @Override
    public String toString(){
        return this.getColor() + "P";
    }
}
