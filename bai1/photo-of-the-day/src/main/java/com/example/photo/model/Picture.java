package com.example.photo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String url;
    private int likes;

    public Picture() {
    }

    public Picture(String author, String url, int likes) {
        this.author = author;
        this.url = url;
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public int getLikes() {
        return likes;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
