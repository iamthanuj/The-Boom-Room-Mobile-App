package com.example.thebloomroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPanelActivity extends AppCompatActivity {

    private Button addFlowerBtn, viewAddedFlowersBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        addFlowerBtn = findViewById(R.id.addFlowersBtn);
        viewAddedFlowersBtn = findViewById(R.id.viewAddedFlowers);


        //add flowers
        addFlowerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddFlowersActivity.class);
                startActivity(intent);
            }
        });



        //view added flowers





    }
}