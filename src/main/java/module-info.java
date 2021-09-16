module com.github.dustyd3v.worktracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.github.dustyd3v.worktracker to javafx.fxml;
    exports com.github.dustyd3v.worktracker;
}
