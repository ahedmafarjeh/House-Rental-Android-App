package com.example.renthouses;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class Home extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home2, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        Cursor values;
        values = dataBaseHelper.getLast5Post();
        SearchAdapter adapter = new SearchAdapter(getActivity(),values,getActivity(),true);
        ListView list = (ListView) getActivity().findViewById(R.id.last5post);
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);

}
}