/* Copyright (c) 2015 Andrew Soutar <andrew@andrewsoutar.com>
 *
 * See the COPYING file for details.
 */

import java.util.LinkedList;
import java.util.Scanner;

import com.andrewsoutar.cmp128.Utilities;

public class TicTacToe {
    private Scanner kbdScanner;
    private int game_count;
    private final char [] symbols = { 'X', 'O' };
    private Player [] players = new Player [symbols.length];

    private boolean winner () {
        LinkedList<Player> winnerList = new LinkedList<Player> (),
            loserList = new LinkedList<Player> ();
        int winCount = -1;
        int totalWonGames = 0;
        for (Player player : players) {
            int wins = player.getWins ();
            totalWonGames += wins;
            if (wins < winCount) {
                loserList.add (player);
            } else if (wins > winCount) {
                loserList.addAll (winnerList);
                winnerList = new LinkedList<Player> ();
                winCount = wins;
                winnerList.add (player);
            } else {
                winnerList.add (player);
            }
        }

        if (totalWonGames >= game_count) {
            if (winnerList.size () == 1) {
                System.out.println ();
                Utilities.print_bordered (new String [] {
                        "",
                        "    " + winnerList.get (0).getName () + " wins the round!",
                        "",
                    });
                return (true);
            } else {
                players = winnerList.toArray (new Player [winnerList.size ()]);
                for (Player player : players) {
                    System.out.println (player.getName () + " is eliminated.");
                }
            }
        }
        return (false);
    }

    public TicTacToe (Scanner kbdScanner, int game_count) {
        this.kbdScanner = kbdScanner;
        this.game_count = game_count;
        for (int i = 0; i < symbols.length; i++) {
            players [i] = new Player (kbdScanner, symbols [i]);
        }
    }

    public void run () {
        do {
            Game game = new Game (kbdScanner, players);
            game.run ();
        } while (!(winner ()));
    }
}
