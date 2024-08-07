package com.shopSphere.checkout_service.Contollers;

import com.shopSphere.checkout_service.DTO.CheckoutDto;
import com.shopSphere.checkout_service.Services.CheckoutService;
import com.shopSphere.checkout_service.Util.ApiResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    CheckoutService checkoutService;

    @PostMapping("/createSession")
    public ResponseEntity<ApiResponse> StripePaymentSessions(@RequestBody List<CheckoutDto> checkoutItems) throws  StripeException {
        System.out.print("Stripe API Key:"+ Stripe.apiKey);
        return checkoutService.createSession(checkoutItems);
    }

}