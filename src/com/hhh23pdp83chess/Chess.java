package com.hhh23pdp83chess;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//set up game, chess runs from here
public class Chess {

    private static int rank1;
    private static int file1;
    private static int rank2;
    private static int file2;
    private static Board gameBoard;
    private static String[] tokens;
    private static boolean drawFlag1 = false;
    private static boolean drawFlag2 = false;
    private static boolean resignFlag = false;
    private static boolean resignFlag1 = false;
    private static boolean resignFlag2 = false;

    public static void main(String[] args){
        gameBoard = new Board();
        gameBoard.printBoard();
        System.out.println();
        Cell[][] gb = gameBoard.getBoard();
        Scanner sc;
        //1: white 2: black
        Player p1 = new Player("w");
        Player p2 = new Player("b");

        while(gameBoard.checkWin())
        {
            if(checkWin(p1)){
                System.out.println("Checkmate. Black wins");
                System.exit(0);
            }
            sc = new Scanner(System.in);
            System.out.print("White's Move: ");
            tokens = sc.nextLine().trim().split(" ");
            parseInput();
            while(!processMove(p1, gb))
            {
                if(checkWin(p1)){
                    System.out.println("Checkmate. Black wins");
                    System.exit(0);
                }
                System.out.println("Illegal move, try again");
                System.out.print("White's Move: ");
                tokens = sc.nextLine().trim().split(" ");
                parseInput();
            }
            if((drawFlag1 && drawFlag2) || resignFlag)
                break;
            if(checkWin(p2)){
                System.out.println("Checkmate. White wins");
                System.exit(0);
            }
            System.out.print("Black's Move: ");
            tokens = sc.nextLine().trim().split(" ");
            parseInput();

            while(!processMove(p2, gb))
            {
                if(checkWin(p2)){
                    System.out.println("Checkmate. White wins");
                    System.exit(0);
                }
                System.out.println("Illegal move, try again");
                System.out.print("Black's Move: ");
                tokens = sc.nextLine().trim().split(" ");
                parseInput();
            }
            if((drawFlag1 && drawFlag2) || resignFlag)
                break;


        }
        if(drawFlag1)
            System.out.println("Both Players Draw");
        else if(resignFlag1)
            System.out.println("Black wins");
        else if(resignFlag2)
            System.out.println("White wins");
    }

    private static boolean processMove(Player p, Cell[][] gb) {
        Cell start = new Cell(file1, rank1, gb[file1][rank1].getPiece());
        //System.out.println("File: " + file1 + " Rank: " + rank1);
        Cell end = new Cell(file2, rank2, gb[file2][rank2].getPiece());
        //System.out.println("File: " + file2 + " Rank: " + rank2);

        //if(drawFlag1) { return true; }
        if(drawFlag2) { return  true; }

        if(resignFlag)
        {
            if(p.getPlayerID().equals("w"))
                resignFlag1 = true;
            resignFlag2 = true;
            return true;
        }
        //make basic move on gameboard if possible
        if(gameBoard.makeMove(p, start, end)) {
            System.out.println();
            gameBoard.printBoard();
            System.out.println();
            return true;
        }
        else
            return false;
    }

    private static int parseFile(char cFile) {
        int file;
        switch(cFile) {
            case 'a':
                file = 0;
                break;
            case 'b':
                file = 1;
                break;
            case 'c':
                file = 2;
                break;
            case 'd':
                file = 3;
                break;
            case 'e':
                file = 4;
                break;
            case 'f':
                file = 5;
                break;
            case 'g':
                file = 6;
                break;
            case 'h':
                file = 7;
                break;
            default:
                file = -1;
        }
        return file;
    }

    private static void parseInput(){
        //take token[] and assign it to proper values
        if (tokens.length == 1)
        {
            if(tokens[0].equals("draw"))
                drawFlag2 = true;
            if(tokens[0].equals("resign"))
                resignFlag = true;
        }
        if(tokens.length == 2)
        {
            rank1 = parseFile(tokens[0].charAt(0));
            file1 = Integer.parseInt(tokens[0].charAt(1) + "")-1;
            rank2 = parseFile(tokens[1].charAt(0));
            file2 = Integer.parseInt(tokens[1].charAt(1) + "")-1;
        }
        if(tokens.length == 3)
        {
            rank1 = parseFile(tokens[0].charAt(0));
            file1 = Integer.parseInt(tokens[0].charAt(1) + "")-1;
            rank2 = parseFile(tokens[1].charAt(0));
            file2 = Integer.parseInt(tokens[1].charAt(1) + "")-1;
            if(tokens[2].equals("draw?"))
                drawFlag1=true;
            else if(tokens[2].equals("Q") || tokens[2].equals("R") || tokens[2].equals("B") || tokens[2].equals("N"))
                gameBoard.setPromotionPiece(tokens[2]);
        }
    }

    /*
    @Player p black or white
    determines if there's a checkmate
    by creating list of valid moves and simulating them
     */
    private static boolean checkWin(Player p){
        // find king and check if he's in check
        King king = null;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(gameBoard.getBoard()[i][j].getPiece() instanceof King && gameBoard.getBoard()[i][j].getPiece().getColor().equals(p.getPlayerID()))
                    king = (King)gameBoard.getBoard()[i][j].getPiece();
            }
        }
        // if king not in check, return false
        if(king != null && !king.getIsCheck())
            return false;

        String pColor = p.getPlayerID();
        // get list of all the pieces of the current player on the board
        List<Cell> piecesLeft = new ArrayList<>();
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++){
                if(gameBoard.getBoard()[i][j].getPiece() != null && gameBoard.getBoard()[i][j].getPiece().getColor().equals(pColor))
                    piecesLeft.add(gameBoard.getBoard()[i][j]);
            }
        }

        /*
        1. now go through all pieces and their moves and simulate moves
        2. if a valid move is found, quit and return false, and remove the flag from the king
         */
        boolean moveOutput;
        for(Cell c : piecesLeft)
        {
            for(int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    Cell s, e;
                    s = c;
                    e = gameBoard.getBoard()[i][j];
                    ChessPiece tempPiece = s.getPiece();
                    ChessPiece tempEnd = e.getPiece();
                    moveOutput = gameBoard.makeMove(p, s, e);
                    // make the move and undo it, but these two lines causing issues
                    // run with them and without
                    gameBoard.getBoard()[s.getFile()][s.getRank()].setPiece(tempPiece);
                    gameBoard.getBoard()[e.getFile()][e.getRank()].setPiece(tempEnd);
                    // if the moveOutput is true, there is a possible move, ret false else
                    if(moveOutput)
                        return false;
                }
            }
        }

        return true;
    }


}
