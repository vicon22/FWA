package edu.school21.cinema;

import edu.school21.cinema.config.ServletsApplicationConfig;
import edu.school21.cinema.models.Session;
import edu.school21.cinema.repositories.UsersRepository;
import edu.school21.cinema.repositories.UsersRepositoryImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ServletsApplicationConfig.class);
        UsersRepository usersRepository = context.getBean(UsersRepositoryImpl.class);
        String str = usersRepository.findByEmail("john@gmail.com").get().toString();
        System.out.println(str);

        Session session1 = new Session("john@gmail.com","1");
        Session session2 = new Session("john@gmail.com","1");

        System.out.println(session1.getEmail());

        usersRepository.saveSession(session1);
        usersRepository.saveSession(session2);

        System.out.println(session1);
        System.out.println(session2);
        System.out.println(usersRepository.findSessions("john@gmail.com"));
    }
}
