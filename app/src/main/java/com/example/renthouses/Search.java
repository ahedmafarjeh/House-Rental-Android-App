package com.example.renthouses;

import static com.example.renthouses.PostFragment.RESULT_LOAD_IMAGE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class Search extends Fragment{
    boolean is_garden,is_balcony;
    RadioButton garden,balcony,nogarden,nobalcony;

    public Search() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EditText city = (EditText)getActivity().findViewById(R.id.Scity);
        EditText minArea = (EditText)getActivity().findViewById(R.id.Smin_area);
        EditText maxArea = (EditText)getActivity().findViewById(R.id.Smax_area);
        EditText min_Bedroom_Num = (EditText)getActivity().findViewById(R.id.Smin_bedrrom);
        EditText max_Bedroom_Num = (EditText)getActivity().findViewById(R.id.Smax_bedroom);
        EditText price = (EditText)getActivity().findViewById(R.id.Srental_price);
        //EditText status = (EditText)getActivity().findViewById(R.id.Sstatus);
        Button search = (Button)getActivity().findViewById(R.id.searchBT);
        Button back = (Button)getActivity().findViewById(R.id.backtoSearch);
        RadioGroup gard = (RadioGroup)getActivity().findViewById(R.id.gardenGroup);
        RadioGroup balc = (RadioGroup)getActivity().findViewById(R.id.balconyGroup);
        garden=(RadioButton) getActivity().findViewById(R.id.with_garden);
        balcony=(RadioButton) getActivity().findViewById(R.id.with_balcony);
        nogarden=(RadioButton) getActivity().findViewById(R.id.no_garden);
        nobalcony=(RadioButton) getActivity().findViewById(R.id.no_balcony);
        LinearLayout search_layout = getActivity().findViewById(R.id.search_layout);
        LinearLayout result = (LinearLayout) getActivity().findViewById(R.id.searchResult);
        garden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_garden = true;
            }
        });
        balcony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_balcony = true;
            }
        });
        nogarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_garden = false;
            }
        });
        nobalcony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_balcony=false;
            }
        });
        final String status[] = new String[1];
        String[] options = { "Select Status","furnished", "unfurnished" };
        final Spinner statusSpinner =(Spinner) getActivity().findViewById(R.id.statusSpinner);
        ArrayAdapter objGenderArr = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, options){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    //Disable the third item of spinner.
                    return false;
                }
                else
                {
                    return true;
                }
            }

        };
        statusSpinner.setAdapter(objGenderArr);
        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                status[0] = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(),status[0],Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor values;
                if ( city.getText().toString().isEmpty() || minArea.getText().toString().isEmpty() || maxArea.getText().toString().isEmpty()
                        ||min_Bedroom_Num.getText().toString().isEmpty() ||max_Bedroom_Num.getText().toString().isEmpty() ||
                        price.getText().toString().isEmpty()|| status[0]=="Select Status" ){
                    Toast.makeText(getActivity(),"There is empty field or don't select status",Toast.LENGTH_SHORT).show();
                }
                else {
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
                    values = dataBaseHelper.home_Search(city.getText().toString(), Integer.parseInt(minArea.getText().toString()),
                            Integer.parseInt(maxArea.getText().toString()),Integer.parseInt(min_Bedroom_Num.getText().toString()),
                            Integer.parseInt(max_Bedroom_Num.getText().toString()),Integer.parseInt(price.getText().toString()),status[0],is_garden,is_balcony);
                    Toast.makeText(getActivity(), "search ...", Toast.LENGTH_SHORT).show();
                    if (values == null)
                        Toast.makeText(getActivity(),"There is no house with respect to your requirments",Toast.LENGTH_LONG).show();
                    else {
                        Toast.makeText(getActivity(),"house is found",Toast.LENGTH_SHORT).show();
                        ListView listView = getActivity().findViewById(R.id.listVeiw);
                        city.getText().clear();
                        min_Bedroom_Num.getText().clear();
                        max_Bedroom_Num.getText().clear();
                        minArea.getText().clear();
                        maxArea.getText().clear();
                        price.getText().clear();

                        gard.clearCheck();
                        balc.clearCheck();
                        search_layout.setVisibility(View.GONE);
                        back.setVisibility(View.VISIBLE);
                        result.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.VISIBLE);
                        //SingleItem singleItem = new SingleItem(getActivity());
                       // ToDoCursorAdapter adapter = new ToDoCursorAdapter(getActivity(),values,0,true,true,true);
                        SearchAdapter adapter = new SearchAdapter(getActivity(),values, getActivity(),true);
                        adapter.notifyDataSetChanged();
                        listView.setAdapter(adapter);


                    }

                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setVisibility(View.GONE);
                search_layout.setVisibility(View.VISIBLE);
                back.setVisibility(View.GONE);

             }
        });

    }

}
