package com.example.renthouses;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

public class ApproveRequestFragment extends Fragment {


    public ApproveRequestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reuest_ansert, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(getActivity());
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
       Cursor values = dataBaseHelper.requestAnswer(sharedPrefManager.readString("email", "noValue"));
        ListView listView = (ListView) getActivity().findViewById(R.id.approveList);
        //ToDoCursorAdapter adapter = new ToDoCursorAdapter(getActivity(), values,0,false,false,false);
        SearchAdapter adapter = new SearchAdapter(getActivity(),values,getActivity(),false);
        listView.setAdapter(adapter);
    }
}