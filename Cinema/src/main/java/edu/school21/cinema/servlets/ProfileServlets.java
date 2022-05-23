package edu.school21.cinema.servlets;

import edu.school21.cinema.config.ServletsApplicationConfig;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UsersRepository;
import edu.school21.cinema.services.UsersService;
import edu.school21.cinema.services.UsersServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/profile")
public class ProfileServlets extends HttpServlet {

    private UsersService usersService;
    private PasswordEncoder passwordEncoder;
    private UsersRepository usersRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {

        ApplicationContext context = new AnnotationConfigApplicationContext(ServletsApplicationConfig.class);
        usersService = context.getBean(UsersServiceImpl.class);
        passwordEncoder = context.getBean(PasswordEncoder.class);
        usersRepository = context.getBean(UsersRepository.class);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        PrintWriter printWriter = resp.getWriter();
//        printWriter.write("signIn!");
//        printWriter.close();

         User user = (User) req.getSession().getAttribute("user");
         if (user == null) {
             resp.setStatus(403);
             req.getRequestDispatcher("/WEB-INF/html/forbidden.html").forward(req,resp);
         }
         else {
             PrintWriter out = resp.getWriter();
             out.println(user.toString());
             req.setAttribute("user", user);
             req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);
         }


//        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req,resp);
    }

}
