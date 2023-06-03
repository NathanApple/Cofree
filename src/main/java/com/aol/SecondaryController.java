package com.aol;

import java.io.IOException;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SecondaryController {

    @FXML
    private Label prizeLabel;

    @FXML
    private Button prizeButton;

    public void initialize() {
        prizeButton.setVisible(true);
        // prizeLabel.setVisible(false);
        prizeLabel.setText("???");

        prizeButton.setOnMouseClicked(event -> {
            prizeButton.setVisible(false);
            viewPrize();
        });
    }

    private int score = -1;

    public void setScore(int score) {
        this.score = score;
    }

    public void viewPrize() {
        // String[] prize = {"", "yellow", "blue", "pink", "brown", "white", "silver",
        // "orange"};
        Random random = new Random();
        int discount = (random.nextInt(4) + this.score * 2) * 5;
        if (discount > 0) {
            if (discount < 25) {
                prizeLabel.setText("Kupon Diskon " + String.valueOf(discount) + "%\nMaks: Rp 10.000");
            } else {
                prizeLabel.setText("Kupon Diskon " + String.valueOf(discount) + "%\nMaks: Rp 25.000");
            }
        } else {
            prizeLabel.setText("Maaf Anda Kurang Beruntung\nSilahkan Coba Lagi Nanti");
        }
        prizeLabel.setVisible(true);
    }
}