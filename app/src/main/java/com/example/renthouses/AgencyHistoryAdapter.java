package com.example.renthouses;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class AgencyHistoryAdapter extends CursorAdapter {
    public AgencyHistoryAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.single_tenant_history, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView chose_t_ag = (TextView) view.findViewById(R.id.chose_name);
        TextView city = (TextView) view.findViewById(R.id.city_t_history);
        TextView postalAddress = (TextView) view.findViewById(R.id.postal_address_t_history);
        TextView tenant_Name = (TextView) view.findViewById(R.id.owner_name_history);
        chose_t_ag.setText("Tenant Name");
        city.setText(cursor.getString(1));
        postalAddress.setText(cursor.getString(2));
        tenant_Name.setText(cursor.getString(3)+" "+cursor.getString(4));
    }
}
