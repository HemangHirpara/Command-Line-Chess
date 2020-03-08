package com.hhh23pdp83chess;

public class Rook extends ChessPiece {
    public Rook(String color) {super(color);}

    public boolean validateMove(Cell[][] board, Cell start, Cell end) {
        //allow possible Rook moves
        ChessPiece startPiece = start.getPiece();
        ChessPiece endPiece = end.getPiece();
        //CANNOT ALLOW BACKWARDS MOVE //moves in column a do not work
        if (start.getFile() > 7 || start.getRank() > 7 ||
                end.getFile() > 7 || end.getRank() > 7 ||
                start.getFile() < 0 || start.getRank() < 0 ||
                end.getFile() < 0 || end.getRank() < 0) {
            return false; //out of bounds move
        }
        //if start piece and end piece are the same color, return false
        if (endPiece != null && startPiece.getColor().equals("w") && endPiece.getColor().equals("w")) {
            return false;
        } else if (endPiece != null && startPiece.getColor().equals("b") && endPiece.getColor().equals("b")) {
            return false;
        }
        int offset = ((end.getRank() - start.getRank()) < 0) ? 1 : -1;
        if (start.getFile() == end.getFile() && start.getRank() != end.getRank()) { //check horizontal movement
            while ((start.getRank() + offset) != end.getRank()) {
                if (offset == 0) continue;
                if (board[(start.getFile())][(start.getRank() + offset)].getPiece() == null) { //start + counter is empty
                    if (board[(start.getFile())][(start.getRank() + offset)].equals(end))
                        return true;
                    offset = (offset < 0) ? offset - 1 : offset + 1;
                    continue; //if empty cell is not destination, continue to next cell
                }
                if (board[(start.getFile())][(start.getRank() + offset)].getPiece() != null) { //start + counter is NOT empty
                    if (board[(start.getFile())][(start.getRank() + offset)].equals(end))
                        return true;
                    return false;
                }
                offset = (offset < 0) ? offset - 1 : offset + 1;
            }
            return true;
        }
        else if (start.getFile() != end.getFile() && start.getRank() == end.getRank()) { //check vertical movement
            while ((start.getFile() + offset) != end.getFile()) {
                if (offset == 0) continue;
                if (board[(start.getFile() + offset)][(start.getRank())].getPiece() == null) { //start + counter is empty
                    if (board[(start.getFile() + offset)][(start.getRank())].equals(end))
                        return true;
                    offset = (offset < 0) ? offset - 1 : offset + 1;
                    continue; //if empty cell is not destination, continue to next cell
                }
                if (board[(start.getFile() + offset)][(start.getRank())].getPiece() != null) { //start + counter is NOT empty
                    if (board[(start.getFile() + offset)][(start.getRank())].equals(end))
                        return true;
                    return false;
                }
                offset = (offset < 0) ? offset - 1 : offset + 1;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return this.getColor() + "R";
    }
}
