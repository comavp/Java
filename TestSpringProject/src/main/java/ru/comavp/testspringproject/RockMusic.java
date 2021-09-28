package ru.comavp.testspringproject;

import java.util.Arrays;

public class RockMusic implements Music {

    public RockMusic() {
        songList.addAll(Arrays.asList("First rock music", "Second rock music", "Third rock music"));
    }
}
