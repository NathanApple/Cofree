package com.aol;

// import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.objdetect.QRCodeDetector;

public class ScannerController {

    private static final int SCENE_W = 640;
    private static final int SCENE_H = 480;

    VideoCapture videoCapture;

    @FXML
    Canvas canvas;

    GraphicsContext g2d;
    // Stage stage;
    AnimationTimer timer;

    String sys_paths = "TEST";

    public void initialize() {

        // this.stage = stage;

        initOpenCv();

        // canvas = new Canvas(SCENE_W, SCENE_H);
        g2d = canvas.getGraphicsContext2D();
        g2d.setStroke(Color.GREEN);

        // Group group = new Group(canvas);

        // Scene scene = new Scene(group, SCENE_W, SCENE_H);

        // stage.setScene(scene);
        // stage.setResizable(false);
        // stage.show();

        timer = new AnimationTimer() {

            Mat mat = new Mat();

            @Override
            public void handle(long now) {

                videoCapture.read(mat);

                Image image = mat2Image(mat);

                QRCodeDetector decoder = new QRCodeDetector();
                Mat points = new Mat();
                String data = decoder.detectAndDecode(mat, points);

                if (!points.empty()) {
                    System.out.println("Decoded data: " + data);

                    // for (int i = 0; i < points.cols(); i++) {
                    // Point pt1 = new Point(points.get(0, i));
                    // Point pt2 = new Point(points.get(0, (i + 1) % 4));
                    // Imgproc.line(mat, pt1, pt2, new Scalar(255, 0, 0), 3);
                    // }

                    // System.exit(0);
                    // HighGui.imshow("Detected QR code", mat);
                    if (data.length() > 5) {
                        Parent root;
                        try {

                            // root =
                            // FXMLLoader.load(getClass().getClassLoader().getResource("path/to/other/view.fxml"));
                            FXMLLoader fxmlLoader = App.getFXML("preview");
                            root = fxmlLoader.load();
                            PreviewController controller = fxmlLoader.<PreviewController>getController();
                            // controller.setScore(score);
                            // controller.set
                            // controller.viewPrize();
                            controller.openCoupon(data);
                            Stage stage = new Stage();
                            stage.setTitle("Redeemed Coupon");
                            stage.getIcons().add(new Image("file:icon.png"));

                            stage.setScene(new Scene(root, 450, 550));
                            stage.show();
                            // timer
                            this.stop();
                            Stage thisStage = (Stage) canvas.getScene().getWindow();
                            thisStage.close();
                            videoCapture.release();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // System.exit(0);
                    }
                    // HighGui.waitKey(0);
                    // HighGui.destroyAllWindows();
                }
                String result = "";

                g2d.drawImage(image, 0, 0);

                // for (Rectangle2D rect : rectList) {
                // g2d.strokeRect(rect.getMinX(), rect.getMinY(), rect.getWidth(),
                // rect.getHeight());
                // }

            }
        };
        timer.start();

    }

    public static byte[] matToByteArray(Mat original) {
        int width = original.width(), height = original.height(), channels = original.channels();
        byte[] sourcePixels = new byte[width * height * channels];
        original.get(0, 0, sourcePixels);
        return sourcePixels;
    }

    private void initOpenCv() {

        setLibraryPath();

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        videoCapture = new VideoCapture();
        videoCapture.open(0);

        System.out.println("Camera open: " + videoCapture.isOpened());

        // stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        // public void handle(WindowEvent we) {

        // timer.stop();
        // videoCapture.release();

        // System.out.println("Camera released");

        // }
        // });
    }

    public static Image mat2Image(Mat mat) {
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png", mat, buffer);
        return new Image(new ByteArrayInputStream(buffer.toArray()));
    }

    private static void setLibraryPath() {

        try {
            System.setProperty("java.library.path", "lib/x64");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

    }

    public static String getOpenCvResource(Class<?> clazz, String path) {
        try {
            return Paths.get(clazz.getResource(path).toURI()).toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    // static void main(String[] args) {
    // launch(args);
    // }
}