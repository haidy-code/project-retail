package com.example.project_retail.responses;

import com.example.project_retail.models.CategoryModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category_Response {
    @SerializedName("categories")
    private List<CategoryModel> categorylist;

    public Category_Response(List<CategoryModel> categorylist) {
        this.categorylist = categorylist;
    }

    public List<CategoryModel> getCategorylist() {
        return categorylist;
    }
}
