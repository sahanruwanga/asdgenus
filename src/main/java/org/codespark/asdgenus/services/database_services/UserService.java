package org.codespark.asdgenus.services.database_services;

import org.codespark.asdgenus.models.User;
import org.codespark.asdgenus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setSubjects(null);
        this.getUserRepository().save(user);
    }

    public User confirmUserPresent() {

        if (!userRepository.findById(1).isPresent()) {
            User user = new User();
            user.setName("TestName");
            user.setEmail("test@email.com");
            user.setPassword("TestPassword");
            userRepository.save(user);
            return user;
        }
        else
            return userRepository.findById(1).get();
    }

    public void getUser() {
    }

    public void updateUser() {
    }

    public void deleteUser() {
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
