package com.example.thebloomroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainUserPanelActivity extends AppCompatActivity {

    private TextView activeUserTextView;
    private ImageButton offerRosesBtn, offerTulipsBtn, offerOrchidsBtn;

    private Button moreFlowersBtn, viewMyOrdersBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_panel);

        //show log user
        activeUserTextView = findViewById(R.id.activeUserName);
        String activeUser = getIntent().getStringExtra("loggedUser");
        activeUserTextView.setText("Hello There,"+activeUser);


        moreFlowersBtn = findViewById(R.id.viewMoreFlowerBtn);
        viewMyOrdersBtn = findViewById(R.id.viewOrderdItemsBtn);

        offerRosesBtn = findViewById(R.id.offerBtnOne);
        offerTulipsBtn = findViewById(R.id.offerBtnTwo);
        offerOrchidsBtn = findViewById(R.id.offerBtnThree);



        offerRosesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlaceOfferOrderActivity.class);

                intent.putExtra("offerFlowerName", "Rose Bouquet");
                intent.putExtra("offerFlowerDiscount", "15% OFF");
                intent.putExtra("offerFlowerPrice", "5000 LKR");
                intent.putExtra("customerName ", activeUser);
                startActivity(intent);
            }
        });


        offerTulipsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlaceOfferOrderActivity.class);

                intent.putExtra("offerFlowerName", "Tulips Bouquet");
                intent.putExtra("offerFlowerDiscount", "20% OFF");
                intent.putExtra("offerFlowerPrice", "4400 LKR");
                intent.putExtra("customerName", activeUser);
                startActivity(intent);
            }
        });




        offerOrchidsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlaceOfferOrderActivity.class);

                intent.putExtra("offerFlowerName", "Orchids Bouquet");
                intent.putExtra("offerFlowerDiscount", "10% OFF");
                intent.putExtra("offerFlowerPrice", "5200 LKR");
                intent.putExtra("customerName", activeUser);
                startActivity(intent);
            }
        });




        moreFlowersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewAddedFlowersActivity.class);
                intent.putExtra("userType", "CUSTOMER");
                intent.putExtra("customerUsername", activeUser);
                startActivity(intent);
            }
        });



        viewMyOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewOrdersActivity.class);
                intent.putExtra("activeUser", activeUser);
                startActivity(intent);
            }
        });



    }
}