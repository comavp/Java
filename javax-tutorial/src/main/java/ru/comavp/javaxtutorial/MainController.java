package ru.comavp.javaxtutorial;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

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

    public MainController() {

    }

    public void buttonClicked() {
        System.out.println("Button clicked!");
        mainButton.setText("Click me again!");

        firstShinyButton.setShiny(!firstShinyButton.isShiny());
        secondShinyButton.setShiny(!secondShinyButton.isShiny());
        thirdShinyButton.setShiny(!thirdShinyButton.isShiny());
        fourthShinyButton.setShiny(!fourthShinyButton.isShiny());
    }
}
