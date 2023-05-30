module com.aol {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.aol to javafx.fxml;
    exports com.aol;
}
