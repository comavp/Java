package ru.comavp.testspringproject;

import org.springframework.stereotype.Component;

@Component
public class Computer {
    private long id;
    private MusicPlayer player;

    public Computer(final MusicPlayer musicPlayer) {
        this.id = 1;
        this.player = musicPlayer;
    }

    public String playMusic(final MusicTypeEnum musicType) {
        return player.playMusic(musicType);
    }
}
