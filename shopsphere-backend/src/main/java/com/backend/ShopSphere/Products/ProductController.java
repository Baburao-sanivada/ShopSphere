package com.backend.ShopSphere.Products;

import com.backend.ShopSphere.DTO.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto){
        String res=productService.saveProduct(productDto);
        logger.info("Hello from add product");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> res=productService.getAllProducts();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
