package com.example.serviceexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviceexample.adapter.ImgSpinnerAdapter;
import com.example.serviceexample.adapter.MeasureSpinnerAdapter;
import com.example.serviceexample.adapter.ServiceAdapter;
import com.example.serviceexample.dataSrc.DataSrc;
import com.example.serviceexample.model.Service;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ServiceAdapter.ServiceListener {
    private EditText ePrice, eAmount;
    private Spinner serviceSpinner, measureSpinner;
    private Button btAdd, btUpdate;

    private RecyclerView recyclerView;
    private SearchView searchView;

    private ServiceAdapter adapter;
    private ImgSpinnerAdapter imgSpinnerAdapter;
    private MeasureSpinnerAdapter measureSpinnerAdapter;

    private int[] imgs = {R.drawable.spa, R.drawable.gym, R.drawable.laundry, R.drawable.transport};
    private String[] measures = {"gio", "kg", "chuyen"};

    private List<Service> list = new ArrayList<>();
    private DataSrc dataSrc = new DataSrc();

    private int currentPosition;
    private String currentSearchKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fetchData();
        initListener();
    }

    private void initView() {
        ePrice = findViewById(R.id.ePrice);
        eAmount = findViewById(R.id.eAmount);
        btAdd = findViewById(R.id.btAdd);
        btUpdate = findViewById(R.id.btUpdate);
        searchView = findViewById(R.id.searchView);

        serviceSpinner = findViewById(R.id.serviceSpinner);
        imgSpinnerAdapter = new ImgSpinnerAdapter(this, imgs);
        serviceSpinner.setAdapter(imgSpinnerAdapter);

        measureSpinner = findViewById(R.id.measureSpinner);
        measureSpinnerAdapter = new MeasureSpinnerAdapter(this, measures);
        measureSpinner.setAdapter(measureSpinnerAdapter);

        recyclerView = findViewById(R.id.recycleView);
        adapter = new ServiceAdapter(list, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        btUpdate.setEnabled(false);
    }

    public void fetchData(){
        list.clear();
        list.addAll(dataSrc.getList());
        adapter.notifyDataSetChanged();
    }

    public void initListener(){
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Service service = getFromForm();
                System.out.println("hello "+service.getImgService());
                if(service != null){
                    list.add(service);
                    dataSrc.add(service);
                    fetchData();
                }
            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Service service = getFromForm();
                if(service != null){
                    list.set(currentPosition, service);
                    dataSrc.update(service, currentPosition);
                    fetchData();
                    btUpdate.setEnabled(false);
                    btAdd.setEnabled(true);
                }
            }
        });
    }

    public Service getFromForm(){
        int img = (int) serviceSpinner.getSelectedItem();
        String measure = (String) measureSpinner.getSelectedItem();
        String amount = eAmount.getText().toString();
        String price = ePrice.getText().toString();

        try{
            Service service = new Service(img, Integer.parseInt(amount), Integer.parseInt(price), measure);
            return service;
        } catch (Exception e){
            Toast.makeText(this, "Nhap lai", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public void setItemToForm(Service service){
        serviceSpinner.setSelection(imgSpinnerAdapter.getIndexImage(service.getImgService()));
        measureSpinner.setSelection((measureSpinnerAdapter.getIndexMeasure(service.getMeasure())));
        eAmount.setText(service.getAmount()+"");
        ePrice.setText(service.getPrice()+"");
    }

    @Override
    public void onItemClick(Service item) {
        int pos = list.indexOf(item);
        this.currentPosition = pos;
        setItemToForm(item);
        btUpdate.setEnabled(true);
        btAdd.setEnabled(false);
    }
}