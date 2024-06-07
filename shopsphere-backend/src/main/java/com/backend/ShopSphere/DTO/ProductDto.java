package com.backend.ShopSphere.DTO;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public class ProductDto {
    @NotBlank
    private String name;
    @NotBlank
    private String image;
    @NotBlank
    private String category;
    private Integer old_price;
    private Integer new_price;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
}
