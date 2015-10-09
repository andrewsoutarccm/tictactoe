/* Copyright (c) 2015 Andrew Soutar <andrew@andrewsoutar.com>
 *
 * See the COPYING file for details.
 */

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

    private boolean differentPlayer (Player player, int i) {
        return (!(board [i].getPlayer () == player));
    }

    private boolean win (Piece piece) {
        Player player = piece.getPlayer ();
        int spaceNumber = piece.getSpaceNumber (),
            col = spaceNumber % 3,
            row = spaceNumber / 3;

        colBlock: {
            for (int i = col; i < board.length; i += 3) {
                if (differentPlayer (player, i)) {
                    break colBlock;
                }
            }
            return (true);
        }

        rowBlock: {
            for (int i = 3 * row; i < (3 * (row + 1)); i++) {
                if (differentPlayer (player, i)) {
                    break rowBlock;
                }
            }
            return (true);
        }

        if (col == row) {
            mainDiagBlock: {
                for (int i = 0; i < board.length; i += 4) {
                    if (differentPlayer (player, i)) {
                        break mainDiagBlock;
                    }
                }
                return (true);
            }
        }

        if ((row + col) == 2) {
            secondaryDiagBlock: {
                for (int i = 2; i < (board.length - 1); i += 2) {
                    if (differentPlayer (player, i)) {
                        break secondaryDiagBlock;
                    }
                }
                return (true);
            }
        }

        return (false);
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
            Player player = players [player_no];
            printBoard ();
            Piece piece = player.getPiece (board);
            if (win (piece)) {
                System.out.println ();
                Utilities.print_bordered (new String [] {
                        "",
                        "    " + player.getName () + " wins this game!",
                        ""
                    });
                player.incWins ();
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
