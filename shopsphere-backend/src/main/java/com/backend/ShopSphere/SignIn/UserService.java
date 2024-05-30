package com.backend.ShopSphere.SignIn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public Boolean findIfUserAlreadyExists(String email) {
        Optional<User> userData = userRepo.findById(email);
        if(userData.isEmpty()) return false;
        return true;
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public User findIfUserExists(String email) {
        Optional<User> userData = userRepo.findById(email);
        if(userData.isEmpty()) return null;
        return userData.get();
    }
}
