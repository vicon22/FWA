package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {

    Optional<User> findByEmail(String name);
    void saveSession(Session session);
    List<Session> findSessions(String email);
}


