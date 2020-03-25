/**
 * @author Hemang Hirpara hhh23
 * @author Poojan Patel pdp83
 */
package com.hhh23pdp83chess;

import java.util.Objects;

public class Cell {
    private ChessPiece piece;
    private int file;
    private int rank;

    //file: a-h -> 0-7 rank: 1-8 -> 0-7
    // when processing input from user, we can convert the rank into its appropriate index number
    // for example if user enters e2 e4, we can process it as move piece from cell(4,2) to cell(4,4)

    /**
     * constructor
     * @param file file of cell
     * @param rank rank of cell
     * @param piece piece sitting at the cell
     */
    public Cell(int file, int rank, ChessPiece piece){
        this.setPiece(piece);
        this.setFile(file);
        this.setRank(rank);
    }

    /**
     * getter
     * @return gets the file of cell
     */
    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    /**
     * getter
     * @return gets the rank of cell
     */
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    //if getPiece is null, cell does not have a piece on

    /**
     * getter
     * @return gets the piece of cell
     */
    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return file == cell.file &&
                rank == cell.rank &&
                Objects.equals(piece, cell.piece);
    }

    @Override
    public String toString() {
        return "(" + file + ", " + rank + ", " + piece + ")";
    }
}
