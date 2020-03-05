package com.hhh23pdp83chess;

public abstract class ChessPiece {
    private String color;

    //set the color of the piece
    public ChessPiece(String color){
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public abstract boolean move(int file, int rank);
}
