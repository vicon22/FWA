package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UsersRepository;
import edu.school21.cinema.services.UsersService;
import edu.school21.cinema.services.UsersServiceImpl;
import org.apache.commons.io.FileUtils;
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
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

@WebServlet(value = {"/profile"}, name = "Profile")
public class ProfileServlets extends HttpServlet {

    private UsersService usersService;
    private PasswordEncoder passwordEncoder;
    private UsersRepository usersRepository;
    private String uploadPath;

    @Override
    public void init(ServletConfig config) {

        ServletContext servletContext = config.getServletContext();
        ApplicationContext context = (ApplicationContext)servletContext.getAttribute("springContext");
        usersService = context.getBean(UsersServiceImpl.class);
        passwordEncoder = context.getBean(PasswordEncoder.class);
        usersRepository = context.getBean(UsersRepository.class);
        uploadPath = context.getBean(String.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        File imagesDir = new File(uploadPath + user.getEmail());
        if (!imagesDir.exists()) {
//            System.out.println("none exists");
            imagesDir.mkdir();
        }
//        System.out.println(imagesDir.getAbsolutePath());
        req.setAttribute("uploadPath", uploadPath + user.getEmail());
        File image = new File(uploadPath + user.getEmail());
        for (File file : image.listFiles())
            if (file.getName().contains("DS_Store"))
                file.delete();
        if ((image.listFiles().length != 0)) {
            File[] files = image.listFiles();
            Arrays.sort(files, (f1, f2) -> Long.valueOf(f1.lastModified()).compareTo(f2.lastModified()));
            byte[] fileContent = FileUtils.readFileToByteArray(files[files.length - 1]);
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            req.setAttribute("image", encodedString);
        }

        req.setAttribute("auths", user.getSessionList());
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       Writer writer = resp.getWriter();
//       writer.write("------\n");
//
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user");
//        writer.write(user.toString());
//        resp.setContentType("text/jsp");
//
//        writer.write("((((\n");
//        File filePart = (File) session.getAttribute("image");
//        writer.write(String.valueOf(filePart.exists()));
//        writer.write("))))\n");
//        writer.write("+++++\n");
//        System.out.println(filePart.toString());
//        writer.write("+++++\n");
//        String pathToPicture = "/Users/eveiled/Desktop/FWA-clone/Cinema/src/main/webapp/images/";
//        File pathToPic = new File(pathToPicture + user.getEmail());
//
////        File file = (File) session.getAttribute("image");
////        writer.write(file.toString());
//        writer.write("------\n");
        req.getRequestDispatcher("/WEB-INF/html/plug.html").forward(req,resp);

    }

}
