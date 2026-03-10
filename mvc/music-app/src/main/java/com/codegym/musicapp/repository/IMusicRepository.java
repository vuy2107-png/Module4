package com.codegym.musicapp.repository;

import com.codegym.musicapp.model.Music;

import java.util.List;

public interface IMusicRepository {
    List<Music> findAll();
    Music findById(int id);
    void save(Music music);
    void delete(int id);
}
