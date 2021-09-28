package ru.comavp.testspringproject;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RockMusic implements Music {

    public RockMusic() {
        songList.addAll(Arrays.asList("First rock music", "Second rock music", "Third rock music"));
    }
}
