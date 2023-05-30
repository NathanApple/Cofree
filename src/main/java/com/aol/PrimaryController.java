package com.aol;

import java.io.IOException;
import java.lang.System.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class PrimaryController {

    // private static final Logger logger =
    // LoggerFactory.getLogger(ButtonEventHandlerController.class);

    @FXML
    private Label label1;

    @FXML
    private GridPane cardGrid;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void clickGrid(MouseEvent event) {
        // System.out.println("Clicked");
        // Node source = (Node) event.getTarget();
        Node source = (Node) event.getSource();
        System.out.println(event);
        System.out.println(source);
        // int columnIndex = GridPane.getColumnIndex(source);
        // int rowIndex = GridPane.getRowIndex(source);
        // System.out.println("Row : " + rowIndex + ", Col : " + columnIndex);
    }

    public void initialize() {
        int numCols = 4 ;
        int numRows = 3 ;

        for (int i = 0 ; i < numCols ; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.SOMETIMES);
            cardGrid.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0 ; i < numRows ; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.SOMETIMES);
            cardGrid.getRowConstraints().add(rowConstraints);
        }

        for (int i = 0 ; i < numCols ; i++) {
            for (int j = 0; j < numRows; j++) {
                addPane(i, j);
            }
        }
    }

    private void addPane(int colIndex, int rowIndex) {
        Pane pane = new Pane();
        pane.setOnMouseClicked(e -> {
            System.out.printf("Mouse enetered cell [%d, %d]%n", colIndex, rowIndex);
        });
        cardGrid.add(pane, colIndex, rowIndex);
    }


    // @FXML
    // public void buttonPressed(ActionEvent event) {
    // Object source = event.getSource();
    // System.out.println("TEST");
    // System.out.println(source);
    // // ...
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
