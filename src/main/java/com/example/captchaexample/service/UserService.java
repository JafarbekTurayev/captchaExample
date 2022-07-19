package com.example.captchaexample.service;

import com.example.captchaexample.entity.User;
import com.example.captchaexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    @Autowired
    private UserRepository repo;

    public void createUser(User user) {
        repo.save(user);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public Optional<User> getOneUser(Integer id) {
        return repo.findById(id);
    }

}
