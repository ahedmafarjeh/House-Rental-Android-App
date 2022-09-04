package com.example.renthouses;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class profile extends Fragment {

    SharedPrefManager sharedPrefManager;

    public profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        TextView eml = (TextView) getActivity().findViewById(R.id.eml);
        EditText fname = (EditText) getActivity().findViewById(R.id.fname);
        TextView TorAgname = (TextView) getActivity().findViewById(R.id.TorAg_name);
        EditText lname = ( EditText) getActivity().findViewById(R.id.lname);
        EditText gnder = ( EditText) getActivity().findViewById(R.id.gend);
        EditText nationality = ( EditText) getActivity().findViewById(R.id.nation);
        EditText salary = ( EditText) getActivity().findViewById(R.id.slry);
        EditText occupation = ( EditText) getActivity().findViewById(R.id.ocup);
        EditText f_size = ( EditText) getActivity().findViewById(R.id.familysize);
        EditText country = ( EditText) getActivity().findViewById(R.id.cntry);
        EditText city = ( EditText) getActivity().findViewById(R.id.cty);
        EditText phone = ( EditText) getActivity().findViewById(R.id.ph_num);
        Button edit = (Button) getActivity().findViewById(R.id.edit);
        Button apply = (Button) getActivity().findViewById(R.id.apply);
        sharedPrefManager =SharedPrefManager.getInstance(getActivity());
        if (sharedPrefManager.readString("userFlag","noValue").equals("true")) {
            eml.setText(sharedPrefManager.readString("email", "noValue"));
            TorAgname.setText("First Name");
            fname.setText(sharedPrefManager.readString("fname", "noValue"));
            lname.setText(sharedPrefManager.readString("lname", "noValue"));
            gnder.setText(sharedPrefManager.readString("gender", "noValue"));
            nationality.setText(sharedPrefManager.readString("nationality", "noValue"));
            salary.setText(sharedPrefManager.readString("salary", "noValue"));
            occupation.setText(sharedPrefManager.readString("occupation", "noValue"));
            f_size.setText(sharedPrefManager.readString("familySize", "noValue"));
            country.setText(sharedPrefManager.readString("country", "noValue"));
            city.setText(sharedPrefManager.readString("city", "noValue"));
            phone.setText(sharedPrefManager.readString("phone", "noValue"));
        }
        else{
            LinearLayout lastName_layout= getActivity().findViewById(R.id.l_name_layout);
            LinearLayout gender_layout= getActivity().findViewById(R.id.gender_layout);
            LinearLayout nationality_layout= getActivity().findViewById(R.id.nation_layout);
            LinearLayout salary_layout= getActivity().findViewById(R.id.salary_layout);
            LinearLayout occupation_layout= getActivity().findViewById(R.id.occupation_layout);
            LinearLayout familySize_layout= getActivity().findViewById(R.id.family_layout);
            lastName_layout.setVisibility(View.GONE);
            gender_layout.setVisibility(View.GONE);
            nationality_layout.setVisibility(View.GONE);
            salary_layout.setVisibility(View.GONE);
            occupation_layout.setVisibility(View.GONE);
            familySize_layout.setVisibility(View.GONE);
            eml.setText(sharedPrefManager.readString("email", "noValue"));
            TorAgname.setText("Agency Name");
            fname.setText(sharedPrefManager.readString("AgencyName", "noValue"));
            country.setText(sharedPrefManager.readString("country", "noValue"));
            city.setText(sharedPrefManager.readString("city", "noValue"));
            phone.setText(sharedPrefManager.readString("phone", "noValue"));



        }
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname.setEnabled(true);
                lname.setEnabled(true);
                gnder.setEnabled(true);
                nationality.setEnabled(true);
                occupation.setEnabled(true);
                salary.setEnabled(true);
                country.setEnabled(true);
                city.setEnabled(true);
                phone.setEnabled(true);
                f_size.setEnabled(true);
                apply.setVisibility(View.VISIBLE);

            }
        });
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPrefManager.readString("userFlag","noValue").equals("true")) {


                        dataBaseHelper.update_Tenant(eml.getText().toString(),"TENANT_USER",fname.getText().toString(),lname.getText().toString()
                        ,gnder.getText().toString(),nationality.getText().toString(),Integer.parseInt(salary.getText().toString()),
                                occupation.getText().toString(),Integer.parseInt(f_size.getText().toString()),country.getText().toString(),city.getText().toString()
                        ,phone.getText().toString());
                    fname.setEnabled(false);
                    lname.setEnabled(false);
                    gnder.setEnabled(false);
                    nationality.setEnabled(false);
                    occupation.setEnabled(false);
                    salary.setEnabled(false);
                    country.setEnabled(false);
                    city.setEnabled(false);
                    phone.setEnabled(false);
                    f_size.setEnabled(false);
                    apply.setVisibility(View.INVISIBLE);
                }
                else {
                    dataBaseHelper.update_RentalAgency(eml.getText().toString(),fname.getText().toString(),
                            country.getText().toString(), city.getText().toString(),phone.getText().toString());
                    eml.setEnabled(false);
                    fname.setEnabled(false);
                    country.setEnabled(false);
                    city.setEnabled(false);
                    phone.setEnabled(false);
                    apply.setVisibility(View.INVISIBLE);
                }
            }
        });


    }

}