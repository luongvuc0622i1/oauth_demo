package com.example.demooauth2.service.impl;

import com.example.demooauth2.model.User;
import com.example.demooauth2.repository.UserRepository;
import com.example.demooauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean notExistEmail(String email) {
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void changePassword(User u, String password) {
        u.setPassword(new BCryptPasswordEncoder(10).encode(password));
        userRepository.save(u);
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
