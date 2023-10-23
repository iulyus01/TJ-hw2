package com.test.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PropertiesListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();

        System.out.println("server context initialized");

        String dataParameter = context.getInitParameter("data");
        System.out.println("default prop: " + dataParameter);

        context.setAttribute("data", dataParameter);
    }
}
