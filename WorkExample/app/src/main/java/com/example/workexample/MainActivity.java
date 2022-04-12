package com.example.workexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workexample.dataSrc.DataSrc;
import com.example.workexample.model.Work;
import com.example.workexample.model.WorkAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, WorkAdapter.WorkListener {
    private EditText eName, eContent;
    private TextView eDate;
    private RadioButton rMale, rFemale;
    private Button btAdd, btUpdate;
    private RecyclerView recyclerView;
    private DataSrc dataSrc = new DataSrc();
    private List<Work> wList = new ArrayList<>();
    private WorkAdapter adapter;
    private int imgs[] = {R.drawable.female, R.drawable.male};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        adapter = new WorkAdapter(wList);
        fetchData();
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Work w = getFromForm();
                if(w != null){
                    wList.add(w);
                    dataSrc.add(w);
                    fetchData();
                }
            }
        });
    }

    public void initView(){
        eName = findViewById(R.id.eName);
        eContent = findViewById(R.id.eContent);
        eDate = findViewById(R.id.eDate);
        rMale = findViewById(R.id.rbtMale);
        rFemale = findViewById(R.id.rbtFemale);
        btAdd = findViewById(R.id.btAdd);
        btUpdate = findViewById(R.id.btUpdate);
        recyclerView = findViewById(R.id.recycleView);

        eDate.setOnClickListener(this);
    }

    public Work getFromForm(){
        String name = eName.getText().toString();
        String content = eContent.getText().toString();
        String date = eDate.getText().toString();
        int img;
        if(rFemale.isChecked()){
            img = imgs[0];
        } else {
            img = imgs[1];
        }
        if(name.equals("") || content.equals("") || date.equals("")){
            Toast.makeText(this, "Nhap lai", Toast.LENGTH_SHORT).show();
            return null;
        }
        try{
            Work item = new Work(name, content, date, img);
            return item;
        } catch (Exception e){
            Toast.makeText(this, "Nhap lai", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public void fetchData(){
        wList.clear();
        wList.addAll(dataSrc.mList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if(view == eDate){
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    eDate.setText(d+"/"+m+"/"+y);
                }
            }, year, month, day);
            datePickerDialog.show();
        }
    }

    @Override
    public void onItemClick(Work item) {

    }
}