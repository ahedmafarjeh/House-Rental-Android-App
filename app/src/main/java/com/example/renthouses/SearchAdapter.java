package com.example.renthouses;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SearchAdapter extends CursorAdapter {
    private static Activity activity;
    private static boolean applytVisibility;

    public SearchAdapter(Context context, Cursor c, Activity activity1, boolean applytVisibility) {
        super(context, c, 0);
        activity = activity1;
        this.applytVisibility = applytVisibility;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.search_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        SharedPrefManager sharedPrefManager;
        sharedPrefManager = SharedPrefManager.getInstance(context);
        ImageView img = (ImageView) view.findViewById(R.id.img_search_item);
        TextView city = (TextView) view.findViewById(R.id.search_city);
        TextView address = (TextView) view.findViewById(R.id.search_address);
        TextView bedroom_num = (TextView) view.findViewById(R.id.search_bedroomNum);
        TextView area = (TextView) view.findViewById(R.id.search_area);
        Button viewBt = (Button) view.findViewById(R.id.veiw_search);
        Button apply = (Button) view.findViewById(R.id.applyButton);
        city.setText("City: " + cursor.getString(2));
        address.setText("Postal Address: " + cursor.getString(3));
        bedroom_num.setText("Bedroom Number: " + cursor.getString(7));
        area.setText("Surface Area: " + cursor.getString(4));
        byte[] imageBytes = cursor.getBlob(5);
        if (imageBytes != null) {
            // Pic image from database
            img.setImageBitmap(BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length));
        }
        int pos = cursor.getPosition();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        if (applytVisibility == false)
            apply.setVisibility(View.GONE);
        viewBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor.moveToPosition(pos);
                LinearLayout rowView = (LinearLayout) v.getParent();
                int id = cursor.getInt(0);
                cursor.moveToPosition(pos);
                //sharedPrefManager.writeString("hID",id.getText().toString().substring(4));
                DetailFragment fragment = new DetailFragment(id);
                FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.root4, fragment).commit();
            }
        });
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor.moveToPosition(pos);
                String owner_email = cursor.getString(1);
                LinearLayout row = (LinearLayout) v.getParent();
                int id = cursor.getInt(0);
                boolean isInsert = dataBaseHelper.searchById(cursor.getInt(0), "TENANT_HOUSE");
                boolean found = dataBaseHelper.notificationIdSearch(id, sharedPrefManager.readString("email", "noValue"));
                if (isInsert == false) {
                    if (found == false) {
                        boolean insert = dataBaseHelper.insert_Notification(owner_email, sharedPrefManager.readString("email", "noValue"), id);
                        if (insert == true)
                            Toast.makeText(context, "insert success", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(context, "insert failed", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(context, "you are chose it already " + cursor.getInt(0) + " " + sharedPrefManager.readString("email", "noValue"), Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(context, "house is rented", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
