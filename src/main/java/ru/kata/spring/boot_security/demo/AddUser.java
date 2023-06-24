package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.Service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.Service.UserServiceImpl;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AddUser implements CommandLineRunner {
    private RoleServiceImp roleServiceImp;
    private UserServiceImpl userServiceImp;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AddUser(RoleServiceImp roleServiceImp, UserServiceImpl userServiceImp, PasswordEncoder passwordEncoder) {
        this.roleServiceImp = roleServiceImp;
        this.userServiceImp = userServiceImp;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        Role userTest = new Role(1L, "ROLE_USER");
        Role adminTest = new Role(2L, "ROLE_ADMIN");
        roleServiceImp.addRole(userTest);
        roleServiceImp.addRole(adminTest);
        Set<Role> userSet = Stream.of(userTest).collect(Collectors.toSet());
        Set<Role> adminSet = Stream.of(adminTest).collect(Collectors.toSet());

        User user = new User("user", "userLastname", 30, passwordEncoder.encode("user"), userSet);
        User admin = new User("admin", "admin", 20, passwordEncoder.encode("admin"), adminSet);
        userServiceImp.addUser(user);
        userServiceImp.addUser(admin);


    }
}
