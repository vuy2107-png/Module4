package com.example.blogapp.controller;

import com.example.blogapp.model.Blog;
import com.example.blogapp.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    // LIST
    @GetMapping
    public String list(Model model) {
        model.addAttribute("blogs", blogService.findAll());
        return "blog/list";
    }

    // CREATE FORM
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog/create";
    }

    // SAVE (CREATE)
    @PostMapping("/save")
    public String save(@ModelAttribute Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
    }

    // VIEW DETAIL
    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.findById(id));
        return "blog/view";
    }

    // EDIT FORM
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.findById(id));
        return "blog/edit";
    }

    // UPDATE
    @PostMapping("/update")
    public String update(@ModelAttribute Blog blog) {
        blogService.save(blog); // save() dùng chung cho create & update
        return "redirect:/blogs";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        blogService.deleteById(id);
        return "redirect:/blogs";
    }
}
