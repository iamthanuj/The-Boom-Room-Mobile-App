package com.example.thebloomroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartingPageActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_page);

        button = findViewById(R.id.typeCusBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cusLoginMenuActivity = new Intent(getApplicationContext(),UserLoginActivity.class);
                startActivity(cusLoginMenuActivity);
            }
        });


    }
}