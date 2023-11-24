package com.example.thebloomroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewOrdersActivity extends AppCompatActivity {

    private ListView listView;
    private OrderAdapter orderAdapter;

    private String activeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        activeUser = getIntent().getStringExtra("activeUser");

        listView = findViewById(R.id.ListViewOrders);

        List<Order> orderList;

        if(TextUtils.isEmpty(activeUser) || activeUser.equals("ADMIN")){
            orderList = databaseHelper.getAllOrders(activeUser);
        } else {
            orderList = databaseHelper.getAllOrders(activeUser);
        }



        if (orderList != null && !orderList.isEmpty()) {
            OrderAdapter orderAdapter = new OrderAdapter(this, orderList);
            listView.setAdapter(orderAdapter);
        } else {

            Toast.makeText(this, "No orders available", Toast.LENGTH_SHORT).show();
        }

    }
}