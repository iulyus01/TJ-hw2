package com.test.web;

import com.test.services.GraphService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig
@WebServlet("/result")
public class Result extends HttpServlet {

    private GraphService graphService;

    @Override
    public void init() throws ServletException {
        super.init();
        graphService = GraphService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        Part filePart;
        try {
            filePart = req.getPart("file");
            String text = new String(filePart.getInputStream().readAllBytes());

            req.setAttribute("output", graphService.handleTextGraph(text));

            req.getRequestDispatcher("/result.jsp").forward(req, resp);
        } catch(ServletException e) {
            e.printStackTrace();
        }
    }
}
