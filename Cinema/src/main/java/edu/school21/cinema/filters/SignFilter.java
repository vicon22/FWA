package edu.school21.cinema.filters;


import edu.school21.cinema.models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(servletNames = {"SignUp", "SignIn"})
public class SignFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        User user = (User) req.getSession().getAttribute("user");

        System.out.println(user);
        if (user != null)
        {
            req.setAttribute("user", user);
            resp.sendRedirect("/profile");
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
