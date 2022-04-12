package com.example.th1work;

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
import android.widget.Toast;

import com.example.th1work.model.Work;
import com.example.th1work.model.WorkAdapter;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eName, eDetail, eDate;
    private Button btAdd, btUpdate;
    private RadioButton btMale, btFemale;
    private RecyclerView recyclerView;
    private WorkAdapter adapter;

    private int[] imgs = {R.drawable.female, R.drawable.male};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        eDate.setOnClickListener(this);

        adapter = new WorkAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Work work = new Work();
                String name = eName.getText().toString();
                String detail = eDetail.getText().toString();
                String date = eDate.getText().toString();
                int genderImg;

                try {
                    if(btFemale.isChecked()){
                        genderImg = R.drawable.female;
                    } else {
                        genderImg = R.drawable.male;
                    }
                    work.setGenderImg(genderImg);
                    work.setName(name);
                    work.setDetail(detail);
                    work.setDate(date);

                    adapter.add(work);
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        eName = findViewById(R.id.eName);
        eDetail = findViewById(R.id.eDetail);
        eDate = findViewById(R.id.eDate);
        btFemale = findViewById(R.id.btFemale);
        btMale = findViewById(R.id.btMale);
        btAdd = findViewById(R.id.btAdd);
        btUpdate = findViewById(R.id.btUpdate);
        recyclerView = findViewById(R.id.recycleView);

        btMale.setChecked(true);
        btUpdate.setEnabled(false);
    }

    @Override
    public void onClick(View view) {
        if(view == eDate){
            Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                    eDate.setText(dd+"/"+(mm+1)+"/"+yy);
                }
            }, y, m, d);
            dateDialog.show();
        }
    }
}