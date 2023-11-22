package com.example.thebloomroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class FlowerAdapter extends BaseAdapter {

    private Context context;
    private List<Flower> flowerList;

    public FlowerAdapter(Context context, List<Flower> flowerList) {
        this.context = context;
        this.flowerList = flowerList;
    }


    @Override
    public int getCount() {
        return flowerList.size();
    }

    @Override
    public Object getItem(int position) {
        return flowerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.added_flower_list_item, parent, false);
        }

        // Get the current Flower object
        Flower flower = (Flower) getItem(position);

        // Bind data to the views in the list item layout
        TextView nameTextView = convertView.findViewById(R.id.item_name);
//        TextView colorTextView = convertView.findViewById(R.id.colorTextView);
        TextView descriptionTextView = convertView.findViewById(R.id.item_description);
        TextView priceTextView = convertView.findViewById(R.id.item_price);

        nameTextView.setText(flower.getFlowerName());
//        colorTextView.setText(flower.getFlowerColor());
        descriptionTextView.setText(flower.getDescription());
        priceTextView.setText(flower.getPrice());

        return convertView;
    }
}
