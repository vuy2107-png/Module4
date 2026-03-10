package com.codegym.musicapp.controller;

import com.codegym.musicapp.model.Music;
import com.codegym.musicapp.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping("/musics")
public class MusicController {
    @Autowired
    private MusicService musicService;

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("musics", musicService.findAll());
        return "list";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("music", new Music());
        return "create";
    }

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("music", musicService.findById(id));
        return "edit";
    }

    @PostMapping("/update")
    public String update(Music music) {
        musicService.save(music);
        return "redirect:/musics";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        musicService.delete(id);
        return "redirect:/musics";
    }

    @PostMapping("/create")
    public String create(Music music, @RequestParam("file")MultipartFile file) {
        String fileName = file.getOriginalFilename();

        try {
            file.transferTo(new File("D:/music/" + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        music.setFilePath(fileName);

        musicService.save(music);

        return "redirect:/musics";
    }
}
