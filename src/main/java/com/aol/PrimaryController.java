package com.aol;

import java.io.IOException;
import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PrimaryController {

    // private static final Logger logger =
    // LoggerFactory.getLogger(ButtonEventHandlerController.class);
    public class Answer {
        // Answer {};

        // -1 = not populated
        public int key;

        // 0 Unoppened, 1 open, 2 revealed
        public int status;

        public Pane pane;

        public Answer() {
            this.key = -1;
            this.status = 0;
        }

        public Answer(int key) {
            this.key = key;
        }

        public String getImage(){
            List<String> imageList = new ArrayList<String>();
            return "Image";
        }

        public String getColor(){
            String[] colors = {"grey", "yellow", "blue", "pink", "brown", "white", "silver", "orange"};
            return colors[this.key];
        }

        public boolean allowOpen(){
            if (this.status == 0){
                return true;
            } else {
                return false;
            }
        }

        public void openCard(int colIndex, int rowIndex){
            this.status = 1;
            String color = answer.get(colIndex).get(rowIndex).getColor();
            pane.setStyle("-fx-background-color:"+color+";-fx-border-width:2px;-fx-padding: 15;-fx-spacing: 10;-fx-padding: 5px;-fx-border-insets: 5px;-fx-background-insets: 5px;");

        }

        public boolean isPopulated(){
            if (this.key == -1){
                return false;
            } else {
                return true;
            }
        }

        public void printData(){
            System.out.println(this.key+ " : " + this.status);
        }

        public boolean closeCard(){
            System.out.println("wrong");

            if (this.status == 1){
                this.status = 0;
                pane.setStyle("-fx-background-color:lightblue;-fx-border-width:2px;-fx-padding: 15;-fx-spacing: 10;-fx-padding: 5px;-fx-border-insets: 5px;-fx-background-insets: 5px;");
                System.out.println("wrong");
                return true;
            }
            return false;
        }

        public boolean revealCard(){
            if (this.status == 1){
                this.status = 2;
                return true;
            }
            return false;
        }
    }

    public void closeCard(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {

        
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < answer.size(); i++){
                    for (int j = 0; j < answer.get(i).size(); j++) {
                        answer.get(i).get(j).closeCard();
                    }
                }
            }
        }));

        // timeline.setCycleCount(table.length - 1);
        timeline.play();

    }

    public void revealCard(){
        for (int i = 0; i < answer.size(); i++){
            for (int j = 0; j < answer.get(i).size(); j++) {
                answer.get(i).get(j).revealCard();
            }
        }
    }

    List<List<Answer>> answer = new ArrayList<List<Answer>>();  
    int col = 4;
    int row = 3;
    // int opennedCard = 0;
    int lastKey = -1;

    private void populateAnswer(){
        int col = this.col;
        int row = this.row;

        List<Answer> listOfAnswer = new ArrayList<Answer>();

        for (int i = 0; i < col * row / 2; i++) {
            // answer[0][0];
            listOfAnswer.add(new Answer(i));
            listOfAnswer.add(new Answer(i));
        }

        // System.out.println(listOfAnswer.size());
        Collections.shuffle(listOfAnswer);

        for (int i = 0; i < col; i++) {
            List<Answer> columnOfAnswer = new ArrayList<Answer>();
            for (int j = i*(col-1); j < (i*(col-1)) + row; j++) {
                // System.out.println(j);
                columnOfAnswer.add(listOfAnswer.get(j));
            }
            // System.out.println("END");
            answer.add(columnOfAnswer);
        }

        // for (int i = 0; i < listOfAnswer.size(); i++)
        //     listOfAnswer.get(i).printData();
        // Printing and display the elements in ArrayList
        // System.out.print( + " ");    
    }

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


    private StackPane getPane(int i) {
        String[] colors = {"grey", "yellow", "blue", "pink", "brown", "white", "silver", "orange", "lightblue", "grey"};
        StackPane pane = new StackPane();
        pane.setPrefSize(150,150);
        pane.setStyle("-fx-background-color:" + colors[i] + ";-fx-border-width:2px;");
        return pane;
    }


    public void initialize() {
        int numCols = 4 ;
        int numRows = 3 ;
        populateAnswer();
        answer.get(1).get(1).printData();
        answer.get(0).get(1).printData();
        answer.get(2).get(2).printData();
        answer.get(0).get(0).printData();
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

        cardGrid.setGridLinesVisible(true);

        for (int i = 0 ; i < numCols ; i++) {
            for (int j = 0; j < numRows; j++) {
                addPane(i, j);
            }
        }
    }

    private void addPane(int colIndex, int rowIndex) {
        Pane pane = new Pane();
        String[] colors = {"grey", "yellow", "blue", "pink", "brown", "white", "silver", "orange", "lightblue", "grey"};
        pane.setPrefSize(150,150);
        pane.setStyle("-fx-background-color:lightblue;-fx-border-width:2px;-fx-padding: 15;-fx-spacing: 10;-fx-padding: 5px;-fx-border-insets: 5px;-fx-background-insets: 5px;");
        // pane.setStyle("");
        
        pane.setOnMouseClicked(e -> {
            if (answer.get(colIndex).get(rowIndex).allowOpen()){
                answer.get(colIndex).get(rowIndex).openCard(colIndex, rowIndex);
                if (this.lastKey == -1){
                    this.lastKey = answer.get(colIndex).get(rowIndex).key;
                } else {
                    if (answer.get(colIndex).get(rowIndex).key == this.lastKey) {
                        revealCard();
                    } else {

                        closeCard();
                    }
                    this.lastKey = -1;
                }
            }
            System.out.printf("Mouse enetered cell [%d, %d]%n", colIndex, rowIndex);
        });
        answer.get(colIndex).get(rowIndex).pane = pane;
        cardGrid.add(pane, colIndex, rowIndex);
        // pane.setStyle("-fx-background-color:black;-fx-border-width:2px;-fx-padding: 15;-fx-spacing: 10;-fx-padding: 5px;-fx-border-insets: 5px;-fx-background-insets: 5px;");

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
