package com.chess;

/**
 * Extends ChessPiece class to represent a Queen
 * @author Hemang Hirpara hhh23
 * @author Poojan Patel pdp83
 */
public class Queen extends ChessPiece {
    /**
     * Constructor for Queen
     * @param color b or w
     */
    public Queen(String color) {
        super(color);
    }

    /**
     * Check if the specified move is validate for Queen
     * @param board game board
     * @param start start loc
     * @param end end loc
     * @return true if the move can be made by a Queen
     */
    public boolean validateMove(Cell[][] board, Cell start, Cell end){
        //A queens movements is essentially the combination of bishop and rook movements
        ChessPiece bishopTemp = new Bishop(start.getPiece().getColor());
        ChessPiece rookTemp = new Rook(start.getPiece().getColor(), false);
        if(bishopTemp.validateMove(board, start ,end))
            return true;
        else return rookTemp.validateMove(board, start, end);
    }

    /**
     * Get move flagged to see if Queen has moved
     * @return false, queen move does not need to be tracked
     */
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
