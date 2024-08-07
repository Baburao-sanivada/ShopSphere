package com.shopSphere.checkout_service.Services;

import com.shopSphere.checkout_service.DTO.CheckoutDto;
import com.shopSphere.checkout_service.Util.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckoutService {

    @Value("${Base_url}")
    private String Base_Url;

    public ResponseEntity<ApiResponse> createSession(List<CheckoutDto> checkoutItems) throws StripeException{

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(Base_Url+"checkout/success")
                .setCancelUrl(Base_Url+"cart")
                .addAllLineItem(CreateLineItemsFromCheckoutItems(checkoutItems)).build();
        Session session = Session.create(params);
        return new ResponseEntity(new ApiResponse(session.getUrl(),true), HttpStatus.OK);
    }
    public List<SessionCreateParams.LineItem> CreateLineItemsFromCheckoutItems(List<CheckoutDto> checkoutItems){
        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
        for(CheckoutDto item:checkoutItems){
            SessionCreateParams.LineItem lineItem =  SessionCreateParams.LineItem.builder()
                    .setQuantity(item.getQuantity())
                    .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                    .setCurrency("usd")
                                    .setUnitAmount(item.getPrice())
                                    .setProductData(
                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                    .setName(item.getTitle())
                                                    .addImage(Base_Url+item.getImage())
                                                    .build()
                                    )
                                    .build()
                    )
                    .build();
            lineItems.add(lineItem);
        }
        return lineItems;
    }
}

