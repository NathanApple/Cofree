package com.aol;

import java.io.IOException;
import java.lang.System.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PrimaryController {

    // private static final Logger logger =
    // LoggerFactory.getLogger(ButtonEventHandlerController.class);

    @FXML
    private Label label1;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    // @FXML
    // public void buttonPressed(ActionEvent event) {
    //     Object source = event.getSource();
    //     System.out.println("TEST");
    //     System.out.println(source);
    //     // ...
    // }

    @FXML
    public void viewCard(ActionEvent event) {
        // logger.info("OnAction {}", event);
        Object source = event.getSource();
        System.out.println("TEST");
        System.out.println(source);
        // Image img = new Image(getClass().getResourceAsStream("search.png"));
        // ImageView imgView = new ImageView(img);
        // source.setText = "";
        // setText("");

        // FXMLLoader fxmlLoader = new FXMLLoader();
        label1.setText("A");
        // label1.setText(GridPane.getRowIndex((Node) source) + " " +
        // GridPane.getColumnIndex((Node) source));
        System.out.println("Row: " + GridPane.getRowIndex((Node) source));
        System.out.println("Column: " + GridPane.getColumnIndex((Node) source));
    }
}
