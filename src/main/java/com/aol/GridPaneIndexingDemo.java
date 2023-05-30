package com.aol;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GridPaneIndexingDemo extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 450, 450);
        stage.setScene(scene);
        stage.setTitle("GridPane");
        stage.show();

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.addRow(0, getPane(1), getPane(2), getPane(3));
        grid.addRow(1, getPane(4), getPane(5), getPane(6));
        grid.addRow(2, getPane(7), getPane(8), getPane(9));
        grid.setOnMouseClicked(event -> {
            Node source = (Node) event.getTarget();
            int columnIndex = GridPane.getColumnIndex(source);
            int rowIndex = GridPane.getRowIndex(source);
            System.out.println("Row : " + rowIndex + ", Col : " + columnIndex);
        });
        root.getChildren().add(grid);
    }

    private StackPane getPane(int i) {
        String[] colors = {"grey", "yellow", "blue", "pink", "brown", "white", "silver", "orange", "lightblue", "grey"};
        StackPane pane = new StackPane();
        pane.setPrefSize(150,150);
        pane.setStyle("-fx-background-color:" + colors[i] + ";-fx-border-width:2px;");
        return pane;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}