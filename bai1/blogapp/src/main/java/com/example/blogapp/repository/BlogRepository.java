package com.example.blogapp.repository;

import com.example.blogapp.model.Blog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class BlogRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Lấy danh sách tất cả blog
    public List<Blog> findAll() {
        return entityManager
                .createQuery("SELECT b FROM Blog b", Blog.class)
                .getResultList();
    }

    // Tìm blog theo id
    public Blog findById(Long id) {
        return entityManager.find(Blog.class, id);
    }

    // Lưu blog (dùng cho CREATE & UPDATE)
    public void save(Blog blog) {
        if (blog.getId() == null) {
            // Create
            entityManager.persist(blog);
        } else {
            // Update
            entityManager.merge(blog);
        }
    }

    // Xóa blog theo id
    public void deleteById(Long id) {
        Blog blog = findById(id);
        if (blog != null) {
            entityManager.remove(blog);
        }
    }
}
