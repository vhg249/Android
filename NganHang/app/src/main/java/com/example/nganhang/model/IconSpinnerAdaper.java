package com.example.nganhang.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.nganhang.R;

public class IconSpinnerAdaper extends BaseAdapter {
    private Context context;
    private int[] imgs;

    public IconSpinnerAdaper(Context context, int[] imgs) {
        this.context = context;
        this.imgs = imgs;
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
        View item = LayoutInflater.from(context).inflate(R.layout.icon_item, parent, false);
        ImageView img = item.findViewById(R.id.iconImg);
        img.setImageResource(imgs[position]);
        return item;
    }

    public int getIndexImage(int img) {
        int p = 0;
        for (int i = 0; i < imgs.length; i++) {
            if(imgs[i] == img) {
                p = i;
                break;
            }
        }
        return p;
    }
}
