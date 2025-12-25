package com.codegym.hibernatesong.config;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    // --- Cấu hình Multipart cho Servlet 3.0+ ---
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // Cấu hình giới hạn cho file upload (ví dụ: 50MB)
        int maxUploadSizeInMb = 50 * 1024 * 1024;

        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                "/tmp", // Đường dẫn tạm thời để lưu file (thư mục này phải có quyền ghi)
                maxUploadSizeInMb,
                maxUploadSizeInMb * 2,
                0
        );
        registration.setMultipartConfig(multipartConfigElement);
    }
}