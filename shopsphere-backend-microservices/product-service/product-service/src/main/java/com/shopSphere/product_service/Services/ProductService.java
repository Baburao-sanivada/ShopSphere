package com.shopSphere.product_service.Services;

import com.shopSphere.product_service.DTO.ProductDto;
import com.shopSphere.product_service.Models.Product;
import com.shopSphere.product_service.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public String saveProduct(ProductDto productDto){
        Product product = getProductFromProductDto(productDto);
        productRepo.save(product);
        return "success";
    }

    private Product getProductFromProductDto(ProductDto productDto) {
        Product product = new Product(getAutogeneratedId(),productDto.getName(),productDto.getImage(),productDto.getCategory(),productDto.getOld_price(), productDto.getNew_price());
        product.setAvailable(true);
        product.setDate(new Date());
        return product;
    }

    public Integer getAutogeneratedId() {
        List<Product> products = productRepo.findAll();
        return products.size()+1;
    }

    public Product deleteProduct(Integer productId) {
        Optional<Product> productOptional = productRepo.findById(productId);
        if (productOptional.isPresent()) {
            productRepo.deleteById(productId);
        }
        return productOptional.get();
    }
}

