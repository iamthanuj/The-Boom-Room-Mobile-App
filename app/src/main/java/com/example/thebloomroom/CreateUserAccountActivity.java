package com.example.thebloomroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;
import org.mindrot.jbcrypt.BCrypt;

public class CreateUserAccountActivity extends AppCompatActivity {

    Button button;
    private EditText editTxtFirstName, editTxtLastName, editTxtEmail, editTxtPhoneNum,
            editTxtAddress, editTxtPassword, editTxtConPassword;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_account);

        // Initialize the DatabaseHelper
        dbHelper = new DatabaseHelper(this);


        //referencing the ui elements
        button = findViewById(R.id.regBtn);
        editTxtFirstName = findViewById(R.id.regFirstName);
        editTxtLastName = findViewById(R.id.regLastName);
        editTxtEmail = findViewById(R.id.regEmail);
        editTxtPhoneNum = findViewById(R.id.regPhoneNum);
        editTxtAddress = findViewById(R.id.regAddress);
        editTxtPassword = findViewById(R.id.regPassword);
        editTxtConPassword = findViewById(R.id.regConPassword);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = editTxtFirstName.getText().toString();
                String lastName = editTxtLastName.getText().toString();
                String email = editTxtEmail.getText().toString();
                String phoneNum = editTxtPhoneNum.getText().toString();
                String address = editTxtAddress.getText().toString();
                String password = editTxtPassword.getText().toString();
                String confirmPassword = editTxtConPassword.getText().toString();

                //input validation
                if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNum.isEmpty() ||
                        address.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ){

                    String msg = "Please fill in all the fields";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                } else if (!password.equals(confirmPassword)) {
                    String msg = "Confirm Password Doesn't match";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                } else if (!isValidEmail(email)) {
                    String msg = "Please enter valid email";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }
                else {

                    //open database
                    SQLiteDatabase db = dbHelper.getWritableDatabase();

//                    //phone number convert to int
//                    Integer phoneNumInt = Integer.parseInt(editTxtPhoneNum.getText().toString());

                    //encrypted password
                    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

                    ContentValues values = new ContentValues();
                    values.put("first_name", firstName);
                    values.put("last_name", lastName);
                    values.put("email", email);
                    values.put("phone_number", phoneNum);
                    values.put("address", address);
                    values.put("password", hashedPassword);

                    //inserting data into the database
                    long newRowId = db.insert("users", null, values);

//                    db.close();

                    String msg;
                    if( newRowId != -1){
                        msg = "Registration successful!";
                        Intent intent = new Intent(getApplicationContext(),UserLoginActivity.class);
                        startActivity(intent);
                    }
                    else{
                        msg = "Registration failed!";
                    }
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                }


            }
        });

    }

    private boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+$";
        return Pattern.matches(emailPattern, email);
    }

}