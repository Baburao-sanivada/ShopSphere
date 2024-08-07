package com.shopSphere.cart_service.Services;

import com.shopSphere.cart_service.DAO.User;
import com.shopSphere.cart_service.Util.ApiResponse;
import com.shopSphere.cart_service.Util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class CartService {

    Logger logger = LoggerFactory.getLogger(CartService.class);
    private final WebClient webClient;

    @Autowired
    public CartService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public ResponseEntity<ApiResponse> addItemToCart(String jwtToken, Integer itemId) {
        String email = JWTUtil.extractUsername(jwtToken);
        // Get the user with email
        Optional<User> optionalUser = getUserWithEmail(email).blockOptional();
        logger.info("Optional User Value: "+optionalUser);
        logger.info("Email: "+email);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse("Invalid User", true), HttpStatus.NOT_FOUND);
        }
        User user = optionalUser.get();
        logger.info(" User Value: "+user.getEmail()+" "+user.getUsername());

        user.addItemToCart(itemId);

        // Update the user
        updateUser(user).block(); // Block until the update is complete

        return new ResponseEntity<>(new ApiResponse("Item Added Successfully", true), HttpStatus.OK);
    }

    public Mono<Void> updateUser(User user) {
        String url = "http://user-service/user/updateUser"; // Service name registered with Eureka
        return webClient.post()
                .uri(url)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(Void.class) // Specify the response body type
                .then(); // Return Mono<Void> after processing the update
    }

    public Mono<User> getUserWithEmail(String email) {
        String url = "http://user-service/user/getUserFromEmail"; // Service name registered with Eureka
        return webClient.get()
                .uri(url)
                .header("email", email)
                .retrieve()
                .bodyToMono(User.class)
                .onErrorResume(e -> Mono.empty()); // Handle errors by returning an empty Mono
    }

    public ResponseEntity<ApiResponse> removeItemFromCart(String jwtToken, Integer itemId) {
        String email = JWTUtil.extractUsername(jwtToken);
        Optional<User> optionalUser = getUserWithEmail(email).blockOptional();
        if(optionalUser.isEmpty()){
            return new ResponseEntity(new ApiResponse("Invalid User",true), HttpStatus.NOT_FOUND);
        }
        //User Exists
        User user = optionalUser.get();
        user.removeItemFromCart(itemId);
        updateUser(user);
        return new ResponseEntity(new ApiResponse("Item Removed Successfully",true), HttpStatus.OK);

    }

    public ResponseEntity<ApiResponse> getCartData(String jwtToken) {
        String email = JWTUtil.extractUsername(jwtToken);
        Optional<User> optionalUser = getUserWithEmail(email).blockOptional();
        if(optionalUser.isEmpty()){
            return new ResponseEntity(new ApiResponse("Invalid User",true), HttpStatus.NOT_FOUND);
        }
        //User Exists
        User user = optionalUser.get();
        return new ResponseEntity(new ApiResponse(user.getCartData(),"Cart Data",true),HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> clearCartData(String jwtToken) {
        String email = JWTUtil.extractUsername(jwtToken);
        Optional<User> optionalUser = getUserWithEmail(email).blockOptional();
        if(optionalUser.isEmpty()){
            return new ResponseEntity(new ApiResponse("Invalid User",true), HttpStatus.NOT_FOUND);
        }
        //User Exists
        User user = optionalUser.get();
        user.clearCart();
        updateUser(user);
        return new ResponseEntity(new ApiResponse("Cart Cleared",true), HttpStatus.OK);
    }
}

