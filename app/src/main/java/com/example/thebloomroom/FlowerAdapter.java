package com.example.thebloomroom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FlowerAdapter extends BaseAdapter {

    private Context context;
    private List<Flower> flowerList;
    private DatabaseHelper databaseHelper;

    private String userType;
    private  String activeCusName;


    public FlowerAdapter(Context context, List<Flower> flowerList, DatabaseHelper databaseHelper, String userType, String activeCusName) {
        this.context = context;
        this.flowerList = flowerList;
        this.databaseHelper = databaseHelper;
        this.userType = userType;
        this.activeCusName = activeCusName;
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
        TextView colorTextView = convertView.findViewById(R.id.item_color);
        TextView descriptionTextView = convertView.findViewById(R.id.item_description);
        TextView priceTextView = convertView.findViewById(R.id.item_price);

        nameTextView.setText(flower.getFlowerName());
        colorTextView.setText(flower.getFlowerColor());
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

        if(userType.equals("ADMIN")){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Are you sure you want to delete this flower?")
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked the "Delete" button
                            deleteFlower(flowerId);

                        }
                    })
                    .setNegativeButton("Update", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            updateFlower(flowerId);
                            // User clicked the "Cancel" button
                            dialog.dismiss();
                        }
                    });
            builder.create().show();
        }

        else if(userType.equals("CUSTOMER")){

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Are you want to Order this flower?")
                    .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            addFlowerToOrder(flowerId);

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




    private void updateFlower(int flowerId) {
        // Assume you have a method in your DatabaseHelper
        // class to retrieve the Flower object based on the flowerId.
        Flower flowerToUpdate = databaseHelper.getFlowerById(flowerId);

        if (flowerToUpdate == null) {
            // Handle the case where the flower is not found in the database
            Toast.makeText(context, "Error: Flower not found", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a dialog for updating the flower details.
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update Flower Details");

        // Inflate a layout for the dialog
        LayoutInflater inflater = LayoutInflater.from(context);
        View updateView = inflater.inflate(R.layout.update_flower_dialog, null);

        // Check if the layout inflation was successful
        if (updateView == null) {
            // Handle the case where the layout couldn't be inflated
            Toast.makeText(context, "Error: Unable to inflate update dialog", Toast.LENGTH_SHORT).show();
            return;
        }

        // Reference the views in the dialog layout
        EditText updateNameEditText = updateView.findViewById(R.id.update_name_edittext);
        EditText updateColorEditText = updateView.findViewById(R.id.update_color_edittext);
        EditText updateDescriptionEditText = updateView.findViewById(R.id.update_description_edittext);
        EditText updatePriceEditText = updateView.findViewById(R.id.update_price_edittext);

        // Populate the dialog views with the current flower details
        updateNameEditText.setText(flowerToUpdate.getFlowerName());
        updateColorEditText.setText(flowerToUpdate.getFlowerColor());
        updateDescriptionEditText.setText(flowerToUpdate.getDescription());
        updatePriceEditText.setText(flowerToUpdate.getPrice());

        builder.setView(updateView);

        builder.setPositiveButton("Save Changes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Update the flower details in the database
                String updatedName = updateNameEditText.getText().toString();
                String updatedColor = updateColorEditText.getText().toString();
                String updatedDescription = updateDescriptionEditText.getText().toString();
                String updatedPrice = updatePriceEditText.getText().toString();

                // Assuming you have a method in your DatabaseHelper to update the flower
                databaseHelper.updateFlower(flowerId, updatedName, updatedColor, updatedDescription, updatedPrice);

                // Update the flower details in the flowerList
                flowerToUpdate.setFlowerName(updatedName);
                flowerToUpdate.setFlowerColor(updatedColor);
                flowerToUpdate.setDescription(updatedDescription);
                flowerToUpdate.setPrice(updatedPrice);


                notifyDataSetInvalidated();

                Toast.makeText(context, "Flower updated", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }





    private void addFlowerToOrder(int flowerId) {
        Flower selectedFlower = databaseHelper.getFlowerById(flowerId);

        if (selectedFlower == null) {
            Toast.makeText(context, "Error: Flower not found", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get customer name (you might have to implement this based on your app's logic)
//        String customerName = "John Doe";  // Replace with actual customer name retrieval logic

        // Call the addOrder method in DatabaseHelper
        databaseHelper.addOrder(activeCusName, selectedFlower.getFlowerName(), selectedFlower.getPrice(), "Delivery Address");

        Toast.makeText(context, "Flower added to your order", Toast.LENGTH_SHORT).show();
    }


}
