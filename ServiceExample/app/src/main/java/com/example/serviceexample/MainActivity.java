package com.example.serviceexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.serviceexample.adapter.ServiceAdapter;


public class MainActivity extends AppCompatActivity {
    private TextView ePrice;
    private Spinner serviceSpinner, measureSpinner;
    private Button btAdd, btUpdate;

    private RecyclerView recyclerView;

    private ServiceAdapter serviceAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
    }
}