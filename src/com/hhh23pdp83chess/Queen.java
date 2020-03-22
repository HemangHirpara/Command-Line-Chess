package com.hhh23pdp83chess;

public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    //fix method
    public boolean validateMove(Cell[][] board, Cell start, Cell end){
        //allow possible Queen moves
        //If Q is at position (x,y), she can move
        // (x+i,y) OR (x,y+i) OR (x-i,y) OR (x,y-i) OR (x+i,y+i) OR (x-i,y-i) OR (x-i,y+i) OR (x+i,y-i)
        // as long as coordinates result in valid position on board
        // there is no piece blocking the path
        ChessPiece bishopTemp = new Bishop(start.getPiece().getColor());
        ChessPiece rookTemp = new Rook(start.getPiece().getColor(), false);
        if(bishopTemp.validateMove(board, start ,end))
            return true;
        else if(rookTemp.validateMove(board, start ,end))
            return true;
        return false;
    }

    public boolean getHasMoved() {
        return false;
    }

    @Override
    public void setHasMoved(boolean b) {

    }

    @Override
    public String toString(){
        return this.getColor() + "Q";
    }
}
