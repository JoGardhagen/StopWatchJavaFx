package application;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Controller implements Initializable {

	Time time = new Time("00:00:00");

	@FXML
	private Text clock;
	@FXML
	private Button button1,reset;
	@FXML
	private ListView<String> stopList;
	boolean isClicked = false;

	Timeline timeline;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		clock.setText(time.getCurrentTime());
		timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
			time.oneSecondPassed();
			clock.setText(time.getCurrentTime());
		}));

		timeline.setCycleCount(Animation.INDEFINITE);

	}

	public void startStop(ActionEvent event) {

		buttonClicked();
	}

	private void buttonClicked() {

		if (!isClicked) {
			timeline.play();
			isClicked = true;
		} else {
			timeline.stop();
			isClicked = false;
			Time timeNow = new Time(new CurrentTime().currentTime());// måste har nytid här annars resettar han inte
			String onThaClock = timeNow.getCurrentTime();
			String newTime = time.getCurrentTime().toString();
			stopList.getItems().addAll(onThaClock + "\t" + newTime+"\t stopped");

		}

	}
	public void resetClear(ActionEvent event) {
		stopList.getItems().add("reset timer");
		time.timeReset(time.getCurrentTime());
	}
	

}
