package com.aol;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SecondaryController {

    @FXML
    private Label prizeLabel;

    public void initialize() {
    }

    private int score = -1;

    public void setScore(int score) {
        this.score = score;
    }

    public void viewPrize(){
        prizeLabel.setText(String.valueOf(score));
    }
}