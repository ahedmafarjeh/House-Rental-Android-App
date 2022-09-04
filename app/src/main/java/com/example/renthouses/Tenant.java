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

import com.google.android.material.navigation.NavigationView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Tenant extends Fragment {

    private static final Pattern[] inputRegexes = new Pattern[4];
    static {
        inputRegexes[0] = Pattern.compile(".*[A-Z].*");
        inputRegexes[1] = Pattern.compile(".*[a-z].*");
        inputRegexes[2] = Pattern.compile(".*[0-9].*");
        inputRegexes[3] = Pattern.compile(".*[!@#$%{}].*");
    }
    final boolean[] er_flag = new boolean[13];
    final String[] city = new String[1];
    EditText f_name;

    // TODO: Rename parameter arguments, choose names that match

    public Tenant() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tenant, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final String[] gender = new String[1];
        final String[] nationality = new String[1];
        final String[] country = new String[1];

        EditText email=(EditText) getActivity().findViewById(R.id.email_ag);
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(email.getText().toString().isEmpty()==true){
                    email.setError("field is empty");
                    er_flag[6]=false;
                }
                else er_flag[6]=true;
                if(isEmailValid(email.getText().toString())==false){
                    email.setError("email not valid");
                    er_flag[6]=false;
                }
                else er_flag[6]=true;
            }
        });

         f_name=(EditText) getActivity().findViewById(R.id.agency_name);
        f_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(f_name.getText().toString().isEmpty() == true){
                    f_name.setError("field is empty");
                    er_flag[0] =false;
                }
                else
                    er_flag[0] = true;
                if(f_name.getText().toString().length() < 3) {
                    f_name.setError("first name mmust be at min 3");
                    er_flag[0] =false;
                }
                else
                    er_flag[0] =true;
            }
        });

        EditText l_name=(EditText) getActivity().findViewById(R.id.last_name);
        l_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(l_name.getText().toString().isEmpty() == true){
                    l_name.setError("field is empty");
                    er_flag[1] =false;
                }
                else
                    er_flag[1]=true;
                if(l_name.getText().toString().length() < 3) {
                    l_name.setError("last name length mmust be at min 3");
                    er_flag[1] =false;
                }
                else
                    er_flag[1]=true;
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


        //gender spinner
        String[] options = { "","Male", "Female" };
        final Spinner genderSpinner =(Spinner) getActivity().findViewById(R.id.gender);
        ArrayAdapter objGenderArr = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, options);
        genderSpinner.setAdapter(objGenderArr);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender[0] = parent.getItemAtPosition(position).toString();
                if (gender[0] == "")
                    er_flag[4]=false;
                else er_flag[4]=true;
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //nationality spinner
        options = new String[]{"", "Palestinian", "Jordinian","Syrian","Iraqian","Sudian","American","French","Brazilian","German","Spanish"};
        final Spinner nationSpinner =(Spinner) getActivity().findViewById(R.id.nationality);
        ArrayAdapter objnationArr = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, options);
        nationSpinner.setAdapter(objnationArr);
        nationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nationality[0] = parent.getItemAtPosition(position).toString();
                if (nationality[0] == "")
                    er_flag[5]=false;
                else er_flag[5]=true;
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //country spinner
        options = new String[]{ "","Palestine", "Jordan","Syria","Iraq","Sudia","America","French","Brazil","Germany","Spain"};
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
                    er_flag[7]=false;
                else er_flag[7]=true;
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

        // salary
        EditText salary = (EditText) getActivity().findViewById(R.id.salary);
        salary.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (salary.getText().toString().isEmpty() == true){
                    salary.setError("field is empty");
                    er_flag[8]=false;
                }
                else er_flag[8]=true;
            }
        });

        //ocupation
        EditText ocupy = (EditText) getActivity().findViewById(R.id.ocupation);
        ocupy.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (ocupy.getText().toString().isEmpty() == true){
                    ocupy.setError("field is empty");
                    er_flag[9]=false;
                }
                else er_flag[9]=true;
            }
        });

        //family size
        EditText family_size = (EditText) getActivity().findViewById(R.id.family_size);
        family_size.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (family_size.getText().toString().isEmpty() == true){
                    family_size.setError("field is empty");
                    er_flag[10]=false;
                }
                else er_flag[10]=true;
            }
        });
        //phone number
        EditText phone_num2=(EditText) getActivity().findViewById(R.id.phone_num2_agency);
        phone_num2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (phone_num2.getText().toString().isEmpty() == true){
                    phone_num2.setError("field is empty");
                    er_flag[12]=false;
                }
                else er_flag[12]=true;
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
                   /* else {
                        Toast.makeText(getActivity(), "there is an empty field or other error "+i, Toast.LENGTH_SHORT).show();

                    }*/
                }
                if (count == er_flag.length) {
                    //DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity(), "Tenant", null, 2);
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
                    boolean flag = dataBaseHelper.insert_Tenant_User(email.getText().toString(),f_name.getText().toString(),l_name.getText().toString(),
                            gender[0],password.getText().toString(),nationality[0],Integer.parseInt(salary.getText().toString()),ocupy.getText().toString(),
                            Integer.parseInt(family_size.getText().toString()),country[0],city[0],phone_num2.getText().toString() );
                    if(flag == true) {
                        Toast.makeText(getActivity(), "register succeseded", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(),SignIn.class);
                        getActivity().startActivity(intent);
                        getActivity().finish();

                    }
                    else
                        Toast.makeText(getActivity(),"error in database",Toast.LENGTH_SHORT).show();

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
                city[0] = parent.getItemAtPosition(position).toString();
                if (city[0] == "")
                    er_flag[11]=false;
                else er_flag[11]=true;
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private static boolean isMatchingRegex(String input) {
        boolean inputMatches = true;
        for (Pattern inputRegex : inputRegexes) {
            if (!inputRegex.matcher(input).matches()) {
                inputMatches = false;
            }
        }
        return inputMatches;
    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}