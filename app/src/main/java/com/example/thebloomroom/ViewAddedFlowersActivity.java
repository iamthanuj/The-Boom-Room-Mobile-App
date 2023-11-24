package com.example.thebloomroom;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewAddedFlowersActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_added_flowers);

        String userType = getIntent().getStringExtra("userType");
        String activeUsername = getIntent().getStringExtra("customerUsername");

        listView = findViewById(R.id.listViewAddedFlowers);
        databaseHelper = new DatabaseHelper(this);
        Cursor cursor = fetchDataFromDatabase();

        List<Flower> flowerList = getFlowerListFromCursor(cursor);

        FlowerAdapter flowerAdapter = new FlowerAdapter(this, flowerList, databaseHelper, userType, activeUsername);


        listView.setAdapter(flowerAdapter);

        // Close the cursor after use
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }


    }


    public Cursor fetchDataFromDatabase() {
        // Assuming dbHelper is an instance of your SQLiteOpenHelper
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Define the columns you want to retrieve
        String[] projection = {
                "flower_id",
                "flower_name",
                "flower_color",
                "description",
                "price"
        };

        // Query the database
        Cursor cursor = db.query(
                "flowers",     // Table name
                projection,    // Columns to retrieve
                null,           // Selection (null retrieves all rows)
                null,           // Selection arguments
                null,           // Group by (null means no grouping)
                null,           // Having (null means no "having" clause)
                null            // Order by (null means no ordering)
        );

        // Note: Ensure to close the cursor and database when done

        return cursor;
    }



    private List<Flower> getFlowerListFromCursor(Cursor cursor) {
        List<Flower> flowerList = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int flowerId = cursor.getInt(cursor.getColumnIndexOrThrow("flower_id"));
                String flowerName = cursor.getString(cursor.getColumnIndexOrThrow("flower_name"));
                String flowerColor = cursor.getString(cursor.getColumnIndexOrThrow("flower_color"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                String price = cursor.getString(cursor.getColumnIndexOrThrow("price"));

                Flower flower = new Flower(flowerId, flowerName, flowerColor, description, price);
                flowerList.add(flower);
            } while (cursor.moveToNext());
        }

        // Close the cursor after use
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return flowerList;
    }

}