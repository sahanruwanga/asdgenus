package org.codespark.asdgenus.services.database_services;

import org.codespark.asdgenus.dtos.UserDTO;
import org.codespark.asdgenus.models.User;
import org.codespark.asdgenus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public int registerUser(UserDTO userDTO) {

            User user = new User();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(getHashedPassword(userDTO.getPassword()));
            return getUserRepository().save(user).getId();
    }

    public UserDTO loginUser(UserDTO userDTO) {

        User user = verifyUser(userDTO);
        if (user == null)
            return null;
        else {
            return new UserDTO(user.getId(), user.getName(), user.getEmail(), null);
        }
    }

    private User verifyUser(UserDTO userDTO) {

        User user = userRepository.findByEmail(userDTO.getEmail());
        if (user != null) {
            if (getHashedPassword(userDTO.getPassword()).equals(user.getPassword()))
                return user;
            else
                return null;
        }
        return null;
    }

    private String getHashedPassword(String password) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
        }
        md.update(password.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public int getUserByEmail(String email) {

        return userRepository.findByEmail(email).getId();
    }

    public UserDTO getUserById(int uid){

        User user = userRepository.findById(uid).get();
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), null);
    }

    public void getUserByEmail() {
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
