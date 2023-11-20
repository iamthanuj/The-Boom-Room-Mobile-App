package com.example.thebloomroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartingPageActivity extends AppCompatActivity {

    Button customerTypeBtn, adminTypeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_page);

        customerTypeBtn = findViewById(R.id.typeCusBtn);
        adminTypeBtn = findViewById(R.id.typeAdminBtn);


        customerTypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cusLoginMenuActivity = new Intent(getApplicationContext(),UserLoginActivity.class);
                startActivity(cusLoginMenuActivity);
            }
        });


        adminTypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminLoginActivity = new Intent(getApplicationContext(), AdminLoginActivity.class);
                startActivity(adminLoginActivity);
            }
        });


    }
}