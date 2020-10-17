package com.example.project_retail.models;

import com.google.gson.annotations.SerializedName;

public class CategoryModel {
    @SerializedName("id")
    private int category_id;
    @SerializedName("name")
    private String categoryname;
    @SerializedName("avatar")
    private String categoryphoto;

    public CategoryModel() {
    }

    public CategoryModel( String categoryname, String categoryphoto) {
        this.categoryname = categoryname;
        this.categoryphoto = categoryphoto;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getCategoryphoto() {
        return categoryphoto;
    }

    public void setCategoryphoto(String categoryphoto) {
        this.categoryphoto = categoryphoto;
    }
}
