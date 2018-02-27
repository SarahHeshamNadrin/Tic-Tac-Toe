package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import main.Game;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import AI.Random;
import javafx.collections.ObservableList;
import javafx.event.Event;
import AI.Random;

/**
 * FXML Controller class
 *
 * @author sarah
 */
public class GridController implements Initializable {

    @FXML
    private GridPane board;
    private Game game;
    private Node source;

    /**
     *
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        game = new Game(Game.Mode.EasyAI);
        System.out.println("initialize function");
    }

    private Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    private void easyTurnAI() {
        int randomMove = Random.run(game);
        setImage("/views/imgs/woodO.jpg", getNodeByRowColumnIndex(randomMove % 3, randomMove / 3, board));
    }

    @FXML
    private void mouseClick(MouseEvent event) {
        source = (Node) event.getTarget();
        Integer colIndex, rowIndex;

        colIndex = GridPane.getColumnIndex(source);
        rowIndex = GridPane.getRowIndex(source);

        if (game.getPlayMode() == Game.Mode.EasyAI) {
            try {
                if (game.isMoveAvailable(rowIndex, colIndex) && game.getTurn() == Game.State.X) {
                    setImage("/views/imgs/woodX.jpg", source);
                    game.move(rowIndex, colIndex);
                    if (game.isGameOver()) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        if (game.getWinner() == Game.State.X || game.getWinner() == Game.State.O) {
                            alert.setContentText("Player " + game.getWinner() + " wins");
                        } else {
                            alert.setContentText("Draw Game");
                        }
                        alert.showAndWait();
                    }
                    easyTurnAI();
                    if (game.isGameOver()) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        if (game.getWinner() == Game.State.X || game.getWinner() == Game.State.O) {
                            alert.setContentText("Player " + game.getWinner() + " wins");
                        } else {
                            alert.setContentText("Draw Game");
                        }
                        alert.showAndWait();
                    }
                }
            } catch (Exception e) {
                System.out.println("invalid move!");
            }
        } else if (game.getPlayMode() == Game.Mode.MultiplayerLocal) {
            try {
                if (game.isMoveAvailable(rowIndex, colIndex)) {
                    if (game.getTurn() == Game.State.X) {
                        setImage("/views/imgs/woodX.jpg", source);
                    } else if (game.getTurn() == Game.State.O) {
                        setImage("/views/imgs/woodO.jpg", source);
                    }
                    game.move(rowIndex, colIndex);
                    if (game.isGameOver()) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        if (game.getWinner() == Game.State.X || game.getWinner() == Game.State.O) {
                            alert.setContentText("Player " + game.getWinner() + " wins");
                        } else {
                            alert.setContentText("Draw Game");
                        }
                        alert.showAndWait();
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid move!");
            }
        }
    }

    private void setImage(String image, Node source) {
        Image img = new Image(image);
        ImageView iv = (ImageView) source;
        iv.setImage(img);
    }

}
