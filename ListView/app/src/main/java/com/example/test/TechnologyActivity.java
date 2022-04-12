package com.example.test;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.test.model.Technology;
import com.example.test.model.TechnologyAdapter;

public class TechnologyActivity extends AppCompatActivity {
    private ListView listView;
    TechnologyAdapter adapter;
    private Technology[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);
        listView = findViewById(R.id.lview);
        initData();
        adapter = new TechnologyAdapter(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for (int i=0; i<listView.getAdapter().getCount(); i++){
                    listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
                listView.getChildAt(position).setBackgroundColor(Color.YELLOW);
                Technology t = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), t.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        Integer[] imgs = {R.drawable.android, R.drawable.apple, R.drawable.blackberry, R.drawable.window};
        String[] names = {"Android", "Ios", "Blackberry", "Window Mobile"};
        String[] subs = {"Sub Android", "Sub Ios", "Sub Blackberry", "Sub Window Mobile"};
        String[] descs = {"MT Android", "MT Ios", "MT Blackberry", "MT Window Mobile"};

        list = new Technology[imgs.length];
        for(int i=0; i<list.length; i++){
            list[i] = new Technology(imgs[i], names[i], subs[i], descs[i]);
        }
    }
}