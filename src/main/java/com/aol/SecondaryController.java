package com.aol;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SecondaryController {

    // @FXML
    // private Label prizeLabel;

    @FXML
    private Button prizeButton;

    @FXML
    private ImageView prizeImage;

    @FXML
    private TextField textfieldCode;

    public void initialize() {
        prizeButton.setVisible(true);
        // prizeLabel.setVisible(false);
        // prizeLabel.setText("???");

        Image image = new Image("couponunknown.png");
        prizeImage.setImage(image);

        prizeButton.setOnMouseClicked(event -> {
            prizeButton.setVisible(false);
            viewPrize();
        });

        // viewPrize();
    }

    private int score = -1;

    public void setScore(int score) {
        this.score = score;
    }

    public void viewPrize() {
        Random random = new Random();
        int coupon = (random.nextInt(3) + this.score) - 1;

        if (coupon <= 0) {
            coupon = 0;
        } else if (coupon >= 5) {
            coupon = 5;
        }
        Image image = new Image("coupon" + coupon + ".png");

        prizeImage.setImage(image);

    }
}