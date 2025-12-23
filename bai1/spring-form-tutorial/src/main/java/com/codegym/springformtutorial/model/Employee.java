package com.codegym.springformtutorial.model;

public class Employee {
    private String id;
    private String name;
    private String contactNumber;

    // Constructor rỗng (Rất quan trọng cho Spring Data Binding)
    public Employee() {
    }

    // Constructor đầy đủ tham số
    public Employee(String id, String name, String contactNumber) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    // =====================================
    // GETTERS (Dùng để hiển thị dữ liệu)
    // =====================================
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    // =====================================
    // SETTERS (Dùng để gán dữ liệu từ Form)
    // =====================================
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}