package ru.kata.spring.boot_security.demo.Service;



import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
public interface UserService {
    void addUser(User user);

    void removeUserById(int id);

    List<User> getAllUsers();

    User getUser(int id);

    void updateUser(int id, User user);

}
