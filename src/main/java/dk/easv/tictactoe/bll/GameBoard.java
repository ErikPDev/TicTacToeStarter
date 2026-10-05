package dk.easv.tictactoe.bll;

/**
 *
 * @author EASV
 */
public class GameBoard implements IGameBoard {

    // Global Fields
    // Keep track of the game: array 2d with game tiles
    // Keep track of the winner
    // Current Player

    private boolean player = true; // False = player0, true = player1

    private int[][] gameBoard = {
            {-1, -1, -1},
            {-1, -1, -1},
            {-1, -1, -1},
    }; // Game board of 3x3

    private int gameTurns = 0; // Max value should be 9.


    /**
     * Return 0 for player 0, 1 for player 1.
     * @return int id of the current player
     */
    private int getCurrentPlayer(){
        return player ? 1 : 0;
    }

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer() {
        return !player ? 1 : 0;
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row) {
        if (isGameOver()) return false;

        if (gameBoard[col][row] != -1) return false;

        gameBoard[col][row] = getCurrentPlayer();

        player = !player;
        gameTurns++;

        return true;
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will retun false.
     */
    public boolean isGameOver() {
         if (gameTurns >= 9) return true;

        // Check rows
        for (int row = 0; row < gameBoard.length; row++) {
            if (gameBoard[row][0] == 0 && gameBoard[row][1] == 0 && gameBoard[row][2] == 0) return true;
            if (gameBoard[row][0] == 1 && gameBoard[row][1] == 1 && gameBoard[row][2] == 1) return true;
        }

        // Check columns
        for (int col = 0; col < gameBoard[0].length; col++) {
            if (gameBoard[0][col] == 0 && gameBoard[1][col] == 0 && gameBoard[2][col] == 0) return true;
            if (gameBoard[0][col] == 1 && gameBoard[1][col] == 1 && gameBoard[2][col] == 1) return true;
        }

        // Check diagonals
        if (gameBoard[0][0] == 0 && gameBoard[1][1] == 0 && gameBoard[2][2] == 0) return true;
        if (gameBoard[0][0] == 1 && gameBoard[1][1] == 1 && gameBoard[2][2] == 1) return true;

        if (gameBoard[0][2] == 0 && gameBoard[1][1] == 0 && gameBoard[2][0] == 0) return true;
        if (gameBoard[0][2] == 1 && gameBoard[1][1] == 1 && gameBoard[2][0] == 1) return true;

        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner() {
        for (int[] col : gameBoard) {
            if (col[0] == 0 && col[1] == 0 && col[2] == 0) return 0;
            if (col[0] == 1 && col[1] == 1 && col[2] == 1) return 1;
        }

        // Check each column
        for (int row = 0; row < gameBoard.length; row++) {
            if (gameBoard[0][row] == 0 && gameBoard[0][row] == 0 && gameBoard[0][row] == 0) return 0;
            if (gameBoard[0][row] == 1 && gameBoard[0][row] == 1 && gameBoard[0][row] == 1) return 1;
        }

        // Check Diagonal
        if (gameBoard[0][0] == 0 && gameBoard[1][1] == 0 && gameBoard[2][2] == 0) return 0;
        if (gameBoard[0][0] == 1 && gameBoard[1][1] == 1 && gameBoard[2][2] == 1) return 1;

        if (gameBoard[0][2] == 0 && gameBoard[1][1] == 0 && gameBoard[2][0] == 0) return 0;
        if (gameBoard[0][2] == 1 && gameBoard[1][1] == 1 && gameBoard[2][0] == 1) return 1;

        return -1;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame() {
        this.gameBoard = new int[][]{
                {-1, -1, -1},
                {-1, -1, -1},
                {-1, -1, -1},
        };

        this.gameTurns = 0;

        this.player = true;

    }
}
