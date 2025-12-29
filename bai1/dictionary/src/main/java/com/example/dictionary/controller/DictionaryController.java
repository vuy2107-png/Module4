package com.example.dictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DictionaryController {

    private static final Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("hello", "xin chào");
        dictionary.put("book", "quyển sách");
        dictionary.put("computer", "máy tính");
        dictionary.put("java", "ngôn ngữ lập trình Java");
    }

    // Trang form tra cứu
    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    // Xử lý tra cứu
    @PostMapping("/search")
    public String search(
            @RequestParam("word") String word,
            Model model) {

        String meaning = dictionary.get(word.toLowerCase());

        if (meaning == null) {
            meaning = "Không tìm thấy từ này";
        }

        model.addAttribute("word", word);
        model.addAttribute("meaning", meaning);

        return "result";
    }
}
