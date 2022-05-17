package edu.school21.cinema.repositories;


import com.zaxxer.hikari.HikariDataSource;
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
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Component
@ComponentScan(basePackages = {"edu.school21.cinema"})
public class UsersRepositoryImpl implements UsersRepository {

    DriverManagerDataSource dataSource;
    private final String tableName = "users";
    private final JdbcTemplate jdbcTemplate;

    RowMapper<User> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new User(
                resultSet.getString("email"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("phone"),
                resultSet.getString("password"));
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

