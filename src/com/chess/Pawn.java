package com.chess;

/**
 * Extends ChessPiece class to represent a Pawn
 * @author Hemang Hirpara hhh23
 * @author Poojan Patel pdp83
 */
public class Pawn extends ChessPiece {
    private boolean hasMoved2Spots;

    /**
     * Constructor for Pawn
     * @param color b or w
     * @param hasMoved2Spots true if Pawn has moved before
     */
    public Pawn(String color, boolean hasMoved2Spots) {super(color); this.hasMoved2Spots = hasMoved2Spots;}

    /**
     * Check if the specified move is validate for Pawn
     * @param board game board
     * @param start start loc
     * @param end end loc
     * @return true if the move can be made by a pawn
     */
    public boolean validateMove(Cell[][] board, Cell start, Cell end){
        ChessPiece startPiece = start.getPiece();
        ChessPiece endPiece = end.getPiece();
        //CANNOT ALLOW BACKWARDS MOVE
        if(start.getFile() > 7 || start.getRank() > 7 ||
                end.getFile() > 7 || end.getRank() > 7 ||
                start.getFile() < 0 || start.getRank() < 0 ||
                end.getFile() < 0 || end.getRank() < 0){
            return false; //out of bounds move
        }
        //white enpassant check, if you are moving white piece diagonally left or right into an empty spot, you are trying to en passant
        if(endPiece == null && startPiece.getColor().equals("w") && (((start.getRank()+1) == end.getRank() && start.getFile()+1 == end.getFile()) || ((start.getRank()-1) == end.getRank() && start.getFile()+1 == end.getFile()))) {
            //if pawn on left and right of white piece is a black pawn AND then black piece has moved 2 spots
            if(start.getRank() > 0 && board[start.getFile()][start.getRank()-1].getPiece() instanceof Pawn && board[start.getFile()][start.getRank()-1].getPiece().getColor().equals("b")) {
                if(board[start.getFile()][start.getRank() - 1].getPiece().getHasMoved()) {
                    board[start.getFile()][start.getRank()-1].setPiece(null);
                    return true;
                }
                return false;
            }
            if(start.getRank() < 7 && board[start.getFile()][start.getRank()+1].getPiece() instanceof Pawn && board[start.getFile()][start.getRank()+1].getPiece().getColor().equals("b")) {
                if(board[start.getFile()][start.getRank() + 1].getPiece().getHasMoved()) {
                    board[start.getFile()][start.getRank()+1].setPiece(null);
                    return true;
                }
                return false;
            }
            return false;
        }
        //black enpassant check
        if(endPiece == null && startPiece.getColor().equals("b") && (((start.getRank()-1) == end.getRank() && start.getFile()-1 == end.getFile()) || ((start.getRank()+1) == end.getRank() && start.getFile()-1 == end.getFile()))) {
            if(start.getRank() > 0 && board[start.getFile()][start.getRank()-1].getPiece() instanceof Pawn && board[start.getFile()][start.getRank()-1].getPiece().getColor().equals("w")) {
                if(board[start.getFile()][start.getRank() - 1].getPiece().getHasMoved()) {
                    board[start.getFile()][start.getRank()-1].setPiece(null);
                    return true;
                }
                return false;
            }
            if(start.getRank() < 7 && board[start.getFile()][start.getRank()+1].getPiece() instanceof Pawn && board[start.getFile()][start.getRank()+1].getPiece().getColor().equals("w")) {
                if(board[start.getFile()][start.getRank() + 1].getPiece().getHasMoved()) {
                    board[start.getFile()][start.getRank()+1].setPiece(null);
                    return true;
                }
                return false;
            }
            return false;
        }

        //regular white pawn move check
        if(startPiece.getColor().equals("w")) {
            if(start.getFile() == 1) {//if white pawn is on initial rank, it can move 2 spaces
                //allow diagonal moves if end piece is black
                if(endPiece != null && endPiece.getColor().equals("b")) {
                    return ((start.getRank() + 1) == end.getRank() && start.getFile() + 1 == end.getFile()) ||
                            (start.getRank() - 1) == end.getRank() && start.getFile() + 1 == end.getFile();
                }
                else if(endPiece != null && endPiece.getColor().equals("w")) return false;

                //allow 1 or 2 position move from initial rank
                if(end.getRank()-start.getRank() == 0 && ((end.getFile() - start.getFile()) == 2 || (end.getFile() - start.getFile()) == 1)) {
                    if((end.getFile() - start.getFile()) == 2) {
                        //moving two spaces, check if piece is in the way
                        if(board[start.getFile()+1][start.getRank()].getPiece() != null) return false;
                        else {
                            hasMoved2Spots = true;
                            return true;
                        }
                    }
                    return true;
                }
                return false;
            }
            else {
                //allow diagonal moves if end piece is black
                if(endPiece != null && endPiece.getColor().equals("b")) {
                    return ((start.getRank() + 1) == end.getRank() && start.getFile() + 1 == end.getFile()) ||
                            (start.getRank() - 1) == end.getRank() && start.getFile() + 1 == end.getFile();
                }
                else if(endPiece != null && endPiece.getColor().equals("w")) return false;

                //allow 1 position move from another rank
                return end.getRank() - start.getRank() == 0 && (end.getFile() - start.getFile()) == 1;
            }
        }
        //regular black pawn check
        else
        {
            if (start.getFile() == 6) //if black pawn is on initial rank, it can move 2 spaces
            {
                //allow diagonal moves if end piece is white
                if (endPiece != null && endPiece.getColor().equals("w")) {
                    return (start.getRank() - 1) == end.getRank() && start.getFile() - 1 == end.getFile() ||
                            (start.getRank() + 1) == end.getRank() && start.getFile() - 1 == end.getFile();
                }
                else if(endPiece != null && endPiece.getColor().equals("b")) return false;

                //allow 1 or 2 position move from initial rank
                if ((end.getRank() - start.getRank()) == 0 && ((start.getFile() - end.getFile()) == 2 || (start.getFile() - end.getFile()) == 1)){
                    if((start.getFile() - end.getFile()) == 2) {
                        //moving two spaces, check if piece is in the way
                        if(board[start.getFile()-1][start.getRank()].getPiece() != null) return false;
                        else {
                            hasMoved2Spots = true;
                            return true;
                        }
                    }
                    return true;
                }
                return false;
            }
            else {
                //allow diagonal moves if end piece is white
                if (endPiece != null && endPiece.getColor().equals("w")) {
                    return (start.getRank() - 1) == end.getRank() && start.getFile() - 1 == end.getFile() ||
                            (start.getRank() + 1) == end.getRank() && start.getFile() - 1 == end.getFile();
                }
                else if(endPiece != null && endPiece.getColor().equals("b")) return false;

                //allow 1 position move from another rank
                return (end.getRank() - start.getRank()) == 0 && (start.getFile() - end.getFile()) == 1;
            }
        }
    }

    public boolean getHasMoved() {
        return hasMoved2Spots;
    }

    @Override
    public void setHasMoved(boolean b) {
        hasMoved2Spots = b;
    }

    @Override
    public String toString(){
        return this.getColor() + "P";
    }
}