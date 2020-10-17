package com.example.project_retail.room;

import android.content.Context;

import androidx.room.Room;

public class RoomFactory {
    private RoomFactory(){

    }

    private static Product_database productDatabase;

    public static Product_database createRoomObject(Context context) {

        if (productDatabase == null) {

            productDatabase = Room.databaseBuilder(context, Product_database.class, "products_database")
                    .build();
        }

        return productDatabase;
    }
}
