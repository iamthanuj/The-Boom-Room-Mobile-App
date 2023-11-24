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

public class PlaceOfferOrderActivity extends AppCompatActivity {

    private ImageView offerFlowerImageView;
    private TextView offerFlowerNameTextView, offerFlowerDiscountTextView, offerFlowerPriceTextView ;

    private Button offerPlaceOrderBtn, offerOrderCancelBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_offer_order);

        offerPlaceOrderBtn = findViewById(R.id.offerPlaceOrderBtn);
        offerOrderCancelBtn = findViewById(R.id.offerOrderCancelBtn);

        offerFlowerImageView = findViewById(R.id.offerOrderImage);
        offerFlowerNameTextView = findViewById(R.id.offerOrderProductName);
        offerFlowerDiscountTextView = findViewById(R.id.offerOrderDiscount);
        offerFlowerPriceTextView = findViewById(R.id.offerOrderPrice);



        String offerFlowerImage = getIntent().getStringExtra("offerFlowerImage");
        String offerFlowerName = getIntent().getStringExtra("offerFlowerName");
        String offerFlowerDiscount = getIntent().getStringExtra("offerFlowerDiscount");
        String offerFlowerPrice = getIntent().getStringExtra("offerFlowerPrice");
        String activeCustomer = getIntent().getStringExtra("customerName");

        int resourceId = getResources().getIdentifier(offerFlowerImage, "drawable", getPackageName());


        offerFlowerImageView.setImageResource(resourceId);
        offerFlowerNameTextView.setText(offerFlowerName);
        offerFlowerDiscountTextView.setText(offerFlowerDiscount);
        offerFlowerPriceTextView.setText(offerFlowerPrice);


        offerOrderCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainUserPanelActivity.class);
                startActivity(intent);
            }
        });


        offerPlaceOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHelper databaseHelper = new DatabaseHelper(PlaceOfferOrderActivity.this);

                showConfirmationDialog(databaseHelper,activeCustomer, offerFlowerName, offerFlowerPrice);

            }
        });



    }



    private void showConfirmationDialog(DatabaseHelper databaseHelper,String customerName, String offerFlowerName, String offerFlowerPrice) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Order");
        builder.setMessage("Are you sure you want to place the order for " + offerFlowerName + " at $" + offerFlowerPrice + "?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Place the order in the database
                databaseHelper.addOrder(customerName, offerFlowerName, offerFlowerPrice, "Delivery Location");

                // Navigate to the confirmation screen or main user panel
                Intent intent = new Intent(PlaceOfferOrderActivity.this, MainUserPanelActivity.class);
                startActivity(intent);

                Toast.makeText(PlaceOfferOrderActivity.this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked "No", do nothing or show a message
                Toast.makeText(PlaceOfferOrderActivity.this, "Order canceled", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }
}