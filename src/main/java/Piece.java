/* Copyright (c) 2015 Andrew Soutar <andrew@andrewsoutar.com>
 *
 * See the COPYING file for details.
 */

public class Piece {
    private int spaceNumber;
    public int getSpaceNumber () {
        return (spaceNumber);
    }

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
}
