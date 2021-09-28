package ru.comavp.testspringproject;

public class Computer {
    private long id;
    private MusicPlayer player;

    public Computer(final MusicPlayer musicPlayer) {
        this.id = 1;
        this.player = musicPlayer;
    }

    public String playMusic() {
        return player.playMusic();
    }
}
