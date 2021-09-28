package ru.comavp.testspringproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
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

    @Autowired
    public MusicPlayer(@Qualifier("myClassicalMusicId") final Music classicalMusic,
                       @Qualifier("rockMusic") final Music rockMusic,
                       @Qualifier("rapMusic") final Music rapMusic) {
        this.musicList.addAll(Arrays.asList(classicalMusic, rockMusic, rapMusic));
    }

    public String playMusic(final MusicTypeEnum musicType) {
        final int typeIndex = musicType.getTypeIndex();
        String result = "";
        if (typeIndex >= 0 && typeIndex < musicList.size()) {
            final Music currentMusic = musicList.get(typeIndex);
            final int songListSize = currentMusic.getSongListSize();
            result = currentMusic.getSong(getRandomSongIndex(0, songListSize));
        } else {
            result = "There is no music type with such name";
        }
        return result;
    }

    private int getRandomSongIndex(final Integer min, final Integer max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @PostConstruct
    public void initMusicPlayer() {
        System.out.println("Doing initialization of MusicPlayer");
    }

    @PreDestroy
    public void destroyMusicPlayer() {
        System.out.println("Doing destruction of MusicPlayer");
    }
}
