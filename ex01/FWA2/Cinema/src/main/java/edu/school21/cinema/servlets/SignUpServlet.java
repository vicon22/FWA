package edu.school21.cinema.servlets;

import edu.school21.cinema.config.ServletsApplicationConfig;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UsersRepository;
import edu.school21.cinema.repositories.UsersRepositoryImpl;
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

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private UsersService usersService;
    private PasswordEncoder passwordEncoder;

    @Override
    public void init(ServletConfig config) throws ServletException {

        ApplicationContext context = new AnnotationConfigApplicationContext(ServletsApplicationConfig.class);
        usersService = context.getBean(UsersServiceImpl.class);
        passwordEncoder = context.getBean(PasswordEncoder.class);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("signUp!");
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        PrintWriter printWriter = resp.getWriter();

        String email = req.getParameter("email");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");
        String password = req.getParameter("psw");


        User user = new User(email,firstName,lastName,phone,password);

        resp.setContentType("text/html");
        printWriter.write("signUp!POOOOST");
        printWriter.write("<br>");
        printWriter.write(user.toString());
        printWriter.write("<br>");
        printWriter.write("<br>");
        String ans = usersService.signUp(email,firstName,lastName,phone,password).orElse("---null---");
        printWriter.write(ans);
        printWriter.write("<br>");
        printWriter.write(user.toString());
        printWriter.close();
    }


}
