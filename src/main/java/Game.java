import java.util.Scanner;

import com.andrewsoutar.cmp128.Utilities;

public class Game {
    Scanner kbdScanner;
    private Player [] players;
    private Piece [] board = new Piece [9];

    private int getFirstPlayer () {
        int firstPlayer = 0, minFirstCount = players [0].getFirstCount ();
        for (int i = 1; i < players.length; i++) {
            if (players [i].getFirstCount () < minFirstCount) {
                firstPlayer = i;
                minFirstCount = players [i].getFirstCount ();
            }
        }
        players [firstPlayer].incFirstCount ();
        return (firstPlayer);
    }

    private void printBoard () {
        System.out.println ();
        System.out.println ("   |   |   ");
        System.out.println (" " +
                            board [0].getChr () + " | " +
                            board [1].getChr () + " | " +
                            board [2].getChr () + " ");
        System.out.println ("___|___|___");
        System.out.println ("   |   |   ");
        System.out.println (" " +
                            board [3].getChr () + " | " +
                            board [4].getChr () + " | " +
                            board [5].getChr () + " ");
        System.out.println ("___|___|___");
        System.out.println ("   |   |   ");
        System.out.println (" " +
                            board [6].getChr () + " | " +
                            board [7].getChr () + " | " +
                            board [8].getChr () + " ");
        System.out.println ("   |   |   ");
        System.out.println ();
    }

    public Game (Scanner kbdScanner, Player [] players) {
        this.kbdScanner = kbdScanner;
        this.players = players;
        for (int i = 0; i < board.length; i++) {
            board [i] = new Piece (i);
        }
    }

    public void run () {
        for (int i = 0, player_no = getFirstPlayer (); i < 9;
             i++, player_no = ++player_no % players.length) {
            printBoard ();
            Piece piece = players [player_no].getPiece (board);
            if (piece.win (board)) {
                return;
            }
        }
        System.out.println ();
        Utilities.print_bordered (new String [] {
                "",
                "    This game is a tie!",
                ""
            });
    }
}
