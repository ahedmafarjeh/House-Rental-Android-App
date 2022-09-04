package com.example.renthouses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    private CheckBox remember_me;
    private EditText mail;
    private EditText pasword;
    private int check_num = 0;
    DataBaseHelper dataBaseHelper = new DataBaseHelper(SignIn.this);
    //private SharedPreferences mPrefs;
    //private static final String PREFS_NAME="PrefsFile";
    SharedPrefManager sharedPrefManager;
    Cursor values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mail = (EditText) findViewById(R.id.email);
        pasword = (EditText) findViewById(R.id.password);
        remember_me = (CheckBox) findViewById(R.id.remember);
        Button signin = (Button) findViewById(R.id.sign_in);
        Button signup = (Button) findViewById(R.id.sign_up);
        Button viewHouses = (Button) findViewById(R.id.showHouses);
        LinearLayout sign_layout = (LinearLayout) findViewById(R.id.sign_in_layout);
        sharedPrefManager = SharedPrefManager.getInstance(this);
        //remember_me.setChecked(true);

        //boolean flag = dataBaseHelper.insertUser("ahed@gmail.com", "123456");
        //boolean flag1 = dataBaseHelper.insertUser("ahed1@gmail.com", "123456");
        //boolean flag2 = dataBaseHelper.insertUser("ahed2@gmail.com", "123456");
        //pasword.setText(String.valueOf(flag2));
        //showData();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //DataBaseHelper dataBaseHelper=new DataBaseHelper(MainActivity.this,"Project",null,1);
                //DataBaseHelper dataBaseHelper = new DataBaseHelper(SignIn.this);
                values = dataBaseHelper.search(mail.getText().toString(), "TENANT_USER");
                if (values == null) {
                  //  dataBaseHelper = new DataBaseHelper(SignIn.this);
                    values = dataBaseHelper.search(mail.getText().toString(), "RENTING_AGENCY_USER");
                    if (values == null)
                        Toast.makeText(SignIn.this, "email not registerded", Toast.LENGTH_SHORT).show();
                    else {
                        if (values.getString(2).equals(pasword.getText().toString())) {
                            sharedPrefManager.writeString("userFlag", "false");
                            Toast.makeText(SignIn.this, "Sucessfull :Renting Agency User", Toast.LENGTH_SHORT).show();
                            if (remember_me.isChecked() ) {
                                sharedPrefManager.writeString("Email", mail.getText().toString());
                                sharedPrefManager.writeString("Password", pasword.getText().toString());

                                Toast.makeText(SignIn.this, "Acount hav been saved",
                                        Toast.LENGTH_SHORT).show();
                            }
                            go_To_Hmoe_Layout();
                            mail.getText().clear();
                            pasword.getText().clear();
                            remember_me.setChecked(false);
                        }
                        else
                            Toast.makeText(SignIn.this,"Incorrect Password",Toast.LENGTH_SHORT).show();

                    }
                } else {
                    if (values.getString(4).equals(pasword.getText().toString())) {
                        sharedPrefManager.writeString("userFlag", "true");
                        Toast.makeText(SignIn.this, "Sucessfull: Tenant User", Toast.LENGTH_SHORT).show();
                        if (remember_me.isChecked() ) {
                            sharedPrefManager.writeString("Email", mail.getText().toString());
                            sharedPrefManager.writeString("Password", pasword.getText().toString());
                            Toast.makeText(SignIn.this, "Acount hav been saved",
                                    Toast.LENGTH_SHORT).show();
                        }
                        go_To_Hmoe_Layout();
                        mail.getText().clear();
                        pasword.getText().clear();
                        remember_me.setChecked(false);
                    }
                    else
                        Toast.makeText(SignIn.this,"Incorrect Password",Toast.LENGTH_SHORT).show();

                }

            }
        });

        remember_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (remember_me.isChecked()) {
                    check_num++;
                    //  if(check_num > 1)
                    if (mail.getText().toString().isEmpty() && pasword.getText().toString().isEmpty())
                        getPreferencesData();
                } else {
                    mail.setText("");
                    pasword.setText("");
                }


            }

        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                SignIn.this.startActivity(intent);
                finish();

            }
        });
        viewHouses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_layout.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.guestView,new ViewHousesFragment());
                fragmentTransaction.commit();
            }
        });
    }
    private void getPreferencesData() {
        mail.setText(sharedPrefManager.readString("Email", "noValue"));

        pasword.setText(sharedPrefManager.readString("Password", "noValue"));

    }

    public void showData() {
        //DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        Cursor allUsers = dataBaseHelper.getAllValues("Notification");
        TextView data = (TextView) findViewById(R.id.data);
        data.setMovementMethod(new ScrollingMovementMethod());
        /*if (allUsers != null && allUsers.getCount() > 0) {
            allUsers.moveToFirst();
            textView.setText("start ...");
        }*/
        allUsers.moveToFirst();
        StringBuilder str = new StringBuilder();
        do {
            //int id = allUsers.getInt(0);
            String email = allUsers.getString(0);
            String fname = allUsers.getString(2);
            String lname = allUsers.getString(1);
            //String pass = allUsers.getString(1);
            str.append("agncy mail: " + email + "  id:  " + fname + " ten email " + lname + "\n");
        } while (allUsers.moveToNext());
        //TextView textView =(TextView)findViewById(R.id.data);
        data.setText(str.toString());

    }
    public void go_To_Hmoe_Layout(){
        if ( sharedPrefManager.readString("userFlag","noValue").equals("true")) {
            sharedPrefManager.writeString("email", mail.getText().toString());
            sharedPrefManager.writeString("password", pasword.getText().toString());
            sharedPrefManager.writeString("fname", values.getString(1));
            sharedPrefManager.writeString("lname", values.getString(2));
            sharedPrefManager.writeString("gender", values.getString(3));
            sharedPrefManager.writeString("nationality", values.getString(5));
            sharedPrefManager.writeString("salary", values.getString(6));
            sharedPrefManager.writeString("occupation", values.getString(7));
            sharedPrefManager.writeString("familySize", values.getString(8));
            sharedPrefManager.writeString("country", values.getString(9));
            sharedPrefManager.writeString("city", values.getString(10));
            sharedPrefManager.writeString("phone", values.getString(11));
        }
        else {
            sharedPrefManager.writeString("email", mail.getText().toString());
            sharedPrefManager.writeString("password", pasword.getText().toString());
            sharedPrefManager.writeString("AgencyName", values.getString(1));
            sharedPrefManager.writeString("country", values.getString(3));
            sharedPrefManager.writeString("city", values.getString(4));
            sharedPrefManager.writeString("phone", values.getString(5));

        }
        check_num++;
        Intent intent = new Intent(SignIn.this,Home_layout.class);
        SignIn.this.startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();


    }

}