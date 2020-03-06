package com.hhh23pdp83chess;
import java.util.Scanner;
//set up game, chess runs from here
public class Chess {

    private static int rank;
    private static int file;
    private static Cell start;
    private static Cell end;
    private static String input;
    private static Board gameBoard;

    public static void main(String[] args){
        gameBoard = new Board();
        gameBoard.printBoard();
        System.out.println();
        Cell[][] gb = gameBoard.getBoard();

        //1: white 2: black
        Player p1 = new Player("w");
        Player p2 = new Player("b");

        while(true)
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("White's Move: ");
            input = sc.nextLine();

            //take a1 which is a white rook and move it to a3 which is blank
            char cFile = input.charAt(0);
            rank = parseInput(cFile);
            file = Integer.parseInt(input.charAt(1)+ "")-1;

            while(processMove(p1, gb) != true)
            {
                System.out.println("Illegal move, try again");
                System.out.print("White's Move: ");
                input = sc.nextLine();

                //FIX HOW INPUT IS PARSED
                cFile = input.charAt(0);
                rank = parseInput(cFile);
                file = Integer.parseInt(input.charAt(1)+ "")-1;
            }
            System.out.print("Black's Move: ");
            input = sc.nextLine();

            //take a1 which is a white rook and move it to a3 which is blank
            cFile = input.charAt(0);
            rank = parseInput(cFile);
            file = Integer.parseInt(input.charAt(1)+ "")-1;

            while(processMove(p2, gb) != true)
            {
                System.out.println("Illegal move, try again");
                System.out.print("Black's Move: ");
                input = sc.nextLine();

                //FIX HOW INPUT IS PARSED
                cFile = input.charAt(0);
                rank = parseInput(cFile);
                file = Integer.parseInt(input.charAt(1)+ "")-1;
            }
        }
    }

    private static boolean processMove(Player p, Cell[][] gb) {
        start = new Cell(file, rank, gb[file][rank].getPiece());
        //System.out.println(file + " " + rank + " " + gb[file][rank].getPiece());

        char cFile = input.charAt(0);
        rank = parseInput(cFile);
        file = Integer.parseInt(input.charAt(4)+ "")-1;
        end = new Cell(file, rank, gb[file][rank].getPiece());
        //System.out.println(file + " " + rank + " " + gb[file][rank].getPiece());

        //convert input to get file and rank as int
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
