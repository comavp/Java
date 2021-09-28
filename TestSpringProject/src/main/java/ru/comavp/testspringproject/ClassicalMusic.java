package ru.comavp.testspringproject;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("myClassicalMusicId")
public class ClassicalMusic implements Music {

    public ClassicalMusic() {
        songList.addAll(Arrays.asList("First classical music", "Second classical music", "Third classical music"));
    }
}
