package com.example.renthouses;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Currency;

public class RequestFragment extends Fragment {
    public RequestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        SharedPrefManager sharedPrefManager;
        ListView listView = (ListView) getActivity().findViewById(R.id.request_listview);
        sharedPrefManager = SharedPrefManager.getInstance(getActivity());
        Cursor values;
        values = dataBaseHelper.search(sharedPrefManager.readString("email","noValue"),"Notification");
        if (values == null)
            Toast.makeText(getActivity(),"there is no value", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(getActivity(), "okaaaay", Toast.LENGTH_SHORT).show();
            RequestCursorAdapter adapter = new RequestCursorAdapter(getActivity(), values);
            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);
        }

    }
}