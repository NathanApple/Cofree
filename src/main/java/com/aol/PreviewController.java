package com.aol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

public class PreviewController {

    @FXML
    private ImageView prizeImage;
    // @FXML
    // private Text textCode;

    public void initialize() {
        Image image = new Image("couponunknown.png");
        prizeImage.setImage(image);
    }

    private int score = -1;

    public void setScore(int score) {
        this.score = score;
        Image image = new Image("coupon" + score + ".png");
        prizeImage.setImage(image);

    }

    private String uuidString = "";

    public void openCoupon(String uuid) {
        System.out.println("UUID: " + uuid);
        File csvFile = new File("winner.csv");
        // try (FileWriter fileWriter = new FileWriter(csvFile, true)) {
        // StringBuilder line = new StringBuilder();

        // fileWriter.close();

        // // textCode.setText(uuid.toString().toUpperCase());
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("winner.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] data = line.split(splitBy); // use comma as separator
                if (data[0].equals(uuid)) {
                    System.out.println("Coupon" + data[1]);
                    this.setScore(Integer.valueOf(data[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}