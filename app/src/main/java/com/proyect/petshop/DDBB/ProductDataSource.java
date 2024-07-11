package com.proyect.petshop.DDBB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.proyect.petshop.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_NAME,
            DatabaseHelper.COLUMN_DESCRIPTION,
            DatabaseHelper.COLUMN_PRICE
    };

    public ProductDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Product createProduct(String name, String description, double price) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, description);
        values.put(DatabaseHelper.COLUMN_PRICE, price);
        long insertId = database.insert(DatabaseHelper.TABLE_PRODUCTS, null, values);
        Cursor cursor = database.query(DatabaseHelper.TABLE_PRODUCTS,
                allColumns, DatabaseHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Product newProduct = cursorToProduct(cursor);
        cursor.close();
        return newProduct;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_PRODUCTS,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Product product = cursorToProduct(cursor);
            products.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        return products;
    }

    private Product cursorToProduct(Cursor cursor) {
        Product product = new Product(cursor.getString(1), cursor.getDouble(3), cursor.getInt(4), 1);
        product.setId(cursor.getLong(0));
        return product;
    }

}

