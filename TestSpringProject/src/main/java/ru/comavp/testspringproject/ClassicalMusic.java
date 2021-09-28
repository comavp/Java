package ru.comavp.testspringproject;

import java.util.Arrays;

public class ClassicalMusic implements Music {

    public ClassicalMusic() {
        songList.addAll(Arrays.asList("First classical music", "Second classical music", "Third classical music"));
    }
}
