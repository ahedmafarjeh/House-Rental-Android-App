package com.example.renthouses;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

//import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.cursoradapter.widget.CursorAdapter;

public class RequestCursorAdapter extends CursorAdapter {
    public RequestCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.single_request_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView home_id = (TextView) view.findViewById(R.id.home_id_item);
        TextView tenant_email = (TextView) view.findViewById(R.id.tenant_email_item);
        Button view_profile = (Button) view.findViewById(R.id.veiw_profile_item);
        home_id.setText(cursor.getString(2));
        tenant_email.setText(cursor.getString(1));
        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ViewProfileActivity.class);
                intent.putExtra("Temail",tenant_email.getText().toString());
                intent.putExtra("house_id",home_id.getText().toString());
                startActivity(context,intent,null);
            }
        });
    }
}