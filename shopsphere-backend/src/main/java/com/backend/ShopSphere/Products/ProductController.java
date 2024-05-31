package com.backend.ShopSphere.Products;

import com.backend.ShopSphere.CommonUtility.ApiResponse;
import com.backend.ShopSphere.DTO.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductService productService;

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto){
        String res=productService.saveProduct(productDto);
        logger.info("Hello from add product");
        return new ResponseEntity<>(new ApiResponse(productDto.getProductName()+"Prodct Added", true), HttpStatus.OK);
    }

    @GetMapping(value = "/get" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getAllProducts(){
        List<Product> res=productService.getAllProducts();
        return new ResponseEntity<>(new ApiResponse(res,"Products",true), HttpStatus.OK);
    }

}
