package com.codegym.musicapp.repository;

import com.codegym.musicapp.model.Music;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class MusicRepository implements IMusicRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Music> findAll() {
        return entityManager.createQuery("FROM Music", Music.class).getResultList();
    }

    @Override
    public Music findById(int id) {
        return entityManager.find(Music.class, id);
    }

    @Override
    public void save(Music music) {
        if (music.getId() == 0) {
            entityManager.persist(music);
        } else {
            entityManager.merge(music);
        }
    }

    @Override
    public void delete(int id) {
        Music music = findById(id);
        entityManager.remove(music);
    }
}
