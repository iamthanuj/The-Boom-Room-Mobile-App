package com.example.thebloomroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFlowersActivity extends AppCompatActivity {

    private EditText flowerNameValue, flowerColorValue, flowerDescription, flowerPriceValue;
    private Button addFlowerBtn;
    private DatabaseHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flowers);


        flowerNameValue = findViewById(R.id.update_name_edittext);
        flowerColorValue = findViewById(R.id.update_color_edittext);
        flowerDescription = findViewById(R.id.update_description_edittext);
        flowerPriceValue = findViewById(R.id.update_price_edittext);
        addFlowerBtn = findViewById(R.id.offerPlaceOrderBtn);

        dbHelper = new DatabaseHelper(this);

        addFlowerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = "";

                String flowerName = flowerNameValue.getText().toString();
                String flowerColor = flowerColorValue.getText().toString();
                String flowerDes = flowerDescription.getText().toString();
                String flowerPrice = flowerPriceValue.getText().toString();

                if( flowerName.isEmpty() || flowerColor.isEmpty() || flowerDes.isEmpty() || flowerPrice.isEmpty() ) {
                     msg = "Please fill in all fields";

                }
                else {
                    insertFlower(flowerName, flowerColor, flowerDes, flowerPrice);
                    msg = "Flower Added Successfully";
                    flowerNameValue.setText("");
                    flowerColorValue.setText("");
                    flowerDescription.setText("");
                    flowerPriceValue.setText("");
                }

                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

            }


        });



    }

    private void insertFlower(String flowerName, String flowerColor, String flowerDes, String flowerPrice){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("flower_name", flowerName);
        values.put("flower_color", flowerColor);
        values.put("description", flowerDes);
        values.put("price", flowerPrice);

        long newRowId = database.insert("flowers", null, values);

    }




}