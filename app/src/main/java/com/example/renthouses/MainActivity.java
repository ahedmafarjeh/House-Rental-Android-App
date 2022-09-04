package com.example.renthouses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setProgress(false);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionAsyncTask connectionAsyncTask = new
                        ConnectionAsyncTask(MainActivity.this);

                connectionAsyncTask.execute("https://run.mocky.io/v3/5d41f897-62ca-4663-9d07-353b40a42521");
            }
        });
        linearLayout = (LinearLayout) findViewById(R.id.layout);


    }
    public void setButtonText(String text) {
        button.setText(text);
        Toast.makeText(MainActivity.this, "Connection Success", Toast.LENGTH_SHORT).show();
    }
    public void fillHouses(List<House> houses) {
        LinearLayout linearLayout = (LinearLayout)
                findViewById(R.id.layout);
        linearLayout.removeAllViews();
        for (int i = 0; i < houses.size(); i++) {
            TextView textView = new TextView(this);
            textView.setText(houses.get(i).toString());
            linearLayout.addView(textView);
        }
        /*DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this, "House", null, 2);
        for (int i = 0; i < houses.size(); i++) {
            Cursor values = dataBaseHelper.search(houses.get(i).getOwner_email(),"House_Info");
            if (values == null){
                //URL url = new URL(houses.get(i).getImg());
                //Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                dataBaseHelper.storeHouse(houses.get(i).getOwner_email(),houses.get(i).getCity(),houses.get(i).getPostal_address()
                        ,houses.get(i).getSurface_area(),img,houses.get(i).getConstruction_year(),
                        houses.get(i).getBedroom_number(),houses.get(i).getRental_price(),houses.get(i).getStatus(),
                        houses.get(i).getAvailability_date(),houses.get(i).getDescription(),
                        houses.get(i).isGarden(),houses.get(i).isBalcony());
                Toast.makeText(MainActivity.this,"data saved on database",Toast.LENGTH_SHORT).show();

            }*/

        Intent intent = new Intent(MainActivity.this, SignIn.class);
        MainActivity.this.startActivity(intent);
        finish();
    }
    public void setProgress(boolean progress) {
        ProgressBar progressBar = (ProgressBar)
                findViewById(R.id.progressBar);
        if (progress) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}

