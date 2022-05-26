package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ComponentScan(basePackages = {"edu.school21.cinema"})
public class UsersServiceImpl implements UsersService{

    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<String> signUp(String email, String firstName, String lastName, String phone, String password) {

        User user = new User(email, firstName,lastName, phone, passwordEncoder.encode(password), null);

        if (usersRepository.findByEmail(email).isPresent()){
            return Optional.empty();
        } else {
            usersRepository.save(user);
        }
        return Optional.of(user.getPassword());
    }

    @Override
    public Optional<User> signIn(String email, String password) {

        User user = usersRepository.findByEmail(email).orElse(null);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return Optional.of(user);
        }
        return Optional.empty();
    }
}

