package edu.school21.cinema.servlets;

import edu.school21.cinema.services.UsersService;
import edu.school21.cinema.services.UsersServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(value = "/signUp", name = "SignUp")
public class SignUpServlet extends HttpServlet {

    private UsersService usersService;
    private PasswordEncoder passwordEncoder;

    @Override
    public void init(ServletConfig config) {

        ServletContext servletContext = config.getServletContext();
        ApplicationContext context = (ApplicationContext)servletContext.getAttribute("springContext");
        usersService = context.getBean(UsersServiceImpl.class);
        passwordEncoder = context.getBean(PasswordEncoder.class);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/html/signUp.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        PrintWriter printWriter = resp.getWriter();
        Optional<String> ans;

        String email = req.getParameter("email");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");
        String password = req.getParameter("psw");

        resp.setContentType("text/html");

        ans = usersService.signUp(email,firstName,lastName,phone,password);
        if (ans.isPresent()) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", null);
            resp.sendRedirect("/signIn"); // Account successfully registered
        } else {
            printWriter.write("<h1>Account with this email already exists</h1>");
        }
        printWriter.write("<div class=\"container signin\">\n" +
                "    <p>Go to <a href=\"/\">start page</a>.</p>\n" +
                "  </div>");
        printWriter.close();
    }


}
