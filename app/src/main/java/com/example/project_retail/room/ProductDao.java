package com.example.project_retail.room;

import com.example.project_retail.models.ProductsModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ProductDao
{
    @Query("SELECT * FROM product")
    List<ProductsModel> getAllProducts();

    @Insert
    void insertProduct(ProductsModel productsModel);

    @Query("DELETE FROM product")
    void deleteAllProducts();
    @Update
    void updateproduct(ProductsModel productsModel);
}
