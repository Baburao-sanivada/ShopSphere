package com.backend.ShopSphere.SignIn;

import com.backend.ShopSphere.CommonUtility.JsonUtil;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user){
        Boolean userExists = userService.findIfUserAlreadyExists(user.getEmail());
        if(userExists){
            return new ResponseEntity<>("User Already Exits",HttpStatus.BAD_REQUEST);
        }
        userService.saveUser(user);
        //create Json Token
        String jwtToken = JsonUtil.generateToken(user.getEmail());
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        User userDetails = userService.findIfUserExists(user.getEmail());
        if(userDetails != null){
            //check password
            if(userDetails.getPassword().equals(user.getPassword())){
                String jwtToken = JsonUtil.generateToken(user.getEmail());
                return new ResponseEntity<>(jwtToken, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Invalid User credentials",HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Invalid User credentials",HttpStatus.BAD_REQUEST);
    }
}
