package com.codegym.caculatorspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("a") double a,
            @RequestParam("b") double b,
            @RequestParam("operator") String operator,
            Model model) {

        double result = 0;

        switch (operator) {
            case "add": result = a + b; break;
            case "sub": result = a - b; break;
            case "mul": result = a * b; break;
            case "div":
                if (b != 0) {
                    result = a / b;
                } else {
                    model.addAttribute("error", "Không thể chia cho 0");
                    return "result";
                }
                break;
        }

        model.addAttribute("result", result);
        return "result";
    }
}
