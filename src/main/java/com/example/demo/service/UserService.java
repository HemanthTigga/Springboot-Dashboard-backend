package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.requests.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public User registerUser(@Valid User user){
        return userRepo.save(user);
    }
//    public Boolean loginUser(LoginRequest loginRequest){
    public String loginUser(@Valid LoginRequest loginRequest){
        System.out.println(loginRequest);
        Optional<User> user1 = userRepo.findById(loginRequest.getUserId());
        if(!user1.isPresent()){
            return "null";
        }
        User user= user1.get();
        System.out.println("User details");
        System.out.println(user.getEmail());
        
        System.out.println("Login Request details");
        System.out.println(loginRequest.getUserId());
        System.out.println(loginRequest.getPassword());

        if((!user.getPassword().equals(loginRequest.getPassword())) ||
                (!user.getEmail().equals(loginRequest.getUserId()))){
            return "false";
        }

        return "true";
    }
}
