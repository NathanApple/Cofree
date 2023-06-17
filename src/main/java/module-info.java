module com.aol {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.bytedeco.opencv;

    opens com.aol to javafx.fxml;

    exports com.aol;
}
