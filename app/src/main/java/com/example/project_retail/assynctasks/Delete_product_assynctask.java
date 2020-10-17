package com.example.project_retail.assynctasks;

import android.os.AsyncTask;

import com.example.project_retail.room.ProductDao;


public class Delete_product_assynctask extends AsyncTask<Void,Void,Void> {
    ProductDao productDao;

    public Delete_product_assynctask(ProductDao productDao) {
        this.productDao = productDao;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        productDao.deleteAllProducts();
        return null;
    }
}
