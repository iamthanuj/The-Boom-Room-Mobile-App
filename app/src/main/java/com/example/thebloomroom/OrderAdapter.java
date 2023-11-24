package com.example.thebloomroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends BaseAdapter {

    private Context context;
    private List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.order_list_item, parent, false);
        }

        // Get the current Order object
        Order order = (Order) getItem(position);

        // Bind data to the views in the list item layout
        TextView itemNameTextView = convertView.findViewById(R.id.orderItemName);
        TextView itemPriceTextView = convertView.findViewById(R.id.orderItemPrice);
        TextView itemLocationTextView = convertView.findViewById(R.id.orderItemLocation);
        TextView orderCusNameTextView = convertView.findViewById(R.id.orderCustomerName);

        itemNameTextView.setText(order.getItemName());
        itemPriceTextView.setText(order.getItemPrice());
        itemLocationTextView.setText(order.getOrderLocation());
        orderCusNameTextView.setText(order.getOrderCusName());

        return convertView;
    }
}

