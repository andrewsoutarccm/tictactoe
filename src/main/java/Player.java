/* Copyright (c) 2015 Andrew Soutar <andrew@andrewsoutar.com>
 *
 * See the COPYING file for details.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    private Scanner kbdScanner;

    private char symbol;
    public char getSymbol () {
        return (symbol);
    }

    private String name;
    public String getName () {
        return (name);
    }

    private int firstCount;
    public int getFirstCount () {
        return (firstCount);
    }
    public int incFirstCount () {
        return (++firstCount);
    }

    private int wins;
    public int getWins () {
        return (wins);
    }
    public int incWins () {
        return (++wins);
    }

    private String prompt (String prompt_str) {
        System.out.print (name + ": " + prompt_str + ": ");
        return (kbdScanner.nextLine ());
    }

    public Player (Scanner kbdScanner, char symbol) {
        this.kbdScanner = kbdScanner;
        this.symbol = symbol;
        name = "Player " + symbol;
        name = prompt ("Enter your name");
    }

    public Piece getPiece (Piece [] board) {
        while (true) {
            try {
                Scanner intScanner = new Scanner (prompt ("Enter a space number"));
                int space = intScanner.nextInt () - 1;
                intScanner.close ();

                Piece piece = board [space];
                piece.setPlayer (this);
                return (piece);
            } catch (InputMismatchException e) {
                System.out.println ("Not a number.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println ("Not a valid space (1-9, inclusive).");
            } catch (Piece.SpaceFilledException e) {
                System.out.println ("Space is already filled.");
            }
        }
    }
}
