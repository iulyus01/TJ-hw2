package com.test.filters;

import com.test.Utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class DecorationFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, wrapper);

        String modifiedContent = addPreludeAndCoda(wrapper.toString());

        response.getWriter().write(modifiedContent);
    }

    private String addPreludeAndCoda(String originalContent) {
        String prelude = Utils.envelopIntoTag("This is the prelude.", "span");
        String coda = Utils.envelopIntoTag("This is the coda.", "span");

        return prelude + originalContent + coda;
    }
}
