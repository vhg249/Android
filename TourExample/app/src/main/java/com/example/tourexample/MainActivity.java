package com.example.tourexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tourexample.model.SpinnerAdapter;
import com.example.tourexample.model.Tour;
import com.example.tourexample.model.TourAdapter;

public class MainActivity extends AppCompatActivity implements TourAdapter.TourItemListener{
    private Spinner sp;
    private EditText eName, eTime;
    private Button btAdd, btUpdate;
    private RecyclerView recyclerView;
    private TourAdapter adapter;

    private int currentPosition;

    private int[] imgs = {R.drawable.xemay, R.drawable.oto, R.drawable.maybay};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        adapter = new TourAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(this);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tour tour = new Tour();
                String i = sp.getSelectedItem().toString();
                String name = eName.getText().toString();
                String time = eTime.getText().toString();

                int img = R.drawable.xemay;
                try {
                    img = imgs[Integer.parseInt(i)];

                    tour.setImg(img);
                    tour.setName(name);
                    tour.setTime(time);

                    adapter.add(tour);
                } catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tour tour = new Tour();
                String i = sp.getSelectedItem().toString();
                String name = eName.getText().toString();
                String time = eTime.getText().toString();

                int img = R.drawable.xemay;
                try {
                    img = imgs[Integer.parseInt(i)];

                    tour.setImg(img);
                    tour.setName(name);
                    tour.setTime(time);

                    adapter.update(currentPosition, tour);
                    btAdd.setEnabled(true);
                    btUpdate.setEnabled(false);
                } catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        sp = findViewById(R.id.spinnerImg);
        SpinnerAdapter adapter = new SpinnerAdapter(this);
        sp.setAdapter(adapter);

        eName = findViewById(R.id.eName);
        eTime = findViewById(R.id.eTime);
        btAdd = findViewById(R.id.btAdd);
        btUpdate = findViewById(R.id.btUpdate);
        btUpdate.setEnabled(false);

        recyclerView = findViewById(R.id.recycleView);
    }

    @Override
    public void onItemClick(View view, int position) {
        btUpdate.setEnabled(true);
        btAdd.setEnabled(false);

        currentPosition = position;
        Tour tour = adapter.getItem(position);

        int img = tour.getImg();
        int p = 0;
        for (int i=0; i<imgs.length; i++){
            if (img == imgs[i]){
                p = i;
                break;
            }
        }
        sp.setSelection(p);

        eName.setText(tour.getName());
        eTime.setText(tour.getTime());
    }
}