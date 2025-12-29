package com.example.photo.servlet;

import com.example.photo.dao.PictureDAO;
import com.example.photo.model.Picture;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/picture")
public class PictureServlet extends HttpServlet {

    private final PictureDAO pictureDAO = new PictureDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Picture> pictures = pictureDAO.findAll();
        req.setAttribute("pictures", pictures);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String author = req.getParameter("author");
        String url = req.getParameter("url");

        Picture picture = new Picture(author, url, 0);
        pictureDAO.save(picture);

        resp.sendRedirect(req.getContextPath() + "/picture");
    }
}
