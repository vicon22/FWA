package edu.school21.cinema.servlets;

import edu.school21.cinema.config.ServletsApplicationConfig;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UsersRepository;
import edu.school21.cinema.repositories.UsersRepositoryImpl;
import edu.school21.cinema.services.UsersService;
import edu.school21.cinema.services.UsersServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/users")
@ComponentScan(basePackages = {"edu.school21.cinema"})
public class UsersServlet extends HttpServlet {

    private UsersService usersService;
    private UsersRepository usersRepository;


    @Override
    public void init ( ServletConfig config ) {;

        ApplicationContext context = new AnnotationConfigApplicationContext(ServletsApplicationConfig.class);
        usersService = context.getBean(UsersServiceImpl.class);
        usersRepository = context.getBean(UsersRepositoryImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        List<User> list = usersRepository.findAll();

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        writer.println("<table>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<th>Email</th>\n" +
                "<th>FirstName</th>\n" +
                "<th>LastName</th>\n" +
                "<th>Phone</th>\n" +
                "<th>Password</th>\n" +
                "</tr>");

        for (User user1 : list) {
            writer.println("<tr>");
            writer.println("<td>" + user1.getEmail() + "</td>");
            writer.println("<td>" + user1.getFirstName() + "</td>");
            writer.println("<td>" + user1.getLastName() + "</td>");
            writer.println("<td>" + user1.getPhone() + "</td>");
            writer.println("<td>" + user1.getPassword() + "</td>");
            writer.println("</tr>");
        }

        writer.println("</tbody\n" +
                "</table>");

        writer.close();
    }
}
