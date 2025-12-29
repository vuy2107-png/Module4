package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CurrencyController {

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/convert")
    public String convert(
            @RequestParam("rate") double rate,
            @RequestParam("usd") double usd,
            Model model) {

        double vnd = rate * usd;
        model.addAttribute("result", vnd);

        return "result";
    }
}
