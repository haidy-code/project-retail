package com.example.project_retail.responses;

import com.example.project_retail.models.ProductsModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Products_Response {
    @SerializedName("products")
private List<ProductsModel>productlist;

    public List<ProductsModel> getProductlist() {
        return productlist;
    }

    public Products_Response(List<ProductsModel> productlist) {
        this.productlist = productlist;
    }
}
