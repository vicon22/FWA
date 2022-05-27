package edu.school21.cinema.servlets;


import edu.school21.cinema.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.Writer;

@WebServlet(value = {"/images"}, name = "Images")
public class ImageServlets extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Writer writer = resp.getWriter();
       writer.write("------\n");
//       writer.close();

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        writer.write(user.toString());
//        req.getC
        resp.setContentType("text/jsp");

        writer.write("((((\n");
        Part filePart = req.getPart("file");
        writer.write("))))\n");
        writer.write("+++++\n");
        System.out.println(filePart.toString());
        writer.write("+++++\n");
        String pathToPicture = "/Users/eveiled/Desktop/FWA-clone/Cinema/src/main/webapp/images/";
        File pathToPic = new File(pathToPicture + user.getEmail());

        String fileName = filePart.getSubmittedFileName();
        System.out.println("fileName: " + fileName);
        try{
            for (Part part : req.getParts()) {
                part.write(pathToPic + File.separator + fileName);
            }
        } catch (Exception ignored){}
//        File file = (File) session.getAttribute("image");
//        writer.write(file.toString());
        writer.write("------\n");

//        resp.sendRedirect("/profile");

    }
}
