package com.codegym.musicapp.service;

import com.codegym.musicapp.model.Music;

import java.util.List;

public interface IMusicService {
    List<Music> findAll();

    Music findById(int id);

    void save(Music music);

    void delete(int id);
}
