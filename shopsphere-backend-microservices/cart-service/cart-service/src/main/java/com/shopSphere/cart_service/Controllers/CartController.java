package com.shopSphere.cart_service.Controllers;

import com.shopSphere.cart_service.Services.CartService;
import com.shopSphere.cart_service.Util.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
public class CartController {

    Logger logger = LoggerFactory.getLogger(CartController.class);
    @Autowired
    private CartService cartService;

    @PostMapping("/add/{itemId}")
    public ResponseEntity<ApiResponse> addToCart(@PathVariable("itemId") Integer itemId, @RequestHeader("auth-token") String jwtToken){
        return cartService.addItemToCart(jwtToken,itemId);
    }

    @PostMapping("/remove/{itemId}")
    public  ResponseEntity<ApiResponse> removeFromCart(@PathVariable("itemId") Integer itemId, @RequestHeader("auth-token") String jwtToken){
        return cartService.removeItemFromCart(jwtToken,itemId);
    }

    @PostMapping("/getCartData")
    public  ResponseEntity<ApiResponse> getCartData(@RequestHeader("auth-token") String jwtToken){
        logger.info("Get Cart Data: "+jwtToken);
        return cartService.getCartData(jwtToken);
    }

    @PostMapping("/clear")
    public ResponseEntity<ApiResponse> clearCart(@RequestHeader("auth-token") String jwtToken){
        return cartService.clearCartData(jwtToken);
    }
}
