package edu.school21.cinema.services;

import java.util.Optional;

public interface UsersService {

    Optional<String> signUp(String email, String firstName, String lastName, String phone, String password);
    boolean signIn(String email, String password);
}