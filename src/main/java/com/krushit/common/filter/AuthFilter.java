package com.nj.common.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI();
        HttpSession session = request.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("currUser") != null);
        boolean isAuthPage = uri.contains("/auth") || uri.contains("/index") || uri.contains("/images");

        if (isLoggedIn || isAuthPage) {
            chain.doFilter(req, res);
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
