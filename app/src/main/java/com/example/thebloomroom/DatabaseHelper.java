package com.example.thebloomroom;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "TheBloomRoomDB";
    private static final int DB_VERSION = 2;


    private static final String CREATE_USER_TABLE = "CREATE TABLE users (" +
            "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "first_name TEXT," +
            "last_name TEXT," +
            "email TEXT," +
            "phone_number TEXT," +
            "address TEXT," +
            "password TEXT" +
            ");";


    private static final String CREATE_FLOWERS_TABLE = "CREATE TABLE flowers (" +
            "flower_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "flower_name TEXT," +
            "flower_color TEXT," +
            "description TEXT," +
            "price TEXT" +
            ");";



    private static final String CREATE_ORDERS_TABLE = "CREATE TABLE orders (" +
            "order_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "customer_name TEXT," +
            "item_name TEXT," +
            "item_price TEXT," +
            "order_location TEXT" +
            ");";



    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_FLOWERS_TABLE);
        db.execSQL(CREATE_ORDERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS flowers");
        db.execSQL("DROP TABLE IF EXISTS orders");
        onCreate(db);
    }


    public void deleteFlower(int flowerId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("flowers", "flower_id=?", new String[]{String.valueOf(flowerId)});
        db.close();
    }


    public void updateFlower(int flowerId, String updatedName, String updatedColor, String updatedDescription, String updatedPrice) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("flower_name", updatedName);
        values.put("flower_color", updatedColor);
        values.put("description", updatedDescription);
        values.put("price", updatedPrice);

        // Update the row with the specified flowerId
        db.update("flowers", values, "flower_id=?", new String[]{String.valueOf(flowerId)});

        // Close the database connection
        db.close();
    }


    public Flower getFlowerById(int flowerId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Flower flower = null;

        // Define the columns to be queried
        String[] columns = {"flower_id", "flower_name", "flower_color", "description", "price"};

        // Define the selection criteria
        String selection = "flower_id" + " = ?";
        String[] selectionArgs = {String.valueOf(flowerId)};

        // Query the database
        Cursor cursor = db.query("flowers", columns, selection, selectionArgs, null, null, null);

        // Check if the cursor is not null and move it to the first row
        if (cursor != null && cursor.moveToFirst()) {
            // Extract flower details from the cursor
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("flower_name"));
            @SuppressLint("Range") String color = cursor.getString(cursor.getColumnIndex("flower_color"));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));
            @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex("price"));

            // Create a Flower object
            flower = new Flower(flowerId, name, color, description, price);

            // Close the cursor
            cursor.close();
        }

        // Close the database connection
        db.close();

        // Return the Flower object
        return flower;
    }



    public void addOrder(String customerName, String itemName, String itemPrice, String orderLocation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("customer_name", customerName);
        values.put("item_name", itemName);
        values.put("item_price", itemPrice);
        values.put("order_location", orderLocation);


        db.insert("orders", null, values);
        db.close();
    }




    public List<Order> getAllOrders(String activeUser) {
        List<Order> orderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Define the columns to be queried
        String[] columns = {"order_id", "customer_name", "item_name", "item_price", "order_location"};

        // Query the "orders" table
        try (Cursor cursor = db.query("orders", columns, null, null, null, null, null)) {
            // Check if the cursor is not null and move it to the first row
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    // Extract order details from the cursor
                    @SuppressLint("Range") String itemName = cursor.getString(cursor.getColumnIndex("item_name"));
                    @SuppressLint("Range") String itemPrice = cursor.getString(cursor.getColumnIndex("item_price"));
                    @SuppressLint("Range") String orderLocation = cursor.getString(cursor.getColumnIndex("order_location"));
                    @SuppressLint("Range") String orderCusName = cursor.getString(cursor.getColumnIndex("customer_name"));

                    // Create an Order object and add it to the list
                    Order order = new Order(itemName, itemPrice, orderLocation, orderCusName);
                    orderList.add(order);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return orderList;
    }



}
