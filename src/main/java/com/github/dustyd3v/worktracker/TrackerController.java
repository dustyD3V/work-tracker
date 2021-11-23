package com.github.dustyd3v.worktracker;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TrackerController implements Initializable {

    boolean timerMainRunning = false;
    Timeline timelineMain;
    Timeline timelineWork;
    Timeline timelineMeetings;
    Timeline timelineBreaks;
    LocalTime timeMain = LocalTime.parse("00:00:00");
    LocalTime timeWork = LocalTime.parse("00:00:00");
    LocalTime timeMeetings = LocalTime.parse("00:00:00");
    LocalTime timeBreaks = LocalTime.parse("00:00:00");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timelineMain = new Timeline(new KeyFrame(Duration.millis(1000), ae -> incrementTimeMain()));
        timelineMain.setCycleCount(Animation.INDEFINITE);
        timelineWork = new Timeline(new KeyFrame(Duration.millis(1000), ae -> incrementTimeWork()));
        timelineWork.setCycleCount(Animation.INDEFINITE);
        timelineMeetings = new Timeline(new KeyFrame(Duration.millis(1000), ae -> incrementTimeMeetings()));
        timelineMeetings.setCycleCount(Animation.INDEFINITE);
        timelineBreaks = new Timeline(new KeyFrame(Duration.millis(1000), ae -> incrementTimeBreaks()));
        timelineBreaks.setCycleCount(Animation.INDEFINITE);
    }

    private void incrementTimeMain() {
        timeMain = timeMain.plusSeconds(1);
        timerMainLabel.setText(timeMain.format(dtf));
    }

    private void incrementTimeWork() {
        timeWork = timeWork.plusSeconds(1);
        timerWorkLabel.setText(timeWork.format(dtf));
    }

    private void incrementTimeMeetings() {
        timeMeetings = timeMeetings.plusSeconds(1);
        timerMeetingsLabel.setText(timeMeetings.format(dtf));
    }

    private void incrementTimeBreaks() {
        timeBreaks = timeBreaks.plusSeconds(1);
        timerBreaksLabel.setText(timeBreaks.format(dtf));
    }

    @FXML
    void startWork(ActionEvent event) {
        if(!timerMainRunning) {
            timelineMain.play();
            timerMainRunning = true;
        }
        timelineWork.play();
        timelineBreaks.pause();
        timelineMeetings.pause();
    }

    @FXML
    void startMeetings(ActionEvent event) {
        if(!timerMainRunning) {
            timelineMain.play();
            timerMainRunning = true;
        }
        timelineMeetings.play();
        timelineBreaks.pause();
        timelineWork.pause();
    }

    @FXML
    void startBreaks(ActionEvent event) {
        if(!timerMainRunning) {
            timelineMain.play();
            timerMainRunning = true;
        }
        timelineBreaks.play();
        timelineMeetings.pause();
        timelineWork.pause();
    }

    @FXML
    void pause(ActionEvent event) {
        if (timelineMain.getStatus().equals(Animation.Status.RUNNING)) {
            timelineMain.pause();
            timelineWork.pause();
            timelineMeetings.pause();
            timelineBreaks.pause();
            timerMainRunning = false;
        }
    }

    @FXML
    void reset(ActionEvent event) {
        timerMainRunning = false;
        timelineMain.stop();
        timeMain = LocalTime.parse("00:00:00");
        timerMainLabel.setText(timeMain.format(dtf));
        timelineWork.stop();
        timeWork = LocalTime.parse("00:00:00");
        timerWorkLabel.setText(timeWork.format(dtf));
        timelineMeetings.stop();
        timeMeetings = LocalTime.parse("00:00:00");
        timerMeetingsLabel.setText(timeMeetings.format(dtf));
        timelineBreaks.stop();
        timeBreaks = LocalTime.parse("00:00:00");
        timerBreaksLabel.setText(timeBreaks.format(dtf));
    }

    @FXML
    private Label timerMainLabel;

    @FXML
    private Label timerWorkLabel;

    @FXML
    private Label timerMeetingsLabel;

    @FXML
    private Label timerBreaksLabel;

}
