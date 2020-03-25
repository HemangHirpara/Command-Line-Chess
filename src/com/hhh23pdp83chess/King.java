package com.hhh23pdp83chess;

public class King extends ChessPiece {

    private boolean hasCastleMoved;
    private boolean isInCheck;

    /**
     * Constructor
     * @param color b or w
     * @param hasCastleMoved
     */
    public King(String color, boolean hasCastleMoved) { super(color); this.hasCastleMoved = hasCastleMoved; this.isInCheck = false;}

    /**
     *
     * @param board game board
     * @param start start loc
     * @param end end loc
     * @return true if the move can be made by a King
     */
    public boolean validateMove(Cell[][] board, Cell start, Cell end){
        //allow possible King moves
        //K can move 1 position in all direction iff valid position
        // (x,y) -> (x+1,y)OR(x,y+1)OR(x-1,y)OR(x,y-1)OR(x+1,y+1)OR(x-1,y-1)
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

        //Still have to check that castling does not result in king being in danger
        if(startPiece.getColor().equals("w")) {
            if ((start.getFile() == 0 && start.getRank() == 4) && (end.getFile() == 0 && end.getRank() == 6)) {
                //king is trying to castle with rook on h1
                if(hasCastleMoved) { return false; } //if white king has moved or castled before
                if(board[0][7].getPiece().getHasMoved()) {
                    return false; //rook on h1 has moved before
                }
                //check if path is clear to castle, if true, move king AND rook
                if(board[0][5].getPiece() == null && board[0][6].getPiece() == null){
                    //path is clear, move rook from h1 to f1
                    board[0][5].setPiece(board[0][7].getPiece());
                    board[0][7].setPiece(null);
                    hasCastleMoved = true;
                    return true;
                }
                return false;
            }
            if ((start.getFile() == 0 && start.getRank() == 4) && (end.getFile() == 0 && end.getRank() == 2)) {
                //king is trying to castle with rook on a1
                if(hasCastleMoved) { return false; } //if white king has moved or castled before
                if(board[0][0].getPiece().getHasMoved()) {
                    return false; //rook on h1 has moved before
                }
                //check if path is clear to castle, if true, move king AND rook
                if(board[0][3].getPiece() == null && board[0][2].getPiece() == null && board[0][1].getPiece() == null){
                    //path is clear, move rook from a1 to d1
                    board[0][3].setPiece(board[0][0].getPiece());
                    board[0][0].setPiece(null);
                    hasCastleMoved = true;
                    return true;
                }
                return false;
            }
        }

        if ((start.getFile() == 7 && start.getRank() == 4) && (end.getFile() == 7 && end.getRank() == 6)) {
            //king is trying to castle with rook on h8
            if(hasCastleMoved) { return false; } //if white king has moved or castled before
            if(board[7][7].getPiece().getHasMoved()) {
                return false; //rook on h1 has moved before
            }
            //check if path is clear to castle, if true, move king AND rook
            if(board[7][5].getPiece() == null && board[7][6].getPiece() == null){
                //path is clear, move rook from h1 to f1
                board[7][5].setPiece(board[7][7].getPiece());
                board[7][7].setPiece(null);
                hasCastleMoved = true;
                return true;
            }
            return false;
        }
        if ((start.getFile() == 7 && start.getRank() == 4) && (end.getFile() == 7 && end.getRank() == 2)) {
            //king is trying to castle with rook on a8
            if(hasCastleMoved) { return false; } //if black king has moved or castled before
            if(board[7][0].getPiece().getHasMoved()) {
                return false; //rook on a8 has moved before
            }
            //check if path is clear to castle, if true, move king AND rook
            if(board[7][3].getPiece() == null && board[7][2].getPiece() == null && board[7][1].getPiece() == null){
                //path is clear, move rook from a8 to d8
                board[7][3].setPiece(board[7][0].getPiece());
                board[7][0].setPiece(null);
                hasCastleMoved = true;
                return true;
            }
            return false;
        }

        int[][] offsets = { { 0, 1 }, { 1, 0 }, { 1, 1 },
                { 0, -1 }, { -1, 0 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };
        //have to make sure taking the given move does not endanger the king
        for(int[] offset : offsets)
        {
            int x = start.getFile()+offset[0];
            int y = start.getRank()+offset[1];
            if(x > 7 || y > 7 || x < 0 || y < 0)
                continue;
            if(x == end.getFile() && y == end.getRank()) {
                hasCastleMoved = true;
                return true;
            }
        }
        return false;
    }

    /**
     * @return t if moved, else false
     */
    public boolean getHasMoved() {
        return false;
    }

    /**
     * for castling
     * @param b true if moved
     */
    @Override
    public void setHasMoved(boolean b) {
        hasCastleMoved = b;
    }

    @Override
    public String toString(){
        return this.getColor() + "K";
    }

    /**
     *
     * @return true if King in check
     */
    public boolean getIsCheck(){
        return this.isInCheck;
    }

    /**
     * setter
     * @param b true if in check, else false
     */
    public void setCheck(boolean b)
    {
        this.isInCheck = b;
    }
}

