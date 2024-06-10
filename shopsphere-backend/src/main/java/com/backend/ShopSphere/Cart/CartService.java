package com.backend.ShopSphere.Cart;

import com.backend.ShopSphere.CommonUtility.ApiResponse;
import com.backend.ShopSphere.CommonUtility.JsonUtil;
import com.backend.ShopSphere.SignIn.User;
import com.backend.ShopSphere.SignIn.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private UserService userService;

    public ResponseEntity<ApiResponse> addItemToCart(String jwtToken,Integer itemId){
        String email = JsonUtil.extractUsername(jwtToken);
        Optional<User> optionalUser = userService.getUserWithEmail(email);
        if(optionalUser.isEmpty()){
            return new ResponseEntity(new ApiResponse("Invalid User",true), HttpStatus.NOT_FOUND);
        }
        //User Exists
        User user = optionalUser.get();
        user.addItemToCart(itemId);
        userService.updateUser(user);
        return new ResponseEntity(new ApiResponse("Item Added Successfully",true), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> removeItemFromCart(String jwtToken, Integer itemId) {
        String email = JsonUtil.extractUsername(jwtToken);
        Optional<User> optionalUser = userService.getUserWithEmail(email);
        if(optionalUser.isEmpty()){
            return new ResponseEntity(new ApiResponse("Invalid User",true), HttpStatus.NOT_FOUND);
        }
        //User Exists
        User user = optionalUser.get();
        user.removeItemFromCart(itemId);
        userService.updateUser(user);
        return new ResponseEntity(new ApiResponse("Item Removed Successfully",true), HttpStatus.OK);

    }

    public ResponseEntity<ApiResponse> getCartData(String jwtToken) {
        String email = JsonUtil.extractUsername(jwtToken);
        Optional<User> optionalUser = userService.getUserWithEmail(email);
        if(optionalUser.isEmpty()){
            return new ResponseEntity(new ApiResponse("Invalid User",true), HttpStatus.NOT_FOUND);
        }
        //User Exists
        User user = optionalUser.get();
        return new ResponseEntity(new ApiResponse(user.getCartData(),"Cart Data",true),HttpStatus.OK);
    }
}
