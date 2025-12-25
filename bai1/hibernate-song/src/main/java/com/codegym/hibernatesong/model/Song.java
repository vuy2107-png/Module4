package com.codegym.hibernatesong.model;

import jakarta.persistence.*;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String artist;

    private String genre;

    @Column(nullable = false)
    private String filePath;

    // --- BẮT BUỘC: Constructor rỗng cho JPA/Hibernate (Đã sửa lỗi) ---
    public Song() {
    }

    // Constructor có tham số cho Controller
    public Song(String name, String artist, String genre, String filePath) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.filePath = filePath;
    }

    // --- GETTERS VÀ SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
}