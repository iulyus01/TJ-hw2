package com.test.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/form")
public class Form extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        ServletContext context = getServletContext();
        System.out.println("data attr: " + context.getAttribute("data"));

        Cookie propertyCookie = new Cookie("tokenCookie", "some-token-here");
        resp.addCookie(propertyCookie);

        try {
            req.getRequestDispatcher("/input.jsp").forward(req, resp);
        } catch(ServletException e) {
            e.printStackTrace();
        }

    }
}
