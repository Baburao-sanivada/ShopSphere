package com.backend.ShopSphere.SignIn;

import com.backend.ShopSphere.CommonUtility.ApiResponse;
import com.backend.ShopSphere.CommonUtility.JsonUtil;
import com.backend.ShopSphere.DTO.SignUpDto;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        String jwtToken = JsonUtil.generateToken(userDto.getEmail());
        return new ResponseEntity<>(new ApiResponse(jwtToken,true), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody User user){
        User userDetails = userService.findIfUserExists(user.getEmail());
        if(userDetails != null){
            //check password
            if(userDetails.getPassword().equals(user.getPassword())){
                String jwtToken = JsonUtil.generateToken(user.getEmail());
                return ResponseEntity.ok(new ApiResponse(jwtToken, true));
            } else {
                return ResponseEntity.badRequest().body(new ApiResponse("Invalid User Credentials", false));
            }
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Invalid Login Credentials",false));
    }

    @GetMapping("/getEmail")
    public ResponseEntity<ApiResponse> getEmail(@RequestHeader("json") String json){
        String email = JsonUtil.extractUsername(json);
        return new ResponseEntity<>(new ApiResponse(email,true),HttpStatus.BAD_REQUEST);
    }

}
