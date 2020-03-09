package com.hhh23pdp83chess;

public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    public boolean validateMove(Cell[][] board, Cell start, Cell end){
        //allow possible Bishop moves
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
        //check diagonal movement
        //check if start piece is moving to positive up diagonal, negative up diagonal, positive down diagonal, negative down diagonal
        //calculate offsets
        //checking diagonal a distace of 1 away does not work yet
        if(Math.abs(end.getFile()-start.getFile()) == Math.abs(end.getRank()-start.getRank()))
        {
            int fileDirection = end.getFile()-start.getFile();
            int rankDirection = end.getRank()-start.getRank();
            int offset = 1;
            //System.out.println("Counting till: " + fileDirection + " Looking for: " + end);
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
                        if(board[(start.getFile()+offset)][(start.getRank()+offset)].equals(end))
                            return true;
                        else return false;
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
                        if(board[(start.getFile()+offset)][(start.getRank()-offset)].equals(end))
                            return true;
                        else return false;
                    }
                    offset++;
                }
                return true;
            }
            if(fileDirection < 0 && rankDirection > 0) { // -i +i
                //System.out.println("Moving postive down diagonal");
                while ((start.getRank() + offset) != end.getRank()) {
                    if(board[(start.getFile()-offset)][(start.getRank()+offset)].getPiece() == null){
                        if(board[(start.getFile()-offset)][(start.getRank()+offset)].equals(end))
                            return true;
                        offset++;
                        continue;
                    }
                    if(board[(start.getFile()-offset)][(start.getRank()+offset)].getPiece() != null){
                        if(board[(start.getFile()-offset)][(start.getRank()+offset)].equals(end))
                            return true;
                        else return false;
                    }
                    offset++;
                }
                return true;
            }
            if(fileDirection < 0 && rankDirection < 0) { // -i -i
                System.out.println("Moving negative down diagonal");
                while ((start.getRank() - offset) != end.getRank()) {
                    System.out.println("start rank: " + start.getRank() + " offset: " + offset + " end rank: " + end.getRank());
                    if(board[(start.getFile()-offset)][(start.getRank()-offset)].getPiece() == null){
                        if(board[(start.getFile()-offset)][(start.getRank()-offset)].equals(end))
                            return true;
                        offset++;
                        continue;
                    }
                    if(board[(start.getFile()-offset)][(start.getRank()-offset)].getPiece() != null){
                        if(board[(start.getFile()-offset)][(start.getRank()-offset)].equals(end))
                            return true;
                        else return false;
                    }
                    offset++;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean getHasMoved() {
        return false;
    }

    @Override
    public String toString(){
        return this.getColor() + "B";
    }
}
