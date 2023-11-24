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

    //buttons for categories
    private Button birthdayCategory,  anniversaryCategory, graduationCategory, funeralCategory, valentineCategory, newbabyCategory;

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

        //offers buttons
        offerRosesBtn = findViewById(R.id.offerBtnOne);
        offerTulipsBtn = findViewById(R.id.offerBtnTwo);
        offerOrchidsBtn = findViewById(R.id.offerBtnThree);

        //category buttons
        birthdayCategory = findViewById(R.id.birthdayCategoryBtn);
        anniversaryCategory = findViewById(R.id.AnninCategoryBtn);
        graduationCategory = findViewById(R.id.graduationCategoryBtn);
        funeralCategory = findViewById(R.id.funeralCategoryBtn);
        valentineCategory = findViewById(R.id.valentineCategoryBtn);
        newbabyCategory = findViewById(R.id.newbabyCategoryBtn);




        offerRosesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlaceOfferOrderActivity.class);

                intent.putExtra("offerFlowerImage", "roses");
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

                intent.putExtra("offerFlowerImage", "tulip");
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

                intent.putExtra("offerFlowerImage", "orchid");
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




        birthdayCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryItemListActivity.class);

                intent.putExtra("categoryTitle", "Fancy Birthday Bundle");
                intent.putExtra("categoryImage", "birthday");
                intent.putExtra("categoryDes", "Perfect for bringing joy and color to the special day");
                intent.putExtra("categoryPrice", "4300 LKR");
                intent.putExtra("customerName", activeUser);

                startActivity(intent);

            }
        });


        anniversaryCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryItemListActivity.class);

                intent.putExtra("categoryTitle", "Fancy Anniversary Bundle");
                intent.putExtra("categoryImage", "anniversary");
                intent.putExtra("categoryDes", "classic arrangement of red roses symbolizing love and passion");
                intent.putExtra("categoryPrice", "5500 LKR");
                intent.putExtra("customerName", activeUser);

                startActivity(intent);

            }
        });


        graduationCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryItemListActivity.class);

                intent.putExtra("categoryTitle", "Fancy Graduation Bundle");
                intent.putExtra("categoryImage", "graduation");
                intent.putExtra("categoryDes", "Congratulate someone with our special graduation bouquets");
                intent.putExtra("categoryPrice", "5000 LKR");
                intent.putExtra("customerName", activeUser);

                startActivity(intent);

            }
        });



        funeralCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryItemListActivity.class);

                intent.putExtra("categoryTitle", "Funeral Flowers Bundle");
                intent.putExtra("categoryImage", "funeral");
                intent.putExtra("categoryDes", "Send funeral flowers to France to express your deepest sympathy");
                intent.putExtra("categoryPrice", "4000 LKR");
                intent.putExtra("customerName", activeUser);

                startActivity(intent);

            }
        });


        valentineCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryItemListActivity.class);

                intent.putExtra("categoryTitle", "Valentines Day Bundle");
                intent.putExtra("categoryImage", "valentine");
                intent.putExtra("categoryDes", "Looking for a Valentine's gift? Deliver a bouquet of flowers");
                intent.putExtra("categoryPrice", "3800 LKR");
                intent.putExtra("customerName", activeUser);

                startActivity(intent);

            }
        });


        newbabyCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryItemListActivity.class);

                intent.putExtra("categoryTitle", "Welcome Baby Bundle");
                intent.putExtra("categoryImage", "newbaby");
                intent.putExtra("categoryDes", "Celebrate their new arrival and send them a fresh blooms");
                intent.putExtra("categoryPrice", "5000 LKR");
                intent.putExtra("customerName", activeUser);

                startActivity(intent);

            }
        });



    }
}