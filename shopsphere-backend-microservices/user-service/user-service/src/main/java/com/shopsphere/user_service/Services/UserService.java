package com.shopsphere.user_service.Services;

import com.shopsphere.user_service.DTO.SignUpDto;
import com.shopsphere.user_service.Models.User;
import com.shopsphere.user_service.Respository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;


import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Boolean findIfUserAlreadyExists(String email) {
        Optional<User> userData = userRepo.findById(email);
        if(userData.isEmpty()) return false;
        return true;
    }

    public void saveUser(SignUpDto userDto) {
        User user = getUserFromUserDto(userDto);
        userRepo.save(user);


    }

    private User getUserFromUserDto(SignUpDto userDto) {
        User user = new User(userDto.getUsername(), userDto.getEmail(), userDto.getPassword());
        return user;
    }

    public User findIfUserExists(String email) {
        Optional<User> userData = userRepo.findById(email);
        if(userData.isEmpty()) return null;
        return userData.get();
    }

    public Optional<User> getUserWithEmail(String email) {
        return userRepo.findById(email);
    }

    public void updateUser(User updatedUser) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(updatedUser.getEmail()));
        Update update = new Update()
                .set("name", updatedUser.getUsername())
                .set("password", updatedUser.getPassword())
                .set("cartData", updatedUser.getCartData());

        mongoTemplate.updateFirst(query, update, User.class);
    }
}

