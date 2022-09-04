package com.example.renthouses;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class ViewHousesFragment extends Fragment {

    public ViewHousesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_houses, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        Button back = (Button) getActivity().findViewById(R.id.back_to_sign);
        ListView list = (ListView) getActivity().findViewById(R.id.viewHousesList);
        Cursor values;
        values = dataBaseHelper.getAllValues("HOUSE_Info");
        values.moveToFirst();
        SearchAdapter adapter = new SearchAdapter(getActivity(),values,getActivity(),false);
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SignIn.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}