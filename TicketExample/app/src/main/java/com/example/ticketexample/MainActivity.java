package com.example.ticketexample;

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

import com.example.ticketexample.model.Ticket;
import com.example.ticketexample.model.TicketAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TicketAdapter.TicketItemListener {
    private EditText eCode, eDate;
    private RadioButton rbtVip, rbtNormal, rbtCheap, rbtYes, rbtNo;
    private Button btAdd, btUpdate;
    private RecyclerView recyclerView;
    private TicketAdapter adapter;
    private int currentPosition;
    private DataSource dataSource = new DataSource();
    List<Ticket> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        adapter = new TicketAdapter(this, list);
        fetchData();
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ticket ticket = new Ticket();
                String code = eCode.getText().toString();
                String date = eDate.getText().toString();
                String type = "";
                if(rbtVip.isChecked()) type = "VIP";
                else if(rbtNormal.isChecked()) type = "Thuong";
                else if(rbtCheap.isChecked()) type = "Re";
                boolean paid;
                if(rbtYes.isChecked()) paid = true;
                else paid = false;
                try{
                    if(code.trim().equals("") || date.trim().equals("")){
                        throw new Exception();
                    }
                    ticket.setCode(code);
                    ticket.setDate(date);
                    ticket.setType(type);
                    ticket.setPaid(paid);

                    dataSource.add(ticket);
                    fetchData();
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ticket ticket = list.get(currentPosition);
                String code = eCode.getText().toString();
                String date = eDate.getText().toString();
                String type = "";
                if(rbtVip.isChecked()) type = "VIP";
                else if(rbtNormal.isChecked()) type = "Thuong";
                else if(rbtCheap.isChecked()) type = "Re";
                boolean paid;
                if(rbtYes.isChecked()) paid = true;
                else paid = false;
                try{
                    if(code.trim().equals("") || date.trim().equals("")){
                        throw new Exception();
                    }
                    ticket.setCode(code);
                    ticket.setDate(date);
                    ticket.setType(type);
                    ticket.setPaid(paid);

                    dataSource.update(ticket, currentPosition);
                    currentPosition = -1;
                    fetchData();
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void fetchData(){
        list.clear();
        list.addAll(dataSource.list);
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        eCode = findViewById(R.id.eCode);
        eDate = findViewById(R.id.eDate);
        rbtCheap = findViewById(R.id.rbtCheap);
        rbtNormal = findViewById(R.id.rbtNormal);
        rbtVip = findViewById(R.id.rbtVip);
        rbtNo = findViewById(R.id.rbtNo);
        rbtYes = findViewById(R.id.rbtYes);
        btAdd = findViewById(R.id.btAdd);
        btUpdate = findViewById(R.id.btUpdate);
        recyclerView = findViewById(R.id.recycleView);

        rbtVip.setChecked(true);
        rbtYes.setChecked(true);
        btUpdate.setEnabled(false);

        eDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == eDate){
            Calendar c = Calendar.getInstance();
            int yy = c.get(Calendar.YEAR);
            int mm = c.get(Calendar.MONTH);
            int dd = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    eDate.setText(d+"/"+m+"/"+y);
                }
            }, yy, mm, dd);
            dateDialog.show();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        currentPosition = position;
        Ticket t = list.get(position);
        System.out.println("click"+position);
        eCode.setText(t.getCode().toString());
        eDate.setText(t.getDate().toString());
        String type = t.getType().toString();
        if(type == "VIP"){
            rbtVip.setChecked(true);
        } else if(type == "Thuong"){
            rbtNormal.setChecked(true);
        } else {
            rbtCheap.setChecked(true);
        }
        boolean isPaid = t.isPaid();
        if(isPaid){
            rbtYes.setChecked(true);
        } else {
            rbtNo.setChecked(false);
        }
    }
}