package com.gauransh.StoreON.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products_categories")
public class ExtractUser {

    @Id
    @Column(name = "category")
    private String category;

    @Column(name = "range")
    private String range;
    
    @Column(name = "category_images")
    private String category_images;
    
    public String getCategory_images() {
    	return category_images;
    }

    public void setCategory_images(String category_images) {
    	this.category_images=category_images;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
