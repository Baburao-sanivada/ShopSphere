package com.shopSphere.product_service.Controllers;


import com.shopSphere.product_service.DTO.ProductDto;
import com.shopSphere.product_service.Models.Product;
import com.shopSphere.product_service.Services.ProductService;
import com.shopSphere.product_service.Util.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductService productService;

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    @CacheEvict(value = "ProductsCache")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto){
        String res=productService.saveProduct(productDto);
        return new ResponseEntity<>(new ApiResponse(productDto.getName()+"Product Added", true), HttpStatus.OK);
    }

    @GetMapping(value = "/allProducts" , produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "ProductsCache")
    public ResponseEntity<ApiResponse> getAllProducts(){
        logger.info("Getting All products from Data Base");
        List<Product> res=productService.getAllProducts();
        return new ResponseEntity<>(new ApiResponse(res,"Products",true), HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{productId}")
    @CacheEvict(value = "ProductsCache")
    public void deleteProduct(@PathVariable("productId") Integer productId){
        productService.deleteProduct(productId);
//        return new ResponseEntity<>(new ApiResponse(res,"Product Deleted Successfully",true), HttpStatus.OK);
    }

    @GetMapping("/newCollections")
    public ResponseEntity<ApiResponse> getNewCollections(){
        List<Product> res=productService.getAllProducts();
        Collections.sort(res,(a,b)-> - a.getId() + b.getId());
        res.subList(0,Math.min(res.size(),10));
        System.out.println(res);
        return new ResponseEntity<>(new ApiResponse(res,"Products",true), HttpStatus.OK);
    }

}
