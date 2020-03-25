/**
 * @author Hemang Hirpara hhh23
 * @author Poojan Patel pdp83
 */package com.hhh23pdp83chess;

public class Player {
    // b or w
    private String playerID;

    /**
     * constructor
     * @param playerID b or w
     */
    public Player(String playerID) {
        this.playerID = playerID;
    }

    /**
     * getter
     * @return gets player color: b or w
     */
    public String getPlayerID() {
        return playerID;
    }
}
