package ru.comavp.testspringproject;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RapMusic implements Music {

    public RapMusic() {
        songList.addAll(Arrays.asList("First rap music", "Second rap music", "Third rap music"));
    }
}
