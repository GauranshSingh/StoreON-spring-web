package com.gauransh.StoreON.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_details")
public class ProductDetails {

    @Id
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    private String rating;

    @Column(name = "old_price")
    private Double oldPrice;

    @Column(name = "new_price")
    private Double newPrice;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "offers")
    private String offers;

    @Column(name = "highlights")
    private String highlights;

    @Column(name = "description")
    private String description;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "category")
    private String category;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }
    
    public void setcategory(String category) {
        this.category = category;
    }

    public String getcategory() {
        return category;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOffers() {
        return offers;
    }

    public void setOffers(String offers) {
        this.offers = offers;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

