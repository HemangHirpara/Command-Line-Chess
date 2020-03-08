package com.hhh23pdp83chess;
import java.util.Scanner;
//set up game, chess runs from here
public class Chess {

    private static int rank;
    private static int file;
    private static String input;
    private static Board gameBoard;

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
            sc = new Scanner(System.in);
            System.out.print("White's Move: ");
            input = sc.nextLine().trim();

            while(!processMove(p1, gb))
            {
                System.out.println("Illegal move, try again");
                System.out.print("White's Move: ");
                input = sc.nextLine().trim();
            }

            System.out.print("Black's Move: ");
            input = sc.nextLine().trim();

            while(!processMove(p2, gb))
            {
                System.out.println("Illegal move, try again");
                System.out.print("Black's Move: ");
                input = sc.nextLine().trim();
            }
        }
    }

    private static boolean processMove(Player p, Cell[][] gb) {
        //process input, create objects
        rank = parseInput(input.charAt(0));
        file = Integer.parseInt(input.charAt(1)+ "")-1;
        Cell start = new Cell(file, rank, gb[file][rank].getPiece());
        System.out.println("File: " + file + " Rank: " + rank);
        rank = parseInput(input.charAt(3));
        file = Integer.parseInt(input.charAt(4)+ "")-1;
        Cell end = new Cell(file, rank, gb[file][rank].getPiece());
        System.out.println("File: " + file + " Rank: " + rank);

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

    private static int parseInput(char cFile) {
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
}
