package edu.school21.cinema.servlets;

import edu.school21.cinema.config.ServletsApplicationConfig;
import edu.school21.cinema.models.Session;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UsersRepository;
import edu.school21.cinema.services.UsersService;
import edu.school21.cinema.services.UsersServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
import java.util.List;
import java.util.Optional;

@WebServlet(value = "/signIn", name = "SignIn")
public class SignInServlet extends HttpServlet {

    private UsersService usersService;
    private PasswordEncoder passwordEncoder;
    private UsersRepository usersRepository;

    @Override
    public void init(ServletConfig config) {

        ServletContext servletContext = config.getServletContext();
        ApplicationContext context = (ApplicationContext)servletContext.getAttribute("springContext");
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
        req.getRequestDispatcher("/WEB-INF/html/signIn.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//        PrintWriter printWriter = resp.getWriter();

        String email = req.getParameter("email");
        String password = req.getParameter("psw");

        resp.setContentType("text/html");
        Optional<User> optUser = usersService.signIn(email, password);

        if (optUser.isPresent()) {

            User user = optUser.get();

            HttpSession httpSession = req.getSession();
            usersRepository.saveSession(new Session(user.getEmail(), req.getRemoteAddr()));

            List<Session> sessionList = usersRepository.findSessions(user.getEmail());

            user.setSessionList(sessionList);

            httpSession.setAttribute("user", user);
            resp.sendRedirect("/profile");
//            printWriter.write("<h1>Authentication success</h1>");
        } else {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", null);
            req.getRequestDispatcher("/WEB-INF/html/unsucSignIn.html").forward(req,resp);
        }

//        printWriter.write("<p>Go to <a href=\"/\">start page</a>.\n");
//        printWriter.write("<p>Go to <a href=\"/profile\">profile page</a>.");
//
//        printWriter.close();
    }

}
