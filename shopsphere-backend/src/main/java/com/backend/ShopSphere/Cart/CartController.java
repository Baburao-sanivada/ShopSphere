package com.backend.ShopSphere.Cart;

import com.backend.ShopSphere.CommonUtility.ApiResponse;
import com.backend.ShopSphere.CommonUtility.JsonUtil;
import com.backend.ShopSphere.SignIn.User;
import com.backend.ShopSphere.SignIn.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add/{itemId}")
    public ResponseEntity<ApiResponse> addToCart(@PathVariable("itemId") Integer itemId, @RequestHeader("jwt") String jwtToken){
        return cartService.addItemToCart(jwtToken,itemId);
    }

    @PostMapping("/remove/{itemId}")
    public  ResponseEntity<ApiResponse> removeFromCart(@PathVariable("itemId") Integer itemId, @RequestHeader("jwt") String jwtToken){
        return cartService.removeItemFromCart(jwtToken,itemId);
    }

    @PostMapping("/getCartData")
    public  ResponseEntity<int[]> getCartData(@RequestHeader("jwt") String jwtToken){
        return cartService.getCartData(jwtToken);
    }
}
