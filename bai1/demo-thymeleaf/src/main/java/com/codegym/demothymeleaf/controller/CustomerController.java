package com.codegym.demothymeleaf.controller;

import com.codegym.demothymeleaf.model.Customer;
import com.codegym.demothymeleaf.service.CustomerService;
import com.codegym.demothymeleaf.service.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    // Khởi tạo Service (Lưu ý: Trong thực tế nên dùng Dependency Injection (@Autowired))
    private final ICustomerService customerService = new CustomerService();

    // 1. READ (Hiển thị danh sách)
    // Ánh xạ tới: /customers
    @GetMapping("") // ĐÃ SỬA: Bổ sung @GetMapping để ánh xạ tới URL gốc /customers
    public String index(Model model) {
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customers", customerList);
        return "/index"; // Trả về view: /WEB-INF/views/index.html (hoặc index.jsp)
    }

    // 2. CREATE (Hiển thị Form thêm mới)
    // Ánh xạ tới: /customers/create
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "/create"; // Trả về view: /WEB-INF/views/create.html
    }

    // 3. CREATE (Xử lý lưu dữ liệu)
    // Ánh xạ tới: POST /customers/save
    @PostMapping("/save")
    public String save(Customer customer, RedirectAttributes redirect) {
        // Gán ID ngẫu nhiên (chỉ dùng trong môi trường ví dụ)
        customer.setId((int) (Math.random() * 10000));
        customerService.save(customer);
        redirect.addFlashAttribute("success", "Added customer successfully!");
        return "redirect:/customers"; // Chuyển hướng về trang danh sách
    }

    // 4. UPDATE (Hiển thị Form chỉnh sửa)
    // Ánh xạ tới: /customers/{id}/edit
    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/update"; // Trả về view: /WEB-INF/views/update.html
    }

    // 5. UPDATE (Xử lý lưu dữ liệu đã chỉnh sửa)
    // Ánh xạ tới: POST /customers/update
    @PostMapping("/update")
    public String update(Customer customer, RedirectAttributes redirect) {
        customerService.update(customer.getId(), customer);
        redirect.addFlashAttribute("success", "Updated customer successfully!");
        return "redirect:/customers"; // Chuyển hướng về trang danh sách
    }

    // 6. DELETE (Hiển thị Form xác nhận xóa)
    // Ánh xạ tới: /customers/{id}/delete
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/delete"; // Trả về view: /WEB-INF/views/delete.html
    }

    // 7. DELETE (Xử lý xóa dữ liệu)
    // Ánh xạ tới: POST /customers/delete
    @PostMapping("/delete")
    public String delete(Customer customer, RedirectAttributes redirect) {
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/customers"; // Chuyển hướng về trang danh sách
    }

    // 8. VIEW (Xem chi tiết)
    // Ánh xạ tới: /customers/{id}/view
    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/view"; // Trả về view: /WEB-INF/views/view.html
    }
}