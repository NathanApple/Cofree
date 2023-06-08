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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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

        public String getImage() {
            String[] images = { "grey", "yellow", "blue", "pink", "brown", "white", "silver", "orange" };
            return images[this.key];
        }

        public String getColor() {
            String[] colors = { "grey", "yellow", "blue", "pink", "brown", "white", "silver", "orange" };
            return colors[this.key];
        }

        public boolean allowOpen() {
            if (this.status == 0 && cardState == 0) {
                return true;
            } else {
                return false;
            }
        }

        public void openCard(int colIndex, int rowIndex) {
            this.status = 1;
            int keyColor = answer.get(colIndex).get(rowIndex).key + 1;
            pane.setStyle(paneStyle(String.valueOf(keyColor)));
        }

        public void forceOpenCard(int colIndex, int rowIndex) {
            this.status = 2;
            int keyColor = answer.get(colIndex).get(rowIndex).key + 1;
            pane.setStyle(paneStyle(String.valueOf(keyColor)));
            // pane.setStyle(paneStyle(color));
        }

        public void forceCloseCard() {
            this.status = 0;
            pane.setStyle(paneStyle("0"));
        }

        public boolean isPopulated() {
            if (this.key == -1) {
                return false;
            } else {
                return true;
            }
        }

        public void printData() {
            System.out.println(this.key + " : " + this.status);
        }

        public boolean closeCard() {

            if (this.status == 1) {
                this.status = 0;
                pane.setStyle(paneStyle("0"));
                // System.out.println("wrong");
                return true;
            }
            return false;
        }

        public boolean revealCard() {
            if (this.status == 1) {
                this.status = 2;
                return true;
            }
            return false;
        }
    }

    public void closeCard() {
        this.cardState = 1;
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < answer.size(); i++) {
                    for (int j = 0; j < answer.get(i).size(); j++) {
                        answer.get(i).get(j).closeCard();
                    }
                }
                cardState = 0;

            }
        }));

        // timeline.setCycleCount(table.length - 1);
        timeline.play();

    }

    public void revealCard() {
        for (int i = 0; i < answer.size(); i++) {
            for (int j = 0; j < answer.get(i).size(); j++) {
                answer.get(i).get(j).revealCard();
            }
        }
    }

    public void closeAllCard() {
        System.out.println("Close All Card");
        for (int i = 0; i < answer.size(); i++) {
            for (int j = 0; j < answer.get(i).size(); j++) {
                answer.get(i).get(j).forceCloseCard();
            }
        }
    }

    public void openAllCard() {
        for (int i = 0; i < answer.size(); i++) {
            for (int j = 0; j < answer.get(i).size(); j++) {
                answer.get(i).get(j).forceOpenCard(i, j);
            }
        }
    }

    List<List<Answer>> answer = new ArrayList<List<Answer>>();
    int col = 4;
    int row = 3;
    // int opennedCard = 0;
    int lastKey = -1;

    // 0: Safe to open, 1: Process are using this state
    int cardState = 0;

    private void populateAnswer() {
        int col = this.col;
        int row = this.row;
        answer.removeAll(answer);

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
            for (int j = i * (col - 1); j < (i * (col - 1)) + row; j++) {
                // System.out.println(j);
                columnOfAnswer.add(listOfAnswer.get(j));
            }
            // System.out.println("END");
            answer.add(columnOfAnswer);
        }

        // for (int i = 0; i < listOfAnswer.size(); i++)
        // listOfAnswer.get(i).printData();
        // Printing and display the elements in ArrayList
        // System.out.print( + " ");
    }

    @FXML
    private Label lifeLabel;

    private int life;

    @FXML
    private Label scoreLabel;

    private int score;

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

    public void setScore() {
        lifeLabel.setText("Attempt: " + this.life);
        scoreLabel.setText("Correct Guess: " + this.score);
    }

    public void incrementScore() {
        this.score++;
        setScore();
        if (this.score >= row * col / 2) {
            revealPrize();
        }
    }

    public void decrementLife() {
        this.life--;
        setScore();
        if (this.life <= 0) {
            System.out.println("0 life");
            openAllCard();
            revealPrize();
        } else {
            closeCard();
        }
    }

    public void revealPrize() {
        System.out.println("Prize: ");
        Parent root;
        try {
            // root =
            // FXMLLoader.load(getClass().getClassLoader().getResource("path/to/other/view.fxml"));
            FXMLLoader fxmlLoader = App.getFXML("secondary");
            root = fxmlLoader.load();
            SecondaryController controller = fxmlLoader.<SecondaryController>getController();
            controller.setScore(score);
            // controller.viewPrize();
            Stage stage = new Stage();
            stage.setTitle("Prize");
            stage.setScene(new Scene(root, 450, 700));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // closeAllCard();
    }

    public void initialize() {
        reset(null);
        revealPrize();
    }

    public String paneStyle(String cardNumber) {
        return "-fx-border-width:2px;-fx-spacing: 20;-fx-padding: 5px;-fx-border-insets: 10px 20px;-fx-background-insets: 10px 20px;-fx-border-color: gray;"
                + "-fx-background-image: url(\"card" + cardNumber
                + ".jpg\");-fx-background-repeat: stretch;-fx-background-size: 150 205;-fx-background-position: center center;";

        // return "-fx-background-color:" + color
        // + ";-fx-border-width:2px;-fx-spacing: 20;-fx-padding: 5px;-fx-border-insets:
        // 10px 20px;-fx-background-insets: 10px 20px;-fx-border-color: gray;"
        // + "-fx-background-image:
        // url(\"\\images\\card\\1.jpg\");-fx-background-repeat:
        // stretch;-fx-background-size: 900 506; ";
    }

    private void addPane(int colIndex, int rowIndex) {
        Pane pane = new Pane();
        // String[] colors = {"grey", "yellow", "blue", "pink", "brown", "white",
        // "silver", "orange", "lightblue", "grey"};
        pane.setPrefSize(150, 400);
        pane.setStyle(paneStyle("0"));
        // pane.setBorder(new Border(new BorderStroke(Color.BLACK,
        // BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        // pane.setStyle("");

        pane.setOnMouseClicked(e -> {
            if (answer.get(colIndex).get(rowIndex).allowOpen()) {
                answer.get(colIndex).get(rowIndex).openCard(colIndex, rowIndex);
                if (this.lastKey == -1) {
                    this.lastKey = answer.get(colIndex).get(rowIndex).key;
                } else {
                    if (answer.get(colIndex).get(rowIndex).key == this.lastKey) {
                        incrementScore();
                        revealCard();
                    } else {
                        decrementLife();
                    }
                    this.lastKey = -1;
                }
            }
            System.out.printf("Mouse enetered cell [%d, %d]%n", colIndex, rowIndex);
        });
        answer.get(colIndex).get(rowIndex).pane = pane;
        cardGrid.add(pane, colIndex, rowIndex);
        // pane.setStyle("-fx-background-color:black;-fx-border-width:2px;-fx-padding:
        // 15;-fx-spacing: 10;-fx-padding: 5px;-fx-border-insets:
        // 5px;-fx-background-insets: 5px;");

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
        // label1.setText(GridPane.getRowIndex((Node) source) + " " +
        // GridPane.getColumnIndex((Node) source));
        System.out.println("Row: " + GridPane.getRowIndex((Node) source));
        System.out.println("Column: " + GridPane.getColumnIndex((Node) source));
    }

    @FXML
    public void reset(ActionEvent event) {
        cardGrid.getChildren().clear();
        cardGrid.getColumnConstraints().clear();
        cardGrid.getRowConstraints().clear();
        int numCols = 4;
        int numRows = 3;
        this.life = 5;
        this.score = 0;
        setScore();
        populateAnswer();
        // answer.get(1).get(1).printData();
        // answer.get(0).get(1).printData();
        // answer.get(2).get(2).printData();
        // answer.get(0).get(0).printData();
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.SOMETIMES);
            colConstraints.setMinWidth(60.0);

            cardGrid.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.SOMETIMES);
            // rowConstraints.setPrefHeight(300.0);
            rowConstraints.setMinHeight(100.0);
            cardGrid.getRowConstraints().add(rowConstraints);
        }

        // cardGrid.setGridLinesVisible(true);

        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                addPane(i, j);
            }
        }
    }
}
