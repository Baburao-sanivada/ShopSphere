package com.backend.ShopSphere.DTO;

public class CheckoutDto {
    private String id;
    private String image;
    private String title;
    private Long price;
    private Long quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public CheckoutDto(String id, String image, String title, Long price, Long quantity) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public CheckoutDto() {
    }
}
