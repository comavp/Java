package ru.comavp.testspringproject;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

public class MusicPlayer {
    private List<Music> musicList = new ArrayList<>();
    @Value("${musicPlayer.name}")
    private String name;
    @Value("${musicPlayer.volume}")
    private int volume;

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    public MusicPlayer() {}

    public MusicPlayer(final List<Music> musicList) {
        this.musicList = musicList;
    }

    public String playMusic() {
        final int typeIndex = getRandomIndex(0, musicList.size());
        String result = "";
        if (typeIndex >= 0 && typeIndex < musicList.size()) {
            final Music currentMusic = musicList.get(typeIndex);
            final int songListSize = currentMusic.getSongListSize();
            result = currentMusic.getSong(getRandomIndex(0, songListSize));
        } else {
            result = "There is no music type with such name";
        }
        return result;
    }

    private int getRandomIndex(final Integer min, final Integer max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public void initMusicPlayer() {
        System.out.println("Doing initialization of MusicPlayer");
    }

    public void destroyMusicPlayer() {
        System.out.println("Doing destruction of MusicPlayer");
    }
}
