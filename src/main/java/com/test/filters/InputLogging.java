package com.test.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

public class InputLogging implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Request info:");
        System.out.println("    - url: " + ((HttpServletRequest) servletRequest).getRequestURL());
        System.out.println("    - method: " + ((HttpServletRequest) servletRequest).getMethod());
        System.out.println("    - IP address: " + servletRequest.getRemoteAddr());
        System.out.println("    - user-agent: " + ((HttpServletRequest) servletRequest).getHeader("User-Agent"));
        System.out.println("    - language: " + getLanguage((HttpServletRequest) servletRequest));
        System.out.println("    - parameters:");
        Collections.list(servletRequest.getParameterNames()).forEach((value) -> System.out.println("        - " + value + ": " + servletRequest.getParameter(value)));

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getLanguage(HttpServletRequest req) {
        Enumeration<String> lang = req.getHeaders("Accept-Language");
        if(lang.hasMoreElements()) return lang.nextElement();
        return "could not get language";
    }
}
