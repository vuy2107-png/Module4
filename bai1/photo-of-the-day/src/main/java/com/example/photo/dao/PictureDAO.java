package com.example.photo.dao;

import com.example.photo.model.Picture;
import com.example.photo.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PictureDAO {

    public void save(Picture picture) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(picture);
        em.getTransaction().commit();
        em.close();
    }

    public List<Picture> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Picture> list = em
                .createQuery("SELECT p FROM Picture p", Picture.class)
                .getResultList();
        em.close();
        return list;
    }
}
