package com.example.renthouses;

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

public class TenantHistoryFragment extends Fragment {


    public TenantHistoryFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tenant_history, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPrefManager sharedPrefManager;
        sharedPrefManager = SharedPrefManager.getInstance(getActivity());
        String tenant_email;
        if (sharedPrefManager.readString("userFlag","noValue").equals("true"))
            tenant_email = sharedPrefManager.readString("email","noValue");
        else
            tenant_email = getArguments().getString("Temail");

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        Cursor values;
        //values = dataBaseHelper.house_City_PostalAdress_Search(Integer.parseInt(sharedPrefManager.readString("houseID","noValue")));
        values = dataBaseHelper.tenantHistory(tenant_email);
        TenantHistoryAdapter adapter = new TenantHistoryAdapter(getActivity(),values);
        ListView listView = (ListView) getActivity().findViewById(R.id.tenant_history_list);
        listView.setAdapter(adapter);


    }
}