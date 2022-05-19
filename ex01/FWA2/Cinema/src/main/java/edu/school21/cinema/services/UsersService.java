package edu.school21.cinema.services;

import java.util.Optional;
import edu.school21.cinema.models.User;

public interface UsersService {

    Optional<String> signUp(String email, String firstName, String lastName, String phone, String password);
    Optional<User> signIn(String email, String password);
}