package edu.school21.cinema.services;

public interface UsersService {

    String signUp(String email, String firstName, String lastName, String phone, String password);
    boolean signIn(String email, String password);
}