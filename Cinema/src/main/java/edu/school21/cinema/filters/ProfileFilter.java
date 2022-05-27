package edu.school21.cinema.filters;


import edu.school21.cinema.models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(servletNames = {"Profile"})
public class ProfileFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
         if (user == null) {
             resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
             req.getRequestDispatcher("/WEB-INF/html/forbidden.html").forward(req, resp);
//             resp.sendError(HttpServletResponse.SC_FORBIDDEN);
             return;
         }
         filterChain.doFilter(servletRequest,servletResponse);
    }
}
