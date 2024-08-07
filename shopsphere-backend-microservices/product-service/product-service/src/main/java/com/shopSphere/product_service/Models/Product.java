package com.shopSphere.product_service.Models;

import com.shopSphere.product_service.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Products")
public class Product {

    @Autowired
    private ProductService productService;

    @Id
    private Integer id;
    private String name;
    private String image;
    private String category;
    private Integer old_price;
    private Integer new_price;
    private Date date;
    private Boolean isAvailable;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getOld_price() {
        return old_price;
    }

    public void setOld_price(Integer old_price) {
        this.old_price = old_price;
    }

    public Integer getNew_price() {
        return new_price;
    }

    public void setNew_price(Integer new_price) {
        this.new_price = new_price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }
    public void setAvailable(Boolean available) {
        isAvailable = available;
    }


    public Product(Integer id,String productName, String imageUrl, String category, Integer oldPrice, Integer newPrice) {
        this.id = id;
        this.name = productName;
        this.image = imageUrl;
        this.category = category;
        this.old_price = oldPrice;
        this.new_price = newPrice;
        this.date = new Date();
        this.isAvailable = true;
    }


    public Product() {
    }

}
