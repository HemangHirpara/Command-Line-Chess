package com.hhh23pdp83chess;

/**
 * Extends ChessPiece class to represent a Rook
 * @author Hemang Hirpara hhh23
 * @author Poojan Patel pdp83
 */
public class Rook extends ChessPiece {
    private boolean hasMoved;

    /**
     * Constructor for Rook
     * @param color b or w
     * @param hasMoved true if Rook has castled/moved before
     */
    public Rook(String color, boolean hasMoved) {super(color); this.hasMoved = hasMoved;}

    /**
     * Check if the specified move is validate for Bishop
     * @param board game board
     * @param start start loc
     * @param end end loc
     * @return true if the move can be made by a rook
     */
    public boolean validateMove(Cell[][] board, Cell start, Cell end) {
        ChessPiece startPiece = start.getPiece();
        ChessPiece endPiece = end.getPiece();
        if (start.getFile() > 7 || start.getRank() > 7 ||
                end.getFile() > 7 || end.getRank() > 7 ||
                start.getFile() < 0 || start.getRank() < 0 ||
                end.getFile() < 0 || end.getRank() < 0) {
            return false; //out of bounds move
        }
        //if start piece and end piece are the same color, return false
        if (endPiece != null && startPiece.getColor().equals("w") && endPiece.getColor().equals("w")) return false;
        else if (endPiece != null && startPiece.getColor().equals("b") && endPiece.getColor().equals("b")) return false;
        if (start.getFile() == end.getFile() && start.getRank() != end.getRank()) { //check horizontal movement
            int offset = ((end.getRank() - start.getRank()) < 0) ? -1 : 1;
            while ((start.getRank() + offset) != end.getRank()) {
                if (offset == 0) continue;
                if (board[(start.getFile())][(start.getRank() + offset)].getPiece() == null) { //start + counter is empty
                    if (board[(start.getFile())][(start.getRank() + offset)].equals(end)) { hasMoved = true; return true; }
                    offset = (offset < 0) ? offset - 1 : offset + 1;
                    continue; //if empty cell is not destination, continue to next cell
                }
                if (board[(start.getFile())][(start.getRank() + offset)].getPiece() != null) { //start + counter is NOT empty
                    if (board[(start.getFile())][(start.getRank() + offset)].equals(end)) { hasMoved = true; return true; }
                    return false;
                }
                offset = (offset < 0) ? offset - 1 : offset + 1;
            }
            hasMoved = true;
            return true;
        }
        else if (start.getFile() != end.getFile() && start.getRank() == end.getRank()) { //check vertical movement
            int offset = ((end.getFile() - start.getFile()) < 0) ? -1 : 1;
            while ((start.getFile() + offset) != end.getFile()) {
                if (offset == 0) continue;
                if (board[(start.getFile() + offset)][(start.getRank())].getPiece() == null) { //start + counter is empty
                    if (board[(start.getFile() + offset)][(start.getRank())].equals(end)) { hasMoved = true; return true; }
                    offset = (offset < 0) ? offset - 1 : offset + 1;
                    continue; //if empty cell is not destination, continue to next cell
                }
                if (board[(start.getFile() + offset)][(start.getRank())].getPiece() != null) { //start + counter is NOT empty
                    if (board[(start.getFile() + offset)][(start.getRank())].equals(end)) { hasMoved = true; return true; }
                    return false;
                }
                offset = (offset < 0) ? offset - 1 : offset + 1;
            }
            hasMoved = true;
            return true;
        }
        return false;
    }

    /**
     * Get moved flag to see if Rook has moved
     * @return true if Rook has moved
     */
    public boolean getHasMoved() {
        return hasMoved;
    }

    /**
     * Set moved flag if Rook moves
     * @param b true if Rook has moved
     */
    @Override
    public void setHasMoved(boolean b) {
        hasMoved = b;
    }

    @Override
    public String toString(){
        return this.getColor() + "R";
    }
}
