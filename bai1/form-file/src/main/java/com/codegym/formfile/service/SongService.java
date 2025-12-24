package com.codegym.formfile.service;

import com.codegym.formfile.model.Song;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service // Phải có để Spring quét và khởi tạo
public class SongService {
    private final List<Song> uploadedSongs = new ArrayList<>();
    private int nextId = 1;

    public List<Song> findAll() {
        return uploadedSongs;
    }

    public void save(Song song) {
        song.setId(nextId++);
        uploadedSongs.add(song);
    }
}
