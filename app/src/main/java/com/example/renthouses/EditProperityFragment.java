package com.example.renthouses;


import static com.example.renthouses.PostFragment.RESULT_LOAD_IMAGE;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.IOException;


public class EditProperityFragment extends Fragment implements ToDoCursorAdapter.OnClickImageListener {
    SharedPrefManager sharedPrefManager;

    public EditProperityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_properity, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedPrefManager = SharedPrefManager.getInstance(getActivity());
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        Cursor houses = dataBaseHelper.search(sharedPrefManager.readString("email", "noValue"), "HOUSE_Info");
        ListView listView = getActivity().findViewById(R.id.EDIT_PROERITY_LIST);
        ToDoCursorAdapter adapter = new ToDoCursorAdapter(getActivity(), houses);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }
    @Override
    public void onClick() {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            ImageView imageView = (ImageView) getActivity().findViewById(R.id.imgView);
            try {
                Bitmap store_Img = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                imageView.setImageBitmap(store_Img);
            } catch (IOException e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


}