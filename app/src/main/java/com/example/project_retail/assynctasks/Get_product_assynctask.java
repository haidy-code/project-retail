package com.example.project_retail.assynctasks;

import android.os.AsyncTask;

import com.example.project_retail.models.ProductsModel;
import com.example.project_retail.room.ProductDao;

import java.util.List;

public class Get_product_assynctask extends AsyncTask<Void,Void, List<ProductsModel>>{
    ProductDao productDao;

    public Get_product_assynctask(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    protected List<ProductsModel> doInBackground(Void... voids) {

        return productDao.getAllProducts();
    }
}
