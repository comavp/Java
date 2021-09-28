package ru.comavp.testspringproject;

import java.util.ArrayList;
import java.util.List;

public interface Music {
    List<String> songList = new ArrayList<>();
    default String getSong(final Integer songIndex) {
        String result = "";
        if (songIndex >= 0 && songIndex < songList.size()) {
            result = songList.get(songIndex);
        } else {
            result = "There is not song with such index";
        }
        return result;
    }

    default int getSongListSize() {
        return songList.size();
    }
}
