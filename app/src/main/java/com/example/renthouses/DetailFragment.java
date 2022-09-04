package com.example.renthouses;

import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class DetailFragment extends Fragment {


    private int id;
    private boolean applyVisiblity;
    public DetailFragment(int id) {
        // Required empty public constructor
        this.id=id;
        this.applyVisiblity=applyVisiblity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(getActivity());
        EditText email = (EditText) getActivity().findViewById(R.id.owner_email_newitem);
        EditText city = (EditText) getActivity().findViewById(R.id.city_newitem);
        EditText postal_address = (EditText) getActivity().findViewById(R.id.postal_address_newitem);
        EditText area = (EditText) getActivity().findViewById(R.id.surface_area_newitem);
        ImageView img = (ImageView) getActivity().findViewById(R.id.img_newitem);
        EditText constructre_year = (EditText) getActivity().findViewById(R.id.construct_year_newitem);
        EditText bedroom_num = (EditText) getActivity().findViewById(R.id.bedroom_num_newitem);
        EditText price = (EditText) getActivity().findViewById(R.id.renta_price_newitem);
        EditText status = (EditText) getActivity().findViewById(R.id.status_newitem);
        EditText available_date = (EditText) getActivity().findViewById(R.id.availabilty_date_newitem);
        EditText description = (EditText) getActivity().findViewById(R.id.description_newitem);
        EditText garden = (EditText) getActivity().findViewById(R.id.has_gared_newitem);
        EditText balacony = (EditText) getActivity().findViewById(R.id.has_balcony_newitem);
        Cursor cursor = dataBaseHelper.findCityPostal(id);
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

    }
}