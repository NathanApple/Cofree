package com.aol;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class SecondaryController {

    // @FXML
    // private Label prizeLabel;

    @FXML
    private Button prizeButton;

    @FXML
    private ImageView prizeImage;

    @FXML
    private ImageView codeImage;

    // @FXML
    // private Text textCode;

    public void initialize() {
        prizeButton.setVisible(true);
        // prizeLabel.setVisible(false);
        // prizeLabel.setText("???");

        Image image = new Image("couponunknown.png");
        prizeImage.setImage(image);
        // textCode.setText("-------------------------------");

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

        // Coupon By pass
        // coupon += 3;
        if (coupon <= 0) {
            coupon = 0;
        } else if (coupon >= 5) {
            coupon = 5;
        }
        Image image = new Image("coupon" + coupon + ".png");

        if (coupon > 0) {
            UUID uuid = UUID.randomUUID();
            String[] code = { uuid.toString().toUpperCase(), String.valueOf(coupon) };
            File csvFile = new File("winner.csv");
            try (FileWriter fileWriter = new FileWriter(csvFile, true)) {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < code.length; i++) {
                    line.append(code[i]);
                    if (i != code.length - 1) {
                        line.append(',');
                    }
                }
                line.append("\n");
                fileWriter.write(line.toString());
                fileWriter.close();

                Image qrImage = new Image(
                        "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data="
                                + uuid.toString().toUpperCase());
                codeImage.setImage(qrImage);

                // textCode.setText(uuid.toString().toUpperCase());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        prizeImage.setImage(image);

    }
}