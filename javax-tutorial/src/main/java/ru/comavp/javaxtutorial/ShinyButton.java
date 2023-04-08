package ru.comavp.javaxtutorial;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.scene.control.Button;

/*
* This is an example of custom pseudo class
* */
public class ShinyButton extends Button {

    private BooleanProperty shiny;

    public ShinyButton() {
        getStyleClass().add("my-button");

        shiny = new SimpleBooleanProperty(false);
        shiny.addListener(e -> pseudoClassStateChanged(PseudoClass.getPseudoClass("shiny"), shiny.get()));
    }

    public boolean isShiny() {
        return shiny.get();
    }

    public void setShiny(boolean shiny) {
        this.shiny.set(shiny);
    }
}
