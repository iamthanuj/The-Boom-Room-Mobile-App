package com.example.thebloomroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.mindrot.jbcrypt.BCrypt;

public class UserLoginActivity extends AppCompatActivity {

    private Button createAccBtn, userLoginBtn;
    private EditText loginUsername, loginPassword;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        dbHelper = new DatabaseHelper(this);

        loginUsername = findViewById(R.id.update_name_edittext);
        loginPassword = findViewById(R.id.adminLoginPassword);
        createAccBtn = findViewById(R.id.createAccBtn);
        userLoginBtn = findViewById(R.id.adminLoginBtn);



        //intent to register user activity
        createAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regScreenActivity = new Intent(getApplicationContext(), CreateUserAccountActivity.class);
                startActivity(regScreenActivity);
            }
        });



        //login user
        userLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = loginUsername.getText().toString();
                String password = loginPassword.getText().toString();

                //input validation
                if(username.isEmpty() || password.isEmpty()){
                    String msg = "Please fill in the empty fields";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                } else if (checkUserCredentials(username, password)) {
                    String msg = "login Successfully!";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainUserPanelActivity.class);
                    intent.putExtra("loggedUser", username);
                    startActivity(intent);
                }
                else{
                    String msg = "login Failed!";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }
            }
        });




    }

    private boolean checkUserCredentials(String username, String enteredPassword){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {"password"};
        String selection = "email = ?";
        String[] selectionArgs = {username};

        Cursor cursor =db.query("users", projection, selection, selectionArgs, null, null, null);

        boolean credentialMatch = false;

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            int passwordIndex = cursor.getColumnIndexOrThrow("password");
            String storedPassword = cursor.getString(passwordIndex);

            if(BCrypt.checkpw(enteredPassword, storedPassword)){
                credentialMatch = true;
            }

        }
        return credentialMatch;
    }

}