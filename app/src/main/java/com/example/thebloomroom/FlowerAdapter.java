package com.example.thebloomroom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FlowerAdapter extends BaseAdapter {

    private Context context;
    private List<Flower> flowerList;
    private DatabaseHelper databaseHelper;

    public FlowerAdapter(Context context, List<Flower> flowerList, DatabaseHelper databaseHelper) {
        this.context = context;
        this.flowerList = flowerList;
        this.databaseHelper = databaseHelper;
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

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click (e.g., show a confirmation dialog)
                showDeleteConfirmationDialog(flower.getFlowerId());
            }
        });

        return convertView;
    }


    private void showDeleteConfirmationDialog(final int flowerId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure you want to delete this flower?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked the "Delete" button
                        deleteFlower(flowerId);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked the "Cancel" button
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    private void deleteFlower(int flowerId) {
        // Perform the delete operation (remove the flower from the list and database)
        for (Flower flower : flowerList) {
            if (flower.getFlowerId() == flowerId) {
                // Assuming you have a DatabaseHelper class with a deleteFlower method
                databaseHelper.deleteFlower(flowerId);

                flowerList.remove(flower);
                notifyDataSetChanged(); // Notify the adapter that the data set has changed
                break;
            }
        }

        // Show a toast or a message indicating that the flower has been deleted
        Toast.makeText(context, "Flower deleted", Toast.LENGTH_SHORT).show();
    }



}
