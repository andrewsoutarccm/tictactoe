import com.andrewsoutar.cmp128.Utilities;
import java.util.Scanner;

public class Main {
    private static final int GAME_COUNT_DEFAULT = 3;

    public static void main (String [] args) {
        Scanner kbdScanner = new Scanner (System.in);

        Utilities.print_header (Utilities.ProgramType.LESSON, 3, "Tic Tac Toe");

        int game_count = GAME_COUNT_DEFAULT;
        if (args.length > 0) {
            try {
                game_count = Integer.parseInt (args [0]);
            } catch (NumberFormatException e) {}
        }

        TicTacToe ticTacToe = new TicTacToe (kbdScanner, game_count);
        ticTacToe.run ();
    }
}
