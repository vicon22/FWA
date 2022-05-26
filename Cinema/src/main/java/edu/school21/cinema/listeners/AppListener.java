package edu.school21.cinema.listeners;


import edu.school21.cinema.config.ServletsApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext servletContext = sce.getServletContext();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ServletsApplicationConfig.class);
        servletContext.setAttribute("springContext", applicationContext);
    }
}
