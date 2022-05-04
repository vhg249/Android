package com.example.ticket;

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
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ticket.dataSrc.DataSrc;
import com.example.ticket.model.Ticket;
import com.example.ticket.model.TicketAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TicketAdapter.Listener {
    private EditText eCode;
    private TextView eDate;
    private RadioButton rbtVip, rbtNormal, rbtCheap, rbtIsPaid, rbtNotPaid;
    private Button btAdd, btUpdate;
    private RecyclerView recyclerView;
    private SearchView searchView;

    private TicketAdapter adapter;
    private List<Ticket> list = new ArrayList<>();
    private DataSrc dataSrc = new DataSrc();

    private int currentPosition;
    private String searchKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        fetchData();
        initListener();
    }

    private void initView() {
        eCode = findViewById(R.id.eCode);
        eDate = findViewById(R.id.eDate);
        rbtCheap = findViewById(R.id.rbtCheap);
        rbtNormal = findViewById(R.id.rbtNormal);
        rbtVip = findViewById(R.id.rbtVip);
        rbtIsPaid = findViewById(R.id.rbtIsPaid);
        rbtNotPaid = findViewById(R.id.rbtNotPaid);
        btAdd = findViewById(R.id.btAdd);
        btUpdate = findViewById(R.id.btUpdate);
        recyclerView = findViewById(R.id.recycleView);
        searchView = findViewById(R.id.eSearch);

        btUpdate.setEnabled(false);
        rbtVip.setChecked(true);
        rbtIsPaid.setChecked(true);

        eDate.setOnClickListener(this);

        adapter = new TicketAdapter(list, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ticket ticket = getFromForm();
                if(ticket != null){
                    list.add(ticket);
                    dataSrc.add(ticket);
                    fetchData();
                }
            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ticket ticket = getFromForm();
                if(ticket != null){
                    list.set(currentPosition, ticket);
                    dataSrc.update(currentPosition, ticket);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchKey = newText;
                fetchData();
                return true;
            }
        });
    }

    public void fetchData(){
        list.clear();
        if(searchKey.trim().equals("")){
            list.addAll(dataSrc.getList());
        } else {
            list.addAll(dataSrc.search(searchKey));
        }

        adapter.notifyDataSetChanged();
    }

    private Ticket getFromForm(){
        String code = eCode.getText().toString();
        String date = eDate.getText().toString();
        String type;
        if(rbtVip.isChecked()){
            type = "VIP";
        } else if(rbtNormal.isChecked()){
            type = "Thuong";
        } else {
            type = "Re";
        }
        boolean isPaid;
        if(rbtIsPaid.isChecked()) isPaid = true;
        else isPaid = false;

        if(code.equals("") || date.equals("Chon ngay")){
            Toast.makeText(this, "Nhap lai", Toast.LENGTH_SHORT).show();
            return null;
        }
        try {
            Ticket t = new Ticket(code, date, type, isPaid);
            return t;
        } catch (Exception e){
            Toast.makeText(this, "Nhap lai", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private void setItemToForm(Ticket ticket){
        eCode.setText(ticket.getCode());
        eDate.setText(ticket.getDate());
        String type = ticket.getType();
        switch (type){
            case "VIP":
                rbtVip.setChecked(true);
                break;
            case "Thuong":
                rbtNormal.setChecked(true);
                break;
            case "Re":
                rbtCheap.setChecked(true);
                break;
            default:
                break;
        }
        boolean isPaid = ticket.isPaid();
        if(isPaid) rbtIsPaid.setChecked(true);
        else rbtNotPaid.setChecked(true);
    }

    @Override
    public void onClick(View view) {
        if(view == eDate){
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    eDate.setText(d+"/"+m+"/"+y);
                }
            }, year, month, day);
            dateDialog.show();
        }
    }

    @Override
    public void onClickItem(Ticket ticket) {
        int pos = list.indexOf(ticket);
        this.currentPosition = pos;
        setItemToForm(ticket);
        btAdd.setEnabled(false);
        btUpdate.setEnabled(true);
    }
}