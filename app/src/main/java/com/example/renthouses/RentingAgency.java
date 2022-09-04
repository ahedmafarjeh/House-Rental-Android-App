package com.example.renthouses;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RentingAgency extends Fragment {

    private static final Pattern[] inputRegexes = new Pattern[4];
    static {
        inputRegexes[0] = Pattern.compile(".*[A-Z].*");
        inputRegexes[1] = Pattern.compile(".*[a-z].*");
        inputRegexes[2] = Pattern.compile(".*[0-9].*");
        inputRegexes[3] = Pattern.compile(".*[!@#$%{}].*");
    }
    final boolean[] er_flag = new boolean[7];
    final String[] city = new String[1];


    public RentingAgency() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_renting_agency, container, false);

    }
    // our main code

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final String[] country = new String[1];
        EditText email=(EditText) getActivity().findViewById(R.id.email_ag);
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(email.getText().toString().isEmpty()==true){
                    email.setError("field is empty");
                    er_flag[0]=false;
                }
                else er_flag[0]=true;
                if(isEmailValid(email.getText().toString())==false){
                    email.setError("email not valid");
                    er_flag[0]=false;
                }
                else er_flag[0]=true;
            }
        });

        EditText agency_name=(EditText) getActivity().findViewById(R.id.agency_name);
        agency_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(agency_name.getText().toString().isEmpty() == true){
                    agency_name.setError("field is empty");
                    er_flag[1] =false;
                }
                else
                    er_flag[1] = true;

            }
        });

        EditText password=(EditText) getActivity().findViewById(R.id.pasword_agency);
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(password.getText().toString().isEmpty() == true){
                    password.setError("field is empty");
                    er_flag[2]=false;
                }
                else er_flag[2]=true;
                if (password.getText().toString().length() < 8 ) {
                    password.setError("password length must be at min 8");
                    er_flag[2]=false;
                }
                else  er_flag[2]=true;
                boolean b=isMatchingRegex(password.getText().toString());
                if (b == false){
                    password.setError("password must contain at least:uper,lower,numer, one of @#$%!{}");
                    er_flag[2]=false;
                }
                else  er_flag[2] =true;
            }
        });

        EditText conf_pass=(EditText) getActivity().findViewById(R.id.confirm_pass_agency);
        conf_pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!conf_pass.getText().toString().equals(password.getText().toString())) {
                    conf_pass.setError("Dose not match password");
                    er_flag[3]=false;
                }
                else er_flag[3]=true;
            }
        });

        //country spinner
        String[] options = { "","Palestine", "Jordan","Syria","Iraq","Sudia","America","French","Brazil","Germany","Spain"};
        final Spinner cntrySpinner =(Spinner) getActivity().findViewById(R.id.country_agency);
        ArrayAdapter objcntryArr = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, options);
        cntrySpinner.setAdapter(objcntryArr);

        //city spinner
        String[] pales_city={"","jurusalem","Ramallah", "Nablus","Jeneen","Khaleel"};
        String[] jordn_city={"","Amman","Zarqa", "Irbid","Russeifa","Sahab"};
        String[] syria_city={"","Halab","Hems", "Damascus","Daraa","AL-Mayadin"};
        String[] Iraq_city={"","Baghdad","Samarra", "Hit","Hilla","Karballa"};
        String[] sudia_city={"","Riyadh","jeddah", "medina","Hilla","Tabuk"};
        String[] amrica_city={"","New Yourk","Los Angelles", "Chicago","",""};
        String[] french_city={"","Paris","Lyon", "Nantes","",""};
        String[] brzil_city={"","Reo da janiro","Manaus", "Fortaleza","",""};
        String[] germn_city={"","Berlin","Hamburg", "Munich","",""};
        String[] spain_city={"","Barcelona","Madrid", "Alicante","",""};

        EditText ph= (EditText) getActivity().findViewById(R.id.phone_num_agency);

        cntrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country[0] = parent.getItemAtPosition(position).toString();
                if (country[0] == "")
                    er_flag[4]=false;
                else er_flag[4]=true;
                if (country[0].equals("Palestine")){
                    city_Fill(pales_city);
                    ph.setText("00970");
                }
                else if (country[0].equals("Jordan")){
                    city_Fill(jordn_city);
                    ph.setText("00962");
                }
                else if (country[0].equals("Syria")){
                    city_Fill(syria_city);
                    ph.setText("00963");
                }
                else if (country[0].equals("Iraq")){
                    city_Fill(Iraq_city);
                    ph.setText("31004");
                }
                else if (country[0].equals("Sudia")){
                    city_Fill(sudia_city);
                    ph.setText("11564");
                }
                else if (country[0].equals("America")){
                    city_Fill(amrica_city);
                    ph.setText("35004");
                }
                else if (country[0].equals("French")){
                    city_Fill(french_city);
                    ph.setText("75001");
                }
                else if (country[0].equals("Brazil")){
                    city_Fill(brzil_city);
                    ph.setText("57000");
                }
                else if (country[0].equals("Germany")){
                    city_Fill(germn_city);
                    ph.setText("97839");
                }
                else if (country[0].equals("Spain")){
                    city_Fill(spain_city);
                    ph.setText("52006");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //phone number
        EditText phone_num2=(EditText) getActivity().findViewById(R.id.phone_num2_agency);
        phone_num2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (phone_num2.getText().toString().isEmpty() == true){
                    phone_num2.setError("field is empty");
                    er_flag[5]=false;
                }
                else er_flag[5]=true;
            }
        });
        //sign up button
        Button signUp= (Button) getActivity().findViewById(R.id.signup_agency);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                for (int i=0;i<er_flag.length;i++){
                    if (er_flag[i]==true) {
                        ++count;
                    }
                    else {
                        Toast.makeText(getActivity(), "there is an empty field or other error "+i, Toast.LENGTH_SHORT).show();

                    }
                }
                if (count == er_flag.length) {
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
                    boolean flag = dataBaseHelper.insert_RentingAgency_User(email.getText().toString(), agency_name.getText().toString(),password.getText().toString() ,country[0], city[0], phone_num2.getText().toString());
                    if (flag == true) {
                        Toast.makeText(getActivity(), "register succeseded", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(),SignIn.class);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    }
                    else
                        Toast.makeText(getActivity(), "error in database", Toast.LENGTH_SHORT).show();
                }
                 else
                   Toast.makeText(getActivity(), "there is an empty field or other error", Toast.LENGTH_SHORT).show();


            }
        });

    }
    public void city_Fill(String[] c){
        String [] options ={ c[0],c[1], c[2],c[3],c[4],c[5]};
        final Spinner citySpinner = (Spinner) getActivity().findViewById(R.id.city_agency);
        ArrayAdapter objcityArr = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, options);
        citySpinner.setAdapter(objcityArr);
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city[0]= parent.getItemAtPosition(position).toString();
                if (city[0] == "")
                    er_flag[6]=false;
                else er_flag[6]=true;
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    // check password
    private static boolean isMatchingRegex(String input) {
        boolean inputMatches = true;
        for (Pattern inputRegex : inputRegexes) {
            if (!inputRegex.matcher(input).matches()) {
                inputMatches = false;
            }
        }
        return inputMatches;
    }
    // check validity of email
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}