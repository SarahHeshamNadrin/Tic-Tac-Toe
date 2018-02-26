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

/**
 * FXML Controller class
 *
 * @author sarah
 */
public class GridController implements Initializable {

    @FXML
    private GridPane board;
    
    /**
     * 
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        System.out.println("initialize function");

   
    }    

    @FXML
    private void mouseClick(MouseEvent event) {
        
       System.out.println("mouse pressed");
       Image x =new Image("/views/imgs/woodX.jpg");
       
        Node source = (Node)event.getTarget();
        //System.out.println(source);
       // System.out.println(event.getTarget());
        //System.out.println(event.getTarget());
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        System.out.println("col Index:"+colIndex);
        System.out.println("row Index:"+rowIndex);
        ImageView m1 = (ImageView)source;
        m1.setImage(x);
        
    }
    
}
