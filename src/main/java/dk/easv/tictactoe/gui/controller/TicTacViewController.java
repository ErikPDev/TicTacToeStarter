package dk.easv.tictactoe.gui.controller;

// Java imports

import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.bll.IGameBoard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

// Project imports

/**
 *
 * @author EASV
 */
public class TicTacViewController implements Initializable {
    private static final String TXT_PLAYER = "Player: ";
    @FXML
    private Label lblPlayer;
    @FXML
    private Button btnNewGame;
    @FXML
    private GridPane gridPane;
    private IGameBoard game;

    /**
     * Event handler for the grid buttons
     *
     * @param event
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            int player = game.getNextPlayer();
            if (!game.play(c, r))  return;

            Button btn = (Button) event.getSource();
            String xOrO = player == 0 ? "X" : "O";
            btn.setText(xOrO);

            if (game.isGameOver()) {
                int winner = game.getWinner();
                displayWinner(winner);
            } else {
                setPlayer();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Event handler for starting a new game
     *
     * @param event
     */
    @FXML
    private void handleNewGame(ActionEvent event) {
        game.newGame();
        setPlayer();
        clearBoard();
    }

    /**
     * Initializes a new controller
     *
     * @param url The location used to resolve relative paths for the root object, or
     *            {@code null} if the location is not known.
     * @param rb  The resources used to localize the root object, or {@code null} if
     *            the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new GameBoard();
        setPlayer();
    }

    /**
     * Set the next player
     */
    private void setPlayer() {
        lblPlayer.setText(TXT_PLAYER + game.getNextPlayerString());
    }


    /**
     * Finds a winner or a draw and displays a message based
     *
     * @param winner
     */
    private void displayWinner(int winner) {
        String message = switch (winner) {
            case -1 -> "It's a draw :-(";
            case 0 -> "Player X wins!!!";
            case 1 -> "Player O wins!!!";
            default -> "Player " + winner + " wins!!!";
        };
        lblPlayer.setText(message);
    }

    /**
     * Clears the game board in the GUI
     */
    private void clearBoard() {
        for (Node n : gridPane.getChildren()) {
            Button btn = (Button) n;
            btn.setText("");
        }
    }
}
