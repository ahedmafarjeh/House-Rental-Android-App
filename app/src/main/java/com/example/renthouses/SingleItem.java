package com.example.renthouses;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

class SingleItem extends BaseAdapter {
    private Context context;

    public SingleItem(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.single_search_item, parent, false);
        //View v = li.inflate(R.layout.single_search_item, null);
        TextView des = (TextView) convertView.findViewById(R.id.description_item);
        //Button view_btn = convertView.findViewById(R.id.apply_lhome);
        des.setText("hello every one");

        return convertView;
    }
}
