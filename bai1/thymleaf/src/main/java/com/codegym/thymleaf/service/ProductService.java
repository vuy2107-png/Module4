package com.codegym.thymleaf.service;

import com.codegym.thymleaf.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService implements IProductService {

    private static final Map<Integer, Product> products;

    static {
        products = new HashMap<>();
        products.put(1, new Product(1, "Laptop XYZ", 1200.0, "Laptop cao cấp", "Dell"));
        products.put(2, new Product(2, "Điện thoại A", 800.0, "Điện thoại tầm trung", "Samsung"));
        products.put(3, new Product(3, "Bàn phím cơ", 150.0, "Bàn phím cơ Blue Switch", "Logitech"));
    }

    @Override
    public List<Product> findAll() { return new ArrayList<>(products.values()); }

    @Override
    public void save(Product product) { products.put(product.getId(), product); }

    @Override
    public Product findById(int id) { return products.get(id); }

    @Override
    public void update(int id, Product product) { products.put(id, product); }

    @Override
    public void remove(int id) { products.remove(id); }

    @Override
    public List<Product> findByName(String keyword) {
        List<Product> result = new ArrayList<>();
        if (keyword == null || keyword.isEmpty()) return findAll();
        String lowerCaseKeyword = keyword.toLowerCase();
        for (Product product : products.values()) {
            if (product.getName().toLowerCase().contains(lowerCaseKeyword)) {
                result.add(product);
            }
        }
        return result;
    }
}