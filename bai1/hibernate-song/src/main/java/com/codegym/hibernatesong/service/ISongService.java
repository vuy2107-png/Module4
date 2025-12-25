package com.codegym.hibernatesong.service;

import com.codegym.hibernatesong.model.Song;
import java.util.List;

public interface ISongService {
    List<Song> findAll();
    void save(Song song);
    Song findById(Long id);
    void remove(Long id);
}
