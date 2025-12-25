package com.example.blogapp.service;

import com.example.blogapp.model.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> findAll();

    Blog findById(Long id);

    void save(Blog blog);

    void deleteById(Long id);
}
