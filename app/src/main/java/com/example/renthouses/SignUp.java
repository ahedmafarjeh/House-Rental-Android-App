package com.example.renthouses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //create object from fragments
        final int[] count=new int[1];
        Tenant tenant = new Tenant();
        RentingAgency rentingAgency = new RentingAgency();
        //create fragment manager
        final FragmentManager fragmentManager = getSupportFragmentManager();
        //creat sign up buttons
        Button tennt_signUP = (Button) findViewById(R.id.tent);
        Button rentAgency_signUp = (Button) findViewById(R.id.rentAgency);
        tennt_signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count[0]++;
                if (count[0]==1) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.root, tenant, "FristFrag");
                    fragmentTransaction.commit();
                }
                else {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.root, tenant);
                    fragmentTransaction.commit();

                }

            }
        });

        rentAgency_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count[0]++;
                if (count[0]==1) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.root, rentingAgency, "SecFrag");
                    fragmentTransaction.commit();
                }
                else {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.root, rentingAgency);
                    fragmentTransaction.commit();

                }

            }
        });


    }
}