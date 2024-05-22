package com.backend.ShopSphere.Products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        String res=productService.saveProduct(product);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> res=productService.getAllProducts();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
