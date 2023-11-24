package com.example.thebloomroom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CategoryItemListActivity extends AppCompatActivity {

    private ImageView categoryImage;
    private TextView categoryTitle, categoryDes, categoryPrice;

    private Button categoryOrderBtn, categoryMainMenuBtn;

    private String activeCustomer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_item_list);


        categoryOrderBtn = findViewById(R.id.categoryOrderBtn);
        categoryMainMenuBtn = findViewById(R.id.categoryMainMenuBtn);


        categoryTitle = findViewById(R.id.categoryTitleTxt);
        categoryImage = findViewById(R.id.categoryBundleImage);
        categoryDes = findViewById(R.id.categoryBundleDescription);
        categoryPrice = findViewById(R.id.categoryBundlePrice);


        String catTitle = getIntent().getStringExtra("categoryTitle");
        String catImage = getIntent().getStringExtra("categoryImage");
        String catDes = getIntent().getStringExtra("categoryDes");
        String catPrice = getIntent().getStringExtra("categoryPrice");

        activeCustomer = getIntent().getStringExtra("customerName");

        int resourceId = getResources().getIdentifier(catImage, "drawable", getPackageName());



        categoryImage.setImageResource(resourceId);
        categoryTitle.setText(catTitle);
        categoryDes.setText(catDes);
        categoryPrice.setText(catPrice);





        categoryOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHelper databaseHelper = new DatabaseHelper(CategoryItemListActivity.this);

                showConfirmationDialog(databaseHelper,activeCustomer, catTitle, catPrice);




            }
        });



    }


    private void showConfirmationDialog(DatabaseHelper databaseHelper,String customerName, String catBunName, String catBunPrice) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Order");
        builder.setMessage("Are you sure you want to place the order for " + catBunName + " at" + catBunPrice + "?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Place the order in the database
                databaseHelper.addOrder(customerName, catBunName, catBunPrice, "Delivery Location");

                // Navigate to the confirmation screen or main user panel
                Intent intent = new Intent(CategoryItemListActivity.this, MainUserPanelActivity.class);
                startActivity(intent);

                Toast.makeText(CategoryItemListActivity.this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked "No", do nothing or show a message
                Toast.makeText(CategoryItemListActivity.this, "Order canceled", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }
}