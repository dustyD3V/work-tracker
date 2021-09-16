package com.github.dustyd3v.worktracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WorkTracker extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WorkTracker.class.getResource("tracker-view.fxml"));
        stage.setTitle("Work Tracking Tool");
        Scene scene = new Scene(fxmlLoader.load(), 353, 373);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
