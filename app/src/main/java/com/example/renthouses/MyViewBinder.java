package com.example.renthouses;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MyViewBinder implements SimpleCursorAdapter.ViewBinder {
    private Context context;
    public MyViewBinder(Context context1){
        context=context1;
    }
    @Override
    public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
        int viewID = view.getId();
        switch(viewID){

            case R.id.owner_email_item:
                TextView email = (TextView) view;
                String eml;
                eml = cursor.getString(1);
                email.setText("Owner Email: "+eml);
                break;

            case R.id.city_item:
                TextView city = (TextView) view;
                String cty = cursor.getString(2);
                city.setText("City: "+cty);
                break;

            case R.id.postal_address_item:
                TextView postal = (TextView) view;
                String p = cursor.getString(3);
                postal.setText("Postal Address: "+p);
                break;
            case R.id.surface_area_item:
                TextView area = (TextView) view;
                String a = cursor.getString(4);
                area.setText("Surface Area: "+a);
                break;

            case R.id.img_item:
                ImageView contactProfile = (ImageView) view;
                byte[] imageBytes = cursor.getBlob(5);
                if(imageBytes != null ){
                    // Pic image from database
                    contactProfile.setImageBitmap(BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length));
                }
                break;
            case R.id.construct_year_item:
                TextView year = (TextView) view;
                String y = cursor.getString(6);
                year.setText("Construction Year: "+y);
                break;
            case R.id.bedroom_num_item:
                TextView bednum = (TextView) view;
                String bed = cursor.getString(7);
                bednum.setText("Number of Bedroom: "+bed);
                break;
            case R.id.renta_price_item:
                TextView price = (TextView) view;
                String pr = cursor.getString(8);
                price.setText("Rental Price: "+pr);
                break;
            case R.id.status_item:
                TextView status = (TextView) view;
                String stat= cursor.getString(9);
                status.setText("Status(f(furnished)/un(unfurnished)): "+stat);
                break;
            case R.id.availabilty_date_item:
                TextView data = (TextView) view;
                String dte = cursor.getString(10);
                data.setText("Availability Date: "+dte);
                break;
            case R.id.description_item:
                TextView des = (TextView) view;
                String descrip = cursor.getString(11);
                des.setText("Description: "+descrip);
                break;
            case R.id.has_gared_item:
                TextView garden = (TextView) view;
                String isgarden = cursor.getString(12);
                if (isgarden.equals("1"))
                    garden.setText("Has Garden: yes");
                else
                    garden.setText("Has Garden: no");

                break;
            case R.id.has_balcony_item:
                TextView balcony = (TextView) view;
                String isbalc = cursor.getString(13);
                if (isbalc.equals("1"))
                    balcony.setText("Has Balcony: yes");
                else
                    balcony.setText("Has Has Balcony: no");
                break;

        }
        return true;
    }


}