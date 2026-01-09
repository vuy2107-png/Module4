package com.codegym.springformtutorial.controller;

import com.codegym.springformtutorial.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    /**
     * Xử lý yêu cầu GET /employee/show-form
     * Mục đích: Hiển thị form nhập liệu.
     */
    @GetMapping("/show-form")
    public String showForm(Model model) {
        // Tạo một đối tượng Employee rỗng và đưa vào Model
        // Đối tượng này được liên kết với form:form (modelAttribute="employee") trong create.jsp
        model.addAttribute("employee", new Employee());
        return "/employee/create"; // Trả về view: /WEB-INF/views/employee/create.jsp
    }

    /**
     * Xử lý yêu cầu POST /employee/add-employee
     * Mục đích: Nhận dữ liệu từ form và hiển thị kết quả.
     */
    @PostMapping("/add-employee")
    public String submit(@ModelAttribute("employee") Employee employee, Model model) {
        // @ModelAttribute tự động nhận và gán dữ liệu từ form vào đối tượng 'employee'

        // Đưa các thuộc tính của đối tượng Employee đã được bind vào Model
        // để view 'info.jsp' có thể truy cập bằng EL (${name}, ${id},...)
        model.addAttribute("name", employee.getName());
        model.addAttribute("contactNumber", employee.getContactNumber());
        model.addAttribute("id", employee.getId());

        return "/employee/info"; // Trả về view: /WEB-INF/views/employee/info.jsp
    }
}