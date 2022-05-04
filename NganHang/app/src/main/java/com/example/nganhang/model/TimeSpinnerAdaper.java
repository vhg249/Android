package com.example.nganhang.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nganhang.R;

public class TimeSpinnerAdaper extends BaseAdapter {
    private Context context;
    private String[] times;

    public TimeSpinnerAdaper(Context context, String[] times) {
        this.context = context;
        this.times = times;
    }

    @Override
    public int getCount() {
        return times.length;
    }

    @Override
    public Object getItem(int position) {
        return times[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View item = LayoutInflater.from(context).inflate(R.layout.time_item, parent, false);
        TextView tv = item.findViewById(R.id.spTime);
        tv.setText(times[position]);
        return item;
    }

    public int getIndexTime(String time) {
        int p = 0;
        for (int i = 0; i < times.length; i++) {
            if(times[i] == time) {
                p = i;
                break;
            }
        }
        return p;
    }
}
