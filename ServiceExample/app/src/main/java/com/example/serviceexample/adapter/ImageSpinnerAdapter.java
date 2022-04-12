package com.example.serviceexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.serviceexample.R;

public class ImageSpinnerAdapter extends BaseAdapter {
    private int[] imgs;
    private Context context;

    public ImageSpinnerAdapter() {
    }

    public ImageSpinnerAdapter(int[] imgs, Context context) {
        this.imgs = imgs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return imgs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View item = LayoutInflater.from(context).inflate(R.layout.service_item, parent, false);
        ImageView img = item.findViewById(R.id.imgSpinner);
        img.setImageResource(imgs[position]);
        return item;
    }

    public int getIndexImage(int img){
        int pos = 0;
        for (int i=0; i<imgs.length; i++){
            if(imgs[i] == img){
                pos = i;
                break;
            }
        }
        return pos;
    }
}
