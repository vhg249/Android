package com.example.test.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.R;

public class TechnologyAdapter extends ArrayAdapter<Technology> {
    private Context context;
    private Technology[] mList;


    public TechnologyAdapter(@NonNull Context context, Technology[] mList) {
        super(context, R.layout.item_tech, mList);
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_tech, null, true);
        ImageView img = view.findViewById(R.id.img);
        TextView tname = view.findViewById(R.id.tname);
        TextView tsub = view.findViewById(R.id.tsub);
        TextView tdesc = view.findViewById(R.id.tdescribe);

        Technology t = mList[position];
        img.setImageResource(t.getImg());
        tname.setText(t.getName());
        tsub.setText(t.getSub());
        tdesc.setText(t.getDescribe());

        return view;
    }

    public Technology getItem(int position) {
        return mList[position];
    }
}
