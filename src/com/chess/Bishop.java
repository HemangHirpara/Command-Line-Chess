package com.chess;

/**
 * Extends ChessPiece class to represent a Bishop
 * @author Hemang Hirpara hhh23
 * @author Poojan Patel pdp83
 */
public class Bishop extends ChessPiece {
    /**
     * Constructor for Bishop
     * @param color b or w
     */
    public Bishop(String color) {
        super(color);
    }

    /**
     * Check if the specified move is validate for Bishop
     * @param board game board
     * @param start start loc
     * @param end end loc
     * @return true if the move can be made by a Bishop
     */
    public boolean validateMove(Cell[][] board, Cell start, Cell end){
        ChessPiece startPiece = start.getPiece();
        ChessPiece endPiece = end.getPiece();
        if(start.getFile() > 7 || start.getRank() > 7 ||
                end.getFile() > 7 || end.getRank() > 7 ||
                start.getFile() < 0 || start.getRank() < 0 ||
                end.getFile() < 0 || end.getRank() < 0){
            return false; //out of bounds move
        }

        //if start piece and end piece are the same color, return false
        if (endPiece != null && startPiece.getColor().equals("w") && endPiece.getColor().equals("w")) {
            return false;
        } else if (endPiece != null && startPiece.getColor().equals("b") && endPiece.getColor().equals("b")) {
            return false;
        }
        if(Math.abs(end.getFile()-start.getFile()) == Math.abs(end.getRank()-start.getRank()))
        {
            int fileDirection = end.getFile()-start.getFile();
            int rankDirection = end.getRank()-start.getRank();
            int offset = 1;
            if(fileDirection > 0 && rankDirection > 0) { // +i +i
                //System.out.println("Moving positive up diagonal");
                while ((start.getFile() + offset) != end.getFile()) {
                    if(board[(start.getFile()+offset)][(start.getRank()+offset)].getPiece() == null){
                        if(board[(start.getFile()+offset)][(start.getRank()+offset)].equals(end))
                            return true;
                        offset++;
                        continue;
                    }
                    if(board[(start.getFile()+offset)][(start.getRank()+offset)].getPiece() != null){
                        return board[(start.getFile() + offset)][(start.getRank() + offset)].equals(end);
                    }
                    offset++;
                }
                return true;
            }
            if(fileDirection > 0 && rankDirection < 0) { // +i -i
                //System.out.println("Moving negative up diagonal");
                while ((start.getFile() + offset) != end.getFile()) {
                    if(board[(start.getFile()+offset)][(start.getRank()-offset)].getPiece() == null){
                        if(board[(start.getFile()+offset)][(start.getRank()-offset)].equals(end))
                            return true;
                        offset++;
                        continue;
                    }
                    if(board[(start.getFile()+offset)][(start.getRank()-offset)].getPiece() != null){
                        return board[(start.getFile() + offset)][(start.getRank() - offset)].equals(end);
                    }
                    offset++;
                }
                return true;
            }
            if(fileDirection < 0 && rankDirection > 0) { // -i +i
                //System.out.println("Moving positive down diagonal");
                while ((start.getRank() + offset) != end.getRank()) {
                    if(board[(start.getFile()-offset)][(start.getRank()+offset)].getPiece() == null){
                        if(board[(start.getFile()-offset)][(start.getRank()+offset)].equals(end))
                            return true;
                        offset++;
                        continue;
                    }
                    if(board[(start.getFile()-offset)][(start.getRank()+offset)].getPiece() != null){
                        return board[(start.getFile() - offset)][(start.getRank() + offset)].equals(end);
                    }
                    offset++;
                }
                return true;
            }
            if(fileDirection < 0 && rankDirection < 0) { // -i -i
                //System.out.println("Moving negative down diagonal");
                while ((start.getRank() - offset) != end.getRank()) {
                    if(board[(start.getFile()-offset)][(start.getRank()-offset)].getPiece() == null){
                        if(board[(start.getFile()-offset)][(start.getRank()-offset)].equals(end))
                            return true;
                        offset++;
                        continue;
                    }
                    if(board[(start.getFile()-offset)][(start.getRank()-offset)].getPiece() != null){
                        return board[(start.getFile() - offset)][(start.getRank() - offset)].equals(end);
                    }
                    offset++;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Get moved flag to see if Bishop has moved
     * @return false, Bishop move does not need to be tracked
     */
    public boolean getHasMoved() {
        return false;
    }

    @Override
    public void setHasMoved(boolean b) {

    }

    @Override
    public String toString(){
        return this.getColor() + "B";
    }
}
