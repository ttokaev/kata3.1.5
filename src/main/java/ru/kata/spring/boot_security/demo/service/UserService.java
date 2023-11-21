package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByName(String username);
    void save(User user);
    void delete(Long id);
    void delete(User user);
    void update(User user);

}
