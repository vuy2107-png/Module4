package com.codegym.hibernatesong.service;

import com.codegym.hibernatesong.model.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Lớp triển khai Service sử dụng chuẩn JPA (EntityManager) và quản lý giao dịch
 * tự động bởi Spring (@Transactional).
 */
@Service
@Transactional // Đặt ở cấp độ lớp, áp dụng cho tất cả các phương thức CUD (Create, Update, Delete)
public class SongService implements ISongService {

    // Yêu cầu Spring tiêm EntityManager vào đây, loại bỏ lỗi NullPointerException
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true) // Chỉ là thao tác đọc, tối ưu hóa giao dịch
    public List<Song> findAll() {
        // Sử dụng JPQL (JPA Query Language)
        return entityManager.createQuery("SELECT s FROM Song s", Song.class).getResultList();
    }

    @Override
    public void save(Song song) {
        // Spring sẽ tự động bắt đầu/kết thúc giao dịch cho phương thức này
        if (song.getId() == null) {
            // Thêm mới
            entityManager.persist(song);
        } else {
            // Cập nhật (Merge)
            entityManager.merge(song);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Song findById(Long id) {
        // Phương thức find chuẩn của JPA để truy vấn theo khóa chính
        return entityManager.find(Song.class, id);
    }

    @Override
    public void remove(Long id) {
        // 1. Tìm đối tượng cần xóa
        Song song = findById(id);

        if (song != null) {
            // 2. Để xóa một đối tượng, nó phải nằm trong Persistence Context (managed).
            // Dùng entityManager.merge() để đảm bảo đối tượng được quản lý trước khi xóa.
            entityManager.remove(entityManager.merge(song));
        }
    }
}