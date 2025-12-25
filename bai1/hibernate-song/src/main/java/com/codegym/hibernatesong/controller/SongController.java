package com.codegym.hibernatesong.controller;

import com.codegym.hibernatesong.model.Song;
import com.codegym.hibernatesong.model.SongForm;
import com.codegym.hibernatesong.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.UUID; // Dùng để tạo tên file duy nhất

@Controller
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private ISongService songService;

    // ĐƯỜNG DẪN LƯU FILE: THAY THẾ BẰNG THƯ MỤC CỦA BẠN TRÊN MÁY TÍNH
    // Phải khớp với đường dẫn đã cấu hình trong AppConfig.java
    private final String FILE_UPLOAD_PATH = "C:\\upload\\music_app_files\\";

    // --- 1. HIỂN THỊ DANH SÁCH (READ) ---
    // Mapping "/" sẽ được xử lý bởi @RequestMapping("/songs")
    @GetMapping
    public ModelAndView listSongs(@ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView("/song/list"); // Tên view JSP/Thymeleaf
        modelAndView.addObject("songs", songService.findAll());
        // Hiển thị thông báo (nếu có từ RedirectAttributes)
        if (message != null && !message.isEmpty()) {
            modelAndView.addObject("message", message);
        }
        return modelAndView;
    }

    // --- 2. TẠO BÀI HÁT MỚI (CREATE) - FORM GET ---
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/song/create");
        modelAndView.addObject("songForm", new SongForm());
        return modelAndView;
    }

    // --- TẠO BÀI HÁT MỚI (CREATE) - XỬ LÝ POST (Upload) ---
    @PostMapping("/create")
    public String createSong(@ModelAttribute("songForm") SongForm songForm, RedirectAttributes redirect) {
        MultipartFile file = songForm.getFile();

        if (file == null || file.isEmpty() || file.getOriginalFilename().isEmpty()) {
            redirect.addFlashAttribute("message", "Vui lòng chọn file nhạc!");
            return "redirect:/songs/create";
        }

        // Tạo tên file duy nhất: UUID + phần mở rộng (extension)
        String originalFileName = file.getOriginalFilename();
        String fileExtension = "";
        int lastDotIndex = originalFileName.lastIndexOf(".");
        if (lastDotIndex > 0) {
            fileExtension = originalFileName.substring(lastDotIndex);
        }
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

        try {
            // Lưu file vào thư mục vật lý trên Server
            FileCopyUtils.copy(file.getBytes(), new File(FILE_UPLOAD_PATH + uniqueFileName));
        } catch (IOException e) {
            e.printStackTrace();
            redirect.addFlashAttribute("message", "Lỗi upload file: " + e.getMessage());
            return "redirect:/songs/create";
        }

        // Tạo Entity và lưu thông tin vào Database
        Song song = new Song(
                songForm.getName(),
                songForm.getArtist(),
                songForm.getGenre(),
                uniqueFileName // Lưu tên file duy nhất
        );
        songService.save(song);

        redirect.addFlashAttribute("message", "Tạo bài hát thành công!");
        return "redirect:/songs";
    }

    // --- 3. CẬP NHẬT (UPDATE) - FORM GET ---
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Song song = songService.findById(id);
        if (song != null) {
            // Chuyển Entity sang Form DTO để hiển thị
            SongForm songForm = new SongForm(
                    song.getId(),
                    song.getName(),
                    song.getArtist(),
                    song.getGenre(),
                    null // Không load file lên form edit
            );

            ModelAndView modelAndView = new ModelAndView("/song/edit");
            modelAndView.addObject("songForm", songForm);
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }

    // --- CẬP NHẬT (UPDATE) - XỬ LÝ POST ---
    @PostMapping("/edit")
    public String updateSong(@ModelAttribute("songForm") SongForm songForm, RedirectAttributes redirect) {
        Song existingSong = songService.findById(songForm.getId());

        if (existingSong != null) {
            // Chỉ cập nhật thông tin (không xử lý file upload lại trong form này)
            existingSong.setName(songForm.getName());
            existingSong.setArtist(songForm.getArtist());
            existingSong.setGenre(songForm.getGenre());

            songService.save(existingSong);
            redirect.addFlashAttribute("message", "Cập nhật thông tin thành công!");
        } else {
            redirect.addFlashAttribute("message", "Không tìm thấy bài hát để cập nhật.");
        }

        return "redirect:/songs";
    }

    // --- 4. XÓA (DELETE) ---
    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id, RedirectAttributes redirect) {
        Song song = songService.findById(id);
        if (song != null) {
            // Xóa file vật lý trên server trước khi xóa khỏi DB
            File fileToDelete = new File(FILE_UPLOAD_PATH + song.getFilePath());
            if (fileToDelete.exists() && fileToDelete.delete()) {
                System.out.println("Đã xóa file: " + song.getFilePath());
            }

            // Xóa trong database
            songService.remove(id);
            redirect.addFlashAttribute("message", "Xóa bài hát thành công!");
        } else {
            redirect.addFlashAttribute("message", "Không tìm thấy bài hát để xóa.");
        }
        return "redirect:/songs";
    }
}