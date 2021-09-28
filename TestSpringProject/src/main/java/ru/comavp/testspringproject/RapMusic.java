package ru.comavp.testspringproject;

import java.util.Arrays;

public class RapMusic implements Music {

    public RapMusic() {
        songList.addAll(Arrays.asList("First rap music", "Second rap music", "Third rap music"));
    }
}
