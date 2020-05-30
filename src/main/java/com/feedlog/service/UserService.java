package com.feedlog.service;

import com.feedlog.domain.User;
import com.feedlog.exceptions.UserNotFound;
import com.feedlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository repo;
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.repo = userRepository;
    }

    public List<User> findAllUsers() {
        return repo.findAll();
    }

    public User findById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new UserNotFound("user not found"));
    }


    public User findByUserName(String userName) {
        User user = repo.findByUsername(userName);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFound("User Not found with country code " + userName);
        }
    }

    public User addUser(User user) {
        user.setRoles("USER");
        user.setEnabled(true);
        String encryptPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptPassword);
        return repo.save(user);
    }

    public User replaceUser(User newUser, int id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new UserNotFound("User not found with id " + id));
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setRoles(newUser.getRoles());
        user.setUserProfile(newUser.getUserProfile());
        repo.save(user);
        return user;
    }

    public void deleteUser(int id) {
        repo.deleteById(id);
    }


}
