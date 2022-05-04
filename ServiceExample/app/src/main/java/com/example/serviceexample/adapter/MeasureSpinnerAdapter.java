package com.example.serviceexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.serviceexample.R;

public class MeasureSpinnerAdapter extends BaseAdapter {
    private Context context;
    private String[] measures;

    public MeasureSpinnerAdapter(Context context, String[] measures) {
        this.context = context;
        this.measures = measures;
    }

    @Override
    public int getCount() {
        return measures.length;
    }

    @Override
    public Object getItem(int position) {
        return measures[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View item = LayoutInflater.from(context).inflate(R.layout.measure_item, parent, false);
        TextView tv = item.findViewById(R.id.measureSpinner);
        tv.setText(measures[position]);
        return item;
    }

    public int getIndexMeasure(String measure) {
        int p = 0;
        for (int i = 0; i < measures.length; i++) {
            if(measures[i] == measure) {
                p = i;
                break;
            }
        }
        return p;
    }
}
