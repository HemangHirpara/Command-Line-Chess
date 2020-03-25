package com.hhh23pdp83chess;

public class Knight extends ChessPiece {
    public Knight(String color) {
        super(color);
    }

    /**
     *
     * @param board game board
     * @param start start loc
     * @param end end loc
     * @return true if the move can be made by a Knight
     */
    public boolean validateMove(Cell[][] board, Cell start, Cell end){
        //allow possible Knight moves
        ChessPiece startPiece = start.getPiece();
        ChessPiece endPiece = end.getPiece();
        if(start.getFile() > 7 || start.getRank() > 7 ||
                end.getFile() > 7 || end.getRank() > 7 ||
                start.getFile() < 0 || start.getRank() < 0 ||
                end.getFile() < 0 || end.getRank() < 0){
            return false; //out of bounds move
        }
        //all valid positions the knight can move to
        if(endPiece != null && startPiece.getColor().equals("w") && endPiece.getColor().equals("w"))
            return false;
        else if(endPiece != null && startPiece.getColor().equals("b") && endPiece.getColor().equals("b"))
            return false;
        int[][] offsets = { { 1, 2 }, { -1, 2 }, { 1, -2 },
                { -1, -2 }, { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 } };
        for(int[] offset : offsets)
        {
            int x = start.getFile()+offset[0];
            int y = start.getRank()+offset[1];
            if(x > 7 || y > 7 || x < 0 || y < 0)
                continue;
            if(board[(start.getFile()+offset[0])][(start.getRank()+offset[1])].equals(end))
                return true;
        }
        return false;
    }

    /**
     * getter
     * @return true if the piece has moved
     */
    public boolean getHasMoved() {
        return false;
    }

    @Override
    public void setHasMoved(boolean b) {

    }

    @Override
    public String toString(){
        return this.getColor() + "N";
    }
}
