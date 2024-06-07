package com.backend.ShopSphere.Products;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductRepo extends MongoRepository<Product,Integer> {

}