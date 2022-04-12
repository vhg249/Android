package com.example.serviceexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.serviceexample.R;

public class MeasureSpinnerAdapter extends BaseAdapter {
    private String[] measureList;
    private Context context;

    @Override
    public int getCount() {
        return measureList.length;
    }

    @Override
    public Object getItem(int position) {
        return measureList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View item = LayoutInflater.from(context).inflate(R.layout.measure_item, parent, false);
        TextView tv = item.findViewById(R.id.measureSpinner);
        tv.setText(measureList[position]);
        return item;
    }
}
