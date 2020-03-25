/**
 * @author Hemang Hirpara hhh23
 * @author Poojan Patel pdp83
 */
package com.hhh23pdp83chess;
public class Pawn extends ChessPiece {
    private boolean hasMoved2Spots;
    public Pawn(String color, boolean hasMoved2Spots) {super(color); this.hasMoved2Spots = hasMoved2Spots;}

    /**
     *
     * @param board game board
     * @param start start loc
     * @param end end loc
     * @return true if the move can be made by a pawn
     */
    public boolean validateMove(Cell[][] board, Cell start, Cell end){
        //allow possible Pawn moves
        //pawn can move 2 on first move or 1 on all other moves
        //pawn can capture one move forward diagonally
        //if pawn move is its first move allow 2 move ahead else 1
        //return false if move cannot be made if (resulting location invalid, move not allowed by piece, outofbounds)
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
                    if(((start.getRank()+1) == end.getRank() && start.getFile()+1 == end.getFile()) ||
                            (start.getRank()-1) == end.getRank() && start.getFile()+1 == end.getFile()) {
                        return true;
                    }
                    return false;
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
                    if(((start.getRank()+1) == end.getRank() && start.getFile()+1 == end.getFile()) ||
                            (start.getRank()-1) == end.getRank() && start.getFile()+1 == end.getFile()) {
                        return true;
                    }
                    return false;
                }
                else if(endPiece != null && endPiece.getColor().equals("w")) return false;

                //allow 1 position move from another rank
                if(end.getRank()-start.getRank() == 0 && (end.getFile() - start.getFile()) == 1) return true;
            }
            return false;
        }
        //regular black pawn check
        else
        {
            if (start.getFile() == 6) //if black pawn is on initial rank, it can move 2 spaces
            {
                //allow diagonal moves if end piece is white
                if (endPiece != null && endPiece.getColor().equals("w")) {
                    if ((start.getRank() - 1) == end.getRank() && start.getFile() - 1 == end.getFile()||
                            (start.getRank()+1) == end.getRank() && start.getFile()-1 == end.getFile()) {
                        return true;
                    }
                    return false;
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
                    if ((start.getRank() - 1) == end.getRank() && start.getFile() - 1 == end.getFile()||
                            (start.getRank()+1) == end.getRank() && start.getFile()-1 == end.getFile()) {
                        return true;
                    }
                    return false;
                }
                else if(endPiece != null && endPiece.getColor().equals("b")) return false;

                //allow 1 position move from another rank
                if ((end.getRank()-start.getRank()) == 0 && (start.getFile() - end.getFile()) == 1) return true;
            }
            return false;
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