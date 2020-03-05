package com.hhh23pdp83chess;

public class Cell {
    private ChessPiece piece;
    private int file;
    private int rank;

    //file: a-h -> 0-7 rank: 1-8 -> 0-7
    // when processing input from user, we can convert the rank into its appropriate index number
    // for example if user enters e2 e4, we can process it as move piece from cell(4,2) to cell(4,4)
    public Cell(int file, int rank, ChessPiece piece){
        this.setPiece(piece);
        this.setFile(file);
        this.setRank(rank);
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    //if getPiece is null, that means the cell is blank
    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }
}
