package edu.school21.cinema.repositories;


import com.zaxxer.hikari.HikariDataSource;
import edu.school21.cinema.models.Session;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Component
@ComponentScan(basePackages = {"edu.school21.cinema"})
public class UsersRepositoryImpl implements UsersRepository {

    DriverManagerDataSource dataSource;
    private final String tableName = "users";
    private final JdbcTemplate jdbcTemplate;

    RowMapper<User> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {

        String email = resultSet.getString("email");

        return new User(
                email,
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("phone"),
                resultSet.getString("password"),
                findSessions(email));
    };

    RowMapper<Session> SESSION_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Session(
                Integer.parseInt(resultSet.getString("session_id")),
                resultSet.getString("user_email"),
                resultSet.getString("ip"),
                resultSet.getString("date"),
                resultSet.getString("time"));
    };

    @Autowired
    public UsersRepositoryImpl(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public UsersRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(format("SELECT * FROM %s", tableName), ROW_MAPPER);
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(format("INSERT INTO %s (email, firstName, lastName, phone, password) VALUES ('%s', '%s', '%s', '%s', '%s')", tableName, entity.getEmail(), entity.getFirstName(), entity.getLastName(), entity.getPhone(), entity.getPassword()));
    }

    @Override
    public void saveSession(Session session) {
        jdbcTemplate.update(format("INSERT INTO %s (user_email, ip, date, time) VALUES ('%s', '%s', '%s', '%s')", "sessions", session.getEmail(), session.getIp(), session.getDate(), session.getTime()));
    }

    @Override
    public List<Session> findSessions(String email) {
        return jdbcTemplate.query(format("SELECT * FROM %s WHERE user_email = '%s'", "sessions", email), SESSION_MAPPER);
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(format("UPDATE %s SET firstName = %s, lastName = %s, phone = %s, password = %s WHERE email = '%s'", tableName, entity.getFirstName(), entity.getLastName(), entity.getPhone(), entity.getPassword(), entity.getEmail()));
    }

    @Override
    public void delete(String email) {
        jdbcTemplate.update(format("DELETE FROM %s WHERE email = %s", tableName, email));
    }

    @Override
    public Optional<User> findByEmail(String email) {

        User user = null;
        List<User> userList = jdbcTemplate.query(format("SELECT * FROM %s WHERE email = '%s'", tableName, email), ROW_MAPPER);
        if (userList.size() != 0){
            user = userList.get(0);
        }
        return Optional.ofNullable(user);
    }
}

