package ru.comavp.testspringproject;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayer {
    private List<Music> musicList = new ArrayList<>();
    private String name;
    private int volume;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public MusicPlayer() {}

    public MusicPlayer(final List<Music> musicList) {
        this.musicList = musicList;
    }

    public void setMusicList(final List<Music> musicList) {
        this.musicList = musicList;
    }

    public void playMusic() {
        musicList.forEach(music -> System.out.println("Playing: " + music.getSong()));
    }

    public void initMusicPlayer() {
        System.out.println("Doing initialization of MusicPlayer");
    }

    public void destroyMusicPlayer() {
        System.out.println("Doing destruction of MusicPlayer");
    }
}
