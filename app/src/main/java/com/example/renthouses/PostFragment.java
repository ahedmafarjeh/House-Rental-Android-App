package com.example.renthouses;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class PostFragment extends Fragment {
    ImageView imageView;
    private Bitmap store_Img;
    boolean is_garden,is_balcony;
    RadioButton garden,balcony,nogarden,nobalcony;
    SharedPrefManager sharedPrefManager;
    public PostFragment() {
        // Required empty public constructor
    }
    protected static int RESULT_LOAD_IMAGE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EditText city = (EditText)getActivity().findViewById(R.id.Pcity);
        EditText postal_addr = (EditText)getActivity().findViewById(R.id.postal_add);
        EditText area = (EditText)getActivity().findViewById(R.id.Psurf_area);
        EditText construct_year = (EditText)getActivity().findViewById(R.id.construct_year);
        EditText bedroom_num = (EditText)getActivity().findViewById(R.id.Pnum_bedroom);
        EditText price = (EditText)getActivity().findViewById(R.id.Prental_price);
        //EditText status = (EditText)getActivity().findViewById(R.id.Pstatus);
        EditText avaulable_dat = (EditText)getActivity().findViewById(R.id.availabil_date);
        EditText description = (EditText)getActivity().findViewById(R.id.description);
        Button buttonLoadImage = (Button) getActivity().findViewById(R.id.loadImage);
        Button post = (Button) getActivity().findViewById(R.id.post);
        sharedPrefManager =SharedPrefManager.getInstance(getActivity());
        RadioGroup gard = (RadioGroup) getActivity().findViewById(R.id.isGarednPost);
        RadioGroup balc = (RadioGroup) getActivity().findViewById(R.id.isbalconyPost);
        garden=(RadioButton) getActivity().findViewById(R.id.Garden);
        balcony=(RadioButton) getActivity().findViewById(R.id.Balcony);
        nogarden=(RadioButton) getActivity().findViewById(R.id.noGarden);
        nobalcony=(RadioButton) getActivity().findViewById(R.id.noBalcony);
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
                is_balcony = false;
            }
        });
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        final String status[] = new String[1];
        String[] options = { "Select Status","furnished", "unfurnished" };
        final Spinner statusSpinner =(Spinner) getActivity().findViewById(R.id.statusSpinerPost);
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
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( city.getText().toString().isEmpty() || postal_addr.getText().toString().isEmpty()
                || area.getText().toString().isEmpty() || construct_year.getText().toString().isEmpty() || bedroom_num.getText().toString().isEmpty()
                || price.getText().toString().isEmpty() || status[0]=="Select Status" || avaulable_dat.getText().toString().isEmpty()
                || description.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"There is empty field or don't select status",Toast.LENGTH_SHORT).show();
                }
                else {
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
                    dataBaseHelper.storeHouse(sharedPrefManager.readString("email", "noValue"),city.getText().toString(),postal_addr.getText().toString(),
                            Integer.parseInt(area.getText().toString()),store_Img,Integer.parseInt(construct_year.getText().toString()),Integer.parseInt(bedroom_num.getText().toString()),
                            Integer.parseInt(price.getText().toString()),status[0],avaulable_dat.getText().toString(),description.getText().toString(),is_garden,is_balcony);
                    Toast.makeText(getActivity(), "House info saved", Toast.LENGTH_SHORT).show();
                    city.getText().clear();postal_addr.getText().clear();area.getText().clear();
                    construct_year.getText().clear();bedroom_num.getText().clear();price.getText().clear();
                    avaulable_dat.getText().clear();description.getText().clear();
                    gard.clearCheck(); balc.clearCheck(); statusSpinner.setSelection(0,true);
                    imageView.setImageResource(0);
                }


            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK &&  data != null ) {
            Uri selectedImage = data.getData();
             imageView = (ImageView) getActivity().findViewById(R.id.imgView);
            try {
                store_Img=MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),selectedImage);
                imageView.setImageBitmap(store_Img);
            } catch (IOException e) {
                Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }


           /* String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) getActivity().findViewById(R.id.imgView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));*/
        }
    }
    public void storeImage(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        try {
            if (imageView.getDrawable() != null && store_Img != null) {
               // dataBaseHelper.storeImage("home", store_Img);
                Toast.makeText(getActivity(),"Image saved",Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(getActivity(),"Please select image",Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

}