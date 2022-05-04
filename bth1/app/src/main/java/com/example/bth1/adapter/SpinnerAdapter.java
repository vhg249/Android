package com.example.bth1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.bth1.R;

public class SpinnerAdapter extends BaseAdapter {
    private int[] imgs;
    private Context context;

    public SpinnerAdapter(int[] imgs, Context context) {
        this.imgs = imgs;
        this.context = context;
    }

    @Override
    public int getCount(){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        ImageView img = item.findViewById(R.id.img);
        img.setImageResource(imgs[position]);

        return item;
    }

    public int getIndexImage(int img) {
        int p =0;
        for (int i=0;i<imgs.length;i++) {
            if(imgs[i] == img) {
                p = i;
                break;
            }
        }
        return p;
    }
}
