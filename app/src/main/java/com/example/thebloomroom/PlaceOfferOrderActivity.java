package com.example.thebloomroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PlaceOfferOrderActivity extends AppCompatActivity {

    private ImageView offerFlowerImageView;
    private TextView offerFlowerNameTextView, offerFlowerDiscountTextView, offerFlowerPriceTextView ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_offer_order);

        offerFlowerNameTextView = findViewById(R.id.offerOrderProductName);
        offerFlowerDiscountTextView = findViewById(R.id.offerOrderDiscount);
        offerFlowerPriceTextView = findViewById(R.id.offerOrderPrice);

        String offerFlowerName = getIntent().getStringExtra("offerFlowerName");
        String offerFlowerDiscount = getIntent().getStringExtra("offerFlowerDiscount");
        String offerFlowerPrice = getIntent().getStringExtra("offerFlowerPrice");

        offerFlowerNameTextView.setText(offerFlowerName);
        offerFlowerDiscountTextView.setText(offerFlowerDiscount);
        offerFlowerPriceTextView.setText(offerFlowerPrice);

    }
}