package com.example.project_retail.assynctasks;

import android.os.AsyncTask;

import com.example.project_retail.models.ProductsModel;
import com.example.project_retail.room.ProductDao;

import java.util.List;

public class Insert_product_assynctask extends AsyncTask<ProductsModel,Void, Void> {
    ProductDao productDao;

    public Insert_product_assynctask(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    protected Void doInBackground(ProductsModel... productsModels) {
        productDao.insertProduct(productsModels[0]);
        return null;
    }
}
