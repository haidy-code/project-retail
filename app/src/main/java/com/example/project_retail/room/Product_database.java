package com.example.project_retail.room;

import com.example.project_retail.models.ProductsModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {ProductsModel.class}, version = 1, exportSchema = false)
public abstract class Product_database extends RoomDatabase {
    public abstract ProductDao getProductDao();

}
