package AI;

import main.Game;

/**
 * Plays a random move in Tic Tac Toe.
 */
public class Random {

    /**
     * Random cannot be instantiated.
     */
    private Random () {}

    /**
     * Execute the algorithm.
     * @param board     the Tic Tac Toe board to play on
     */
    static public int run (Game board) {
        int[] moves = new int[board.getAvailableMoves().size()];
        int index = 0;

        for (Integer item : board.getAvailableMoves()) {
            moves[index++] = item;
        }

        int randomMove = moves[new java.util.Random().nextInt(moves.length)];
        board.move(randomMove);
        return randomMove;
    }

}
