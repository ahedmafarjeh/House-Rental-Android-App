package com.example.renthouses;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class RentalHistoryAgFragment extends Fragment {
    SharedPrefManager sharedPrefManager;
    public RentalHistoryAgFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rental_history_ag, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedPrefManager = SharedPrefManager.getInstance(getActivity());
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        Cursor values = dataBaseHelper.agencyHistory(sharedPrefManager.readString("email",""));
       // ToDoCursorAdapter adapter = new ToDoCursorAdapter(getActivity(),houses,0,false,false,false);
            AgencyHistoryAdapter adapter = new AgencyHistoryAdapter(getActivity(),values);
        ListView listView = getActivity().findViewById(R.id.rentalHistoryList);
        listView.setAdapter(adapter);
    }
}
