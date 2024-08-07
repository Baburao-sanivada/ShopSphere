package com.shopSphere.product_service.Repository;

import com.shopSphere.product_service.Models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends MongoRepository<Product,Integer> {

}