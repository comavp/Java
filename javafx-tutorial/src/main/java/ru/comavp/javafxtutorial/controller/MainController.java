package ru.comavp.javafxtutorial.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.comavp.javafxtutorial.application.ShinyButton;
import ru.comavp.javafxtutorial.service.WeatherService;

@Component
@FxmlView("main.fxml")
public class MainController {

    @FXML
    private ShinyButton firstShinyButton;
    @FXML
    private ShinyButton secondShinyButton;
    @FXML
    private ShinyButton thirdShinyButton;
    @FXML
    private ShinyButton fourthShinyButton;
    @FXML
    private Button mainButton;
    @FXML
    private Label weatherLabel;

    private WeatherService weatherService;

    @Autowired
    public MainController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public void buttonClicked() {
        System.out.println("Button clicked!");
        mainButton.setText("Click me again!");

        firstShinyButton.setShiny(!firstShinyButton.isShiny());
        secondShinyButton.setShiny(!secondShinyButton.isShiny());
        thirdShinyButton.setShiny(!thirdShinyButton.isShiny());
        fourthShinyButton.setShiny(!fourthShinyButton.isShiny());
    }

    public void loadWeatherForecast(ActionEvent actionEvent) {
        this.weatherLabel.setText(weatherService.getWeatherForecast());
    }
}
