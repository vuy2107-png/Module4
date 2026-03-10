package com.codegym.musicapp.service;

import com.codegym.musicapp.model.Music;
import com.codegym.musicapp.repository.IMusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService implements IMusicService{
    @Autowired
    private IMusicRepository repository;

    @Override
    public List<Music> findAll() {
        return repository.findAll();
    }

    @Override
    public Music findById(int id) {
        return repository.findById(id);
    }

    @Override
    public void save(Music music) {
        repository.save(music);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}