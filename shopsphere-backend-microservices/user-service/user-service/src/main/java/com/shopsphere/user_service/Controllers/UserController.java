package com.shopsphere.user_service.Controllers;

import com.shopsphere.user_service.DTO.SignUpDto;
import com.shopsphere.user_service.Models.User;
import com.shopsphere.user_service.Services.UserService;
import com.shopsphere.user_service.Util.ApiResponse;
import com.shopsphere.user_service.Util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody SignUpDto userDto){
        Boolean userExists = userService.findIfUserAlreadyExists(userDto.getEmail());
        if(userExists){
            return new ResponseEntity<>(new ApiResponse(userDto.getEmail()+" is already Registered",false),HttpStatus.BAD_REQUEST);
        }
        userService.saveUser(userDto);
        //create Json Token
        String jwtToken = JWTUtil.generateToken(userDto.getEmail());
        return new ResponseEntity<>(new ApiResponse(jwtToken,true), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody User user){
        User userDetails = userService.findIfUserExists(user.getEmail());
        if(userDetails != null){
            //check password
            if(userDetails.getPassword().equals(user.getPassword())){
                String jwtToken = JWTUtil.generateToken(user.getEmail());
                return ResponseEntity.ok(new ApiResponse(jwtToken, true));
            } else {
                return ResponseEntity.badRequest().body(new ApiResponse("Invalid User Credentials", false));
            }
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Invalid Login Credentials",false));
    }

    @GetMapping("/getEmail")
    public ResponseEntity<ApiResponse> getEmail(@RequestHeader("json") String json){
        String email = JWTUtil.extractUsername(json);
        return new ResponseEntity<>(new ApiResponse(email,true),HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/updateUser")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @GetMapping("/getUserFromEmail")
    public Optional<User> getUserFromEmail(@RequestHeader String email){
        return userService.getUserWithEmail(email);
    }

}
