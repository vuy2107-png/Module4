package com.codegym.formfile.controller;

import com.codegym.formfile.model.Song;
import com.codegym.formfile.model.SongForm;
import com.codegym.formfile.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private  SongService songService;
    private static final String FILE_UPLOAD_PATH = "C:\\pixel\\";

    private final String[] genres = {"Pop", "Rock", "Jazz", "Rap", "R&B", "Khạc"};

    // 1. Hiển thị Form Upload
    @GetMapping("/upload")
    public ModelAndView showUploadForm() {
        ModelAndView modelAndView = new ModelAndView("song/upload-form");
        modelAndView.addObject("songForm", new SongForm());
        modelAndView.addObject("genres", genres);
        return modelAndView;
    }

    // 2. Xử lý Upload và Validation
    @PostMapping("/upload")
    public String handleFileUpload(@ModelAttribute("songForm") SongForm songForm, RedirectAttributes redirectAttributes){
        MultipartFile multipartFile = songForm.getSongFile();
        String originalFileName = multipartFile.getOriginalFilename();

        // --- BƯỚC 1: KIỂM TRA ĐỊNH DẠNG FILE ---
        if (!isValidFileExtension(originalFileName)) {
            redirectAttributes.addFlashAttribute("message", "Lỗi: Chỉ chấp nhận .mp3, .wav, .ogg, .m4p.");
            return "redirect:/songs/upload"; // Chuyển hướng lại form
        }

        // --- BƯỚC 2: XỬ LÝ LƯU FILE ---
        String fileName = System.currentTimeMillis() + originalFileName; // Tạo tên file duy nhất
        try {
            File file = new File(FILE_UPLOAD_PATH + fileName);
            multipartFile.transferTo(file); // Lưu file vào server

            // --- BƯỚC 3: LƯU THÔNG TIN VÀO SERVICE ---
            Song newSong = new Song();
            newSong.setName(songForm.getName());
            newSong.setArtist(songForm.getArtist());
            newSong.setGenre(songForm.getGenre());
            newSong.setFilePath(fileName); // Lưu tên file đã lưu
            songService.save(newSong);

            redirectAttributes.addFlashAttribute("message", "Upload bài hát thành công: " + originalFileName);
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Lỗi I/O khi lưu file.");
            return "redirect:/songs/upload";
        }

        // --- BƯỚC 4: CHUYỂN HƯỚNG ĐẾN TRANG DANH SÁCH ---
        return "redirect:/songs/list";
    }

    // 3. Hiển thị Danh sách Bài hát
    @GetMapping("/list")
    public ModelAndView showSongList() {
        ModelAndView modelAndView = new ModelAndView("song/list");
        modelAndView.addObject("songs", songService.findAll());
        return modelAndView;
    }

    // Hàm kiểm tra định dạng file
    private boolean isValidFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) return false;
        String lowerCaseFileName = fileName.toLowerCase();
        return lowerCaseFileName.endsWith(".mp3") ||
                lowerCaseFileName.endsWith(".wav") ||
                lowerCaseFileName.endsWith(".ogg") ||
                lowerCaseFileName.endsWith(".m4p");
    }
}
