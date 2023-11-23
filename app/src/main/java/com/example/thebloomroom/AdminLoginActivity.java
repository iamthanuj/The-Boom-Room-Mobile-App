package com.example.thebloomroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText adminUsername, adminPassword;
    private Button adminLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        adminUsername = findViewById(R.id.update_name_edittext);
        adminPassword = findViewById(R.id.adminLoginPassword);
        adminLoginBtn = findViewById(R.id.adminLoginBtn);

        adminLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = adminUsername.getText().toString();
                String password = adminPassword.getText().toString();


                String msg;
                if ("Admin".equals(username) && "Admin".equals(password) ){
                    msg = "login Successfully!";
                    Intent intent = new Intent(getApplicationContext(), AdminPanelActivity.class);
                    startActivity(intent);
                }
                else {
                    msg = "Invalid Credentials";
                }
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

            }
        });

    }
}