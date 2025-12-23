package com.codegym.thymleaf.controller;

import com.codegym.thymleaf.model.Product;
import com.codegym.thymleaf.service.IProductService;
import com.codegym.thymleaf.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService = new ProductService();

    // READ ALL & SEARCH
    @GetMapping({"", "/search"})
    public String index(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Product> productList;
        if (keyword != null && !keyword.isEmpty()) {
            productList = productService.findByName(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            productList = productService.findAll();
        }
        model.addAttribute("products", productList);
        return "/index";
    }

    // CREATE (Hiển thị form)
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "/create";
    }

    // CREATE (Lưu)
    @PostMapping("/save")
    public String save(Product product, RedirectAttributes redirect) {
        product.setId((int) (Math.random() * 10000));
        productService.save(product);
        redirect.addFlashAttribute("success", "Thêm sản phẩm thành công!");
        return "redirect:/products";
    }

    // UPDATE (Hiển thị form)
    @GetMapping("/{id}/update")
    public String showUpdateForm(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/update";
    }

    // UPDATE (Lưu)
    @PostMapping("/update")
    public String update(Product product, RedirectAttributes redirect) {
        productService.update(product.getId(), product);
        redirect.addFlashAttribute("success", "Cập nhật sản phẩm thành công!");
        return "redirect:/products";
    }

    // DELETE (Hiển thị form)
    @GetMapping("/{id}/delete")
    public String showDeleteForm(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/delete";
    }

    // DELETE (Thực hiện)
    @PostMapping("/delete")
    public String delete(Product product, RedirectAttributes redirect) {
        productService.remove(product.getId());
        redirect.addFlashAttribute("success", "Xoá sản phẩm thành công!");
        return "redirect:/products";
    }

    // VIEW (Chi tiết)
    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/view";
    }
}