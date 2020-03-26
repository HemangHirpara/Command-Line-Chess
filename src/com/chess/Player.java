package com.chess;

/**
 * Represent a chess player
 * @author Hemang Hirpara hhh23
 * @author Poojan Patel pdp83
 */
public class Player {
    private String playerID;

    /**
     * Constructor for Player
     * @param playerID b or w, defines which side
     */
    public Player(String playerID) {
        this.playerID = playerID;
    }

    /**
     * Get player's ID
     * @return gets player color: b or w
     */
    public String getPlayerID() {
        return playerID;
    }
}
