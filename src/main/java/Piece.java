/* Copyright (c) 2015 Andrew Soutar <andrew@andrewsoutar.com>
 *
 * See the COPYING file for details.
 */

import com.andrewsoutar.cmp128.Utilities;

public class Piece {
    private int spaceNumber;

    private char chr;
    public char getChr () {
        return (chr);
    }

    private Player player = null;
    public Player getPlayer () {
        return (player);
    }

    public class SpaceFilledException extends Exception {
        private static final long serialVersionUID = 1L;

        public SpaceFilledException (int space_no) {
            super ("Space already filled: " + space_no + ".");
        }
    }

    public Piece (int spaceNumber) {
        this.spaceNumber = spaceNumber;
        chr = Integer.toString (spaceNumber + 1).charAt (0);
    }

    public void setPlayer (Player player) throws SpaceFilledException {
        if (this.player != null) {
            throw new SpaceFilledException (spaceNumber);
        }
        this.player = player;
        chr = player.getSymbol ();
    }

    private boolean differentPlayer (Piece [] board, int i) {
        return (!(board [i].getPlayer () == player));
    }

    private boolean check_win (Piece [] board) {
        int col = spaceNumber % 3;
        int row = spaceNumber / 3; // Integer division!

        colBlock: {
            for (int i = col; i < board.length; i += 3) {
                if (differentPlayer (board, i)) {
                    break colBlock;
                }
            }
            return (true);
        }

        rowBlock: {
            for (int i = 3 * row; i < (3 * (row + 1)); i++) {
                if (differentPlayer (board, i)) {
                    break rowBlock;
                }
            }
            return (true);
        }

        if (col == row) {
            mainDiagBlock: {
                for (int i = 0; i < board.length; i += 4) {
                    if (differentPlayer (board, i)) {
                        break mainDiagBlock;
                    }
                }
                return (true);
            }
        }

        if ((row + col) == 2) {
            secondaryDiagBlock: {
                for (int i = 2; i < (board.length - 1); i += 2) {
                    if (differentPlayer (board, i)) {
                        break secondaryDiagBlock;
                    }
                }
                return (true);
            }
        }
        return (false);
    }

    public boolean win (Piece [] board) {
        if (check_win (board)) {
            System.out.println ();
            Utilities.print_bordered(new String [] {
                    "",
                    "    " + player.getName () + " wins this game!",
                    ""
                });
            player.incWins ();
            return (true);
        } else {
            return (false);
        }
    }
}
