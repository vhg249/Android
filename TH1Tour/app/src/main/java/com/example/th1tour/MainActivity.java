package com.example.th1tour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.th1tour.model.SpinnerAdapter;
import com.example.th1tour.model.TourAdapter;

public class MainActivity extends AppCompatActivity {
    private Spinner sp;
//    private RecyclerView recyclerView;
//    private EditText eName, eTime;
//    private TourAdapter adapter;
//    private Button btAdd, btUpdate;

    private int[] imgs = {R.drawable.xemay, R.drawable.oto, R.drawable.maybay};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        adapter = new TourAdapter(this);
//        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        recyclerView.setLayoutManager(manager);
//        recyclerView.setAdapter(adapter);
    }

    private void initView() {
        sp = findViewById(R.id.img);
        SpinnerAdapter adapter = new SpinnerAdapter(this);
//        sp.setAdapter(adapter);

//        recyclerView = findViewById(R.id.recycleView);
//        eName = findViewById(R.id.name);
//        eTime = findViewById(R.id.time);
//        btAdd = findViewById(R.id.btAdd);
//        btUpdate = findViewById(R.id.btUpdate);
//        btUpdate.setEnabled(false);
    }


}