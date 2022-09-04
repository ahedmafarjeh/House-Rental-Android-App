package com.example.renthouses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ViewProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        SharedPrefManager sharedPrefManager;
        sharedPrefManager = SharedPrefManager.getInstance(this);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        TextView eml = (TextView) findViewById(R.id.eml_req);
        EditText fname = (EditText) findViewById(R.id.fname_req);
        EditText lname = ( EditText) findViewById(R.id.lname_req);
        EditText gnder = ( EditText) findViewById(R.id.gend_req);
        EditText nationality = ( EditText) findViewById(R.id.nation_req);
        EditText salary = ( EditText) findViewById(R.id.slry_req);
        EditText occupation = ( EditText) findViewById(R.id.ocup_req);
        EditText f_size = ( EditText) findViewById(R.id.familysize_req);
        EditText country = ( EditText) findViewById(R.id.cntry_req);
        EditText city = ( EditText) findViewById(R.id.cty_req);
        EditText phone = ( EditText) findViewById(R.id.ph_num_req);
        Button history = (Button) findViewById(R.id.tenant_history_bt);
        Button approve = (Button) findViewById(R.id.Approve);
        Button reject = (Button) findViewById(R.id.Reject);
        Intent intent = getIntent();
        String T_email = intent.getStringExtra("Temail");
        String id = intent.getStringExtra("house_id");
        sharedPrefManager.writeString("houseID",intent.getStringExtra("house_id"));
        Cursor values;
        values = dataBaseHelper.search(T_email,"TENANT_USER");
        if (values == null)
            Toast.makeText(this, "user not found", Toast.LENGTH_SHORT).show();
        else {
            eml.setText(values.getString(0));
            fname.setText(values.getString(1));
            lname.setText(values.getString(2));
            gnder.setText(values.getString(3));
            nationality.setText(values.getString(5));
            salary.setText(values.getString(6));
            occupation.setText(values.getString(7));
            f_size.setText(values.getString(8));
            country.setText(values.getString(9));
            city.setText(values.getString(10));
            phone.setText(values.getString(11));
        }
        Bundle bundle = new Bundle();
        bundle.putString("Temail", eml.getText().toString());
        final FragmentManager fragmentManager = getSupportFragmentManager();
        LinearLayout layout = (LinearLayout) findViewById(R.id.root2);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.root1);
        Button back_bt = (Button) findViewById(R.id.back);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layout.setVisibility(View.GONE);
                back_bt.setVisibility(View.VISIBLE);
                TenantHistoryFragment fragment = new TenantHistoryFragment();
                fragment.setArguments(bundle);
                frameLayout.setVisibility(View.VISIBLE);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.root1 ,fragment).commit();

            }
        });
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cursor values = dataBaseHelper.search(sharedPrefManager.readString("email","noValue"),"HOUSE_Info");
                Cursor values = dataBaseHelper.findCityPostal(Integer.parseInt(id));
                Boolean success = dataBaseHelper.insert_Tenant_Home(eml.getText().toString(),Integer.parseInt(id), values.getString(2),
                        values.getString(1),values.getString(3));
                if (success == false)
                    Toast.makeText(ViewProfileActivity.this, "failed", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(ViewProfileActivity.this, "success", Toast.LENGTH_SHORT).show();
                    dataBaseHelper.deleteNotification(Integer.parseInt(id));
                    Intent i = new Intent(ViewProfileActivity.this,Home_layout.class);
                    startActivity(i);

                }
            }
        });
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
                back_bt.setVisibility(View.INVISIBLE);
            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.deleteNotification(Integer.parseInt(id));
                Intent i = new Intent(ViewProfileActivity.this,Home_layout.class);
                startActivity(i);

            }
        });
    }
}