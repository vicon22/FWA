package edu.school21.cinema;

import edu.school21.cinema.config.ServletsApplicationConfig;
import edu.school21.cinema.models.Session;
import edu.school21.cinema.repositories.UsersRepository;
import edu.school21.cinema.repositories.UsersRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        final ApplicationContext context = new AnnotationConfigApplicationContext(ServletsApplicationConfig.class);
        final String DB_SCHEMA = "src/main/resources/sql/schema.sql";
        final String DB_DATA = "src/main/resources/sql/data.sql";
        Connection connection;

        try {
            DriverManagerDataSource dataSource = context.getBean(DriverManagerDataSource.class);
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        runQueriesFromFile(connection, DB_SCHEMA);
        runQueriesFromFile(connection, DB_DATA);

    }

    private static void runQueriesFromFile(Connection connection, String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(
                new File(filename)).useDelimiter(";");
        try {
            while (scanner.hasNext()) {
                connection.createStatement().execute(scanner.next().trim());
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        scanner.close();
    }
}
