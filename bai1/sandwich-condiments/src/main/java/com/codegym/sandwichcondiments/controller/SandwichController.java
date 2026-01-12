package com.codegym.sandwichcondiments.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class SandwichController {

    @RequestMapping("/")
    public String showForm() {
        return "index";
    }

    @RequestMapping("/save")
    public String save(@RequestParam(value = "condiment", required = false) String[] condiment, Model model) {
        model.addAttribute("condiment", condiment);
        return "result";
    }
}