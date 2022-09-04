package com.example.renthouses;

import static androidx.core.app.ActivityCompat.startActivityForResult;
import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cursoradapter.widget.CursorAdapter;

import java.io.IOException;
import java.util.ArrayList;

public class ToDoCursorAdapter extends CursorAdapter  {
    SharedPrefManager sharedPrefManager;
    private static int RESULT_LOAD_IMAGE = 1;
    private boolean btnVisibil;
    private boolean editVisibil;
    private boolean txts_enable;
    private Bitmap store_Img;
    EditText email, city, postal_address, area, constructre_year, bedroom_num, price, status, available_date;
    EditText description, garden, balacony;
    ImageView img;
    public ToDoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }
     public interface OnClickImageListener{
        void onClick();
    }

    // The newView method is used to inflate a new view and return it,
// you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.single_search_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        sharedPrefManager = SharedPrefManager.getInstance(context);

        email = (EditText) view.findViewById(R.id.owner_email_item);
        city = (EditText) view.findViewById(R.id.city_item);
        postal_address = (EditText) view.findViewById(R.id.postal_address_item);
        area = (EditText) view.findViewById(R.id.surface_area_item);
        img = (ImageView) view.findViewById(R.id.img_item);
        constructre_year = (EditText) view.findViewById(R.id.construct_year_item);
        bedroom_num = (EditText) view.findViewById(R.id.bedroom_num_item);
        price = (EditText) view.findViewById(R.id.renta_price_item);
        status = (EditText) view.findViewById(R.id.status_item);
        available_date = (EditText) view.findViewById(R.id.availabilty_date_item);
        description = (EditText) view.findViewById(R.id.description_item);
        garden = (EditText) view.findViewById(R.id.has_gared_item);
        balacony = (EditText) view.findViewById(R.id.has_balcony_item);
        Button enable = (Button) view.findViewById(R.id.enableTexts);
        Button disable = (Button) view.findViewById(R.id.disableTexts);
        Button edit = (Button) view.findViewById(R.id.editProperity);
        Button remove = (Button) view.findViewById(R.id.remove);
        email.setText(cursor.getString(1));
        city.setText(cursor.getString(2));
        postal_address.setText(cursor.getString(3));
        area.setText(cursor.getString(4));
        byte[] imageBytes = cursor.getBlob(5);
        if (imageBytes != null) {
            // Pic image from database
            img.setImageBitmap(BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length));
        }
        constructre_year.setText(cursor.getString(6));
        bedroom_num.setText(cursor.getString(7));
        price.setText(cursor.getString(8));
        status.setText(cursor.getString(9));
        available_date.setText(cursor.getString(10));
        description.setText(cursor.getString(11));
        if (cursor.getString(12).equals("1"))
            garden.setText("Yes");
        else garden.setText("No");
        if (cursor.getString(13).equals("1"))
            balacony.setText("Yes");
        else balacony.setText("No");

        int pos = cursor.getPosition();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout rowView = (LinearLayout) v.getParent();
                getView(rowView, false);
                boolean g = false;
                boolean b = false;
                if (garden.getText().toString().equals("yes") || garden.getText().toString().equals("YES"))
                    g = true;
                if (balacony.getText().toString().equals("yes") || balacony.getText().toString().equals("YES"))
                    g = true;
                cursor.moveToPosition(pos);

                dataBaseHelper.update_Properity(cursor.getInt(0), email.getText().toString(), city.getText().toString(),
                        postal_address.getText().toString(), Integer.parseInt(area.getText().toString()), Integer.parseInt(constructre_year.getText().toString()),
                        Integer.parseInt(bedroom_num.getText().toString()), Integer.parseInt(price.getText().toString()), status.getText().toString(),
                        available_date.getText().toString(), description.getText().toString(), g, b);
                Intent intent = new Intent(context, Home_layout.class);
                startActivity(context, intent, null);
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor.moveToPosition(pos);
                boolean delete = dataBaseHelper.remove_Properity(cursor.getInt(0));
                if (delete == true) {
                    Toast.makeText(context, "Remove Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, Home_layout.class);
                    startActivity(context, intent, null);
                }
                else
                    Toast.makeText(context, "Remove Falied", Toast.LENGTH_SHORT).show();

            }
        });
        enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout rowView = (LinearLayout) v.getParent();
                getView(rowView, true);
                edit.setEnabled(true);
                enable.setVisibility(View.GONE);
                disable.setVisibility(View.VISIBLE);
            }
        });
        disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout rowView = (LinearLayout) v.getParent();
                getView(rowView, false);
                edit.setEnabled(false);
                enable.setVisibility(View.VISIBLE);
                disable.setVisibility(View.GONE);
            }
        });
    }

    public void getView(LinearLayout view, boolean isEnable) {
        email = (EditText) view.findViewById(R.id.owner_email_item);
        city = (EditText) view.findViewById(R.id.city_item);
        postal_address = (EditText) view.findViewById(R.id.postal_address_item);
        area = (EditText) view.findViewById(R.id.surface_area_item);
        img = (ImageView) view.findViewById(R.id.img_item);
        constructre_year = (EditText) view.findViewById(R.id.construct_year_item);
        bedroom_num = (EditText) view.findViewById(R.id.bedroom_num_item);
        price = (EditText) view.findViewById(R.id.renta_price_item);
        status = (EditText) view.findViewById(R.id.status_item);
        available_date = (EditText) view.findViewById(R.id.availabilty_date_item);
        description = (EditText) view.findViewById(R.id.description_item);
        garden = (EditText) view.findViewById(R.id.has_gared_item);
        balacony = (EditText) view.findViewById(R.id.has_balcony_item);
        city.setEnabled(isEnable);
        postal_address.setEnabled(isEnable);
        area.setEnabled(isEnable);
        constructre_year.setEnabled(isEnable);
        bedroom_num.setEnabled(isEnable);
        price.setEnabled(isEnable);
        status.setEnabled(isEnable);
        description.setEnabled(isEnable);
        available_date.setEnabled(isEnable);
        garden.setEnabled(isEnable);
        balacony.setEnabled(isEnable);

    }


}
