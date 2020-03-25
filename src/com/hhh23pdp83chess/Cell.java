package com.hhh23pdp83chess;
import java.util.Objects;

/**
 * Class to represent each space of a chess board
 * @author Hemang Hirpara hhh23
 * @author Poojan Patel pdp83
 */
public class Cell {
    private ChessPiece piece;
    private int file;
    private int rank;

    /**
     * Constructor for Cell
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
     * Get File from specific Cell
     * @return gets the file of cell
     */
    public int getFile() {
        return file;
    }

    /**
     * Get File from the specific Cell
     * @param file file to set for the Cell
     */
    public void setFile(int file) {
        this.file = file;
    }

    /**
     * Get Rank from specific Cell
     * @return gets the rank of cell
     */
    public int getRank() {
        return rank;
    }

    /**
     * Set Rank on specific Cell
     * @param rank rank to set for the Cell
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * Get Piece from specific Cell
     * @return gets the piece on the Cell
     */
    public ChessPiece getPiece() {
        return piece;
    }

    /**
     * Set Piece on specific Cell
     * @param piece piece to place on the Cell
     */
    public void setPiece(ChessPiece piece) { this.piece = piece; }

    /**
     * Equals method to compare two Cell objects
     * @param o object to compare Cell with
     * @return true if objects are equal
     */
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
