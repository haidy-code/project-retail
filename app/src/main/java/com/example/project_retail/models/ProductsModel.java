package com.example.project_retail.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product")

public class ProductsModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long incrementId;
    @SerializedName("id")
    private int product_id;
    @ColumnInfo(name="details")
    @SerializedName("name")
    private String details;
    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;
    @SerializedName("category_id")
    private int category_id;
    @SerializedName("description")
    private String description;
    @SerializedName("price")
    private int price;
    @SerializedName("discount")
    private int discount;
    @SerializedName("discount_type")
    private String discount_Type;
    @SerializedName("currency")
    private String currency;
    @SerializedName("in_stock")
    private int inStock;
    @ColumnInfo(name = "photo")
    @SerializedName("avatar")
    private String photo;
    @SerializedName("price_final")
    private double priceFinal;
    @ColumnInfo(name = "finalpricetext")
    @SerializedName("price_final_text")
    private String finalPriceText;
    @ColumnInfo(name = "quantity")
    private int quantity;

    public ProductsModel(String details, String title, String photo, String finalPriceText) {
        this.details = details;
        this.title = title;
        this.photo = photo;
        this.finalPriceText = finalPriceText;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getIncrementId() {
        return incrementId;
    }

    public void setIncrementId(long incrementId) {
        this.incrementId = incrementId;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDiscount_Type() {
        return discount_Type;
    }

    public void setDiscount_Type(String discount_Type) {
        this.discount_Type = discount_Type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getPriceFinal() {
        return priceFinal;
    }

    public void setPriceFinal(double priceFinal) {
        this.priceFinal = priceFinal;
    }

    public String getFinalPriceText() {
        return finalPriceText;
    }

    public void setFinalPriceText(String finalPriceText) {
        this.finalPriceText = finalPriceText;
    }
}

