package com.codegym.formbinding.controller;

import com.codegym.formbinding.model.MailboxConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MailboxController {
    // Dữ liệu cấu hình mặc định
    private MailboxConfig currentConfig =
            new MailboxConfig("English", 10, true, "King regards, Mail System");
    // Dữ liệu tĩnh cho form
    private final String[] languages = {"English", "Vietnamese", "Japanese", "Chinese"};
    private final int[] pageSizes = {5, 10, 15, 25, 50, 100};
    // 1. Hiển thị Form cấu hình
    @GetMapping("/settings")
    public ModelAndView showSettings(Model model) {
        ModelAndView modelAndView = new ModelAndView("settings/form");

        modelAndView.addObject("config", currentConfig);

        model.addAttribute("languages", languages);
        model.addAttribute("pageSizes", pageSizes);

        return modelAndView;
    }

    // 2.Xử lý Form khi Submit
    @PostMapping("/settings")
    public ModelAndView updateSettings(@ModelAttribute("config") MailboxConfig updateConfig) {
        this.currentConfig = updateConfig;

        ModelAndView modelAndView = new ModelAndView("settings/result");
        modelAndView.addObject("config", updateConfig);

        return modelAndView;
    }
}
