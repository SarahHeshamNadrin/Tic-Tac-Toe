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
import Main.Game;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author sarah
 */
public class GridController implements Initializable {

    @FXML
    private GridPane board;
    private Game game;

    /**
     *
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        game = new Game();
        System.out.println("initialize function");

    }

    @FXML
    private void mouseClick(MouseEvent event) {

        Node source = (Node) event.getTarget();

        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
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

    private void setImage(String image, Node source) {
        Image img = new Image(image);
        ImageView iv = (ImageView) source;
        iv.setImage(img);
    }

}
