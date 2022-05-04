package com.example.nganhang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nganhang.model.Bank;
import com.example.nganhang.model.BankAdapter;
import com.example.nganhang.model.DataSrc;
import com.example.nganhang.model.IconSpinnerAdaper;
import com.example.nganhang.model.TimeSpinnerAdaper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BankAdapter.BankListener {
    private EditText eName, eInterest;
    private Spinner spIcon, spTime;
    private RadioButton rbtOnline, rbtOffline;
    private Button btAdd, btUpdate;
    private SearchView searchView;
    private RecyclerView recyclerView;

    private IconSpinnerAdaper iconAdapter;
    private TimeSpinnerAdaper timeAdapter;
    private BankAdapter adapter;

    private List<Bank> list = new ArrayList<>();
    private DataSrc dataSrc = new DataSrc();

    private int currentPosition;
    private String searchKey = "";

    private int[] imgs = {R.drawable.techcombank, R.drawable.tpbank, R.drawable.vietinbank};
    private String[] times = {"1 tháng", "3 tháng", "6 tháng", "9 tháng", "12 tháng"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        fetchData();
        initListener();
    }

    private void initView() {
        eName = findViewById(R.id.eName);
        eInterest = findViewById(R.id.eInterest);
        rbtOffline = findViewById(R.id.rbtOffline);
        rbtOnline = findViewById(R.id.rbtOnline);
        btAdd = findViewById(R.id.btAdd);
        btUpdate = findViewById(R.id.btUpdate);
        searchView = findViewById(R.id.eSearch);
        recyclerView = findViewById(R.id.recycleView);

        spIcon = findViewById(R.id.iconSpinner);
        iconAdapter = new IconSpinnerAdaper(this, imgs);
        spIcon.setAdapter(iconAdapter);

        spTime = findViewById(R.id.timeSpinner);
        timeAdapter = new TimeSpinnerAdaper(this, times);
        spTime.setAdapter(timeAdapter);

        adapter = new BankAdapter(list, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        btUpdate.setEnabled(false);
        rbtOnline.setChecked(true);
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

    private void initListener(){
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bank bank = getFormForm();
                if(bank != null){
                    list.add(bank);
                    dataSrc.add(bank);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bank bank = getFormForm();
                if(bank != null){
                    list.set(currentPosition, bank);
                    dataSrc.update(bank, currentPosition);
                    adapter.notifyDataSetChanged();
                    btAdd.setEnabled(true);
                    btUpdate.setEnabled(false);
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
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

    private Bank getFormForm(){
        int icon = (int) spIcon.getSelectedItem();
        String name = eName.getText().toString();
        float interest = 0;
        String time = (String) spTime.getSelectedItem();
        boolean isOnline;
        if(rbtOnline.isChecked()){
            isOnline = true;
        } else {
            isOnline = false;
        }
        if(name.equals("") || eInterest.getText().toString().equals("")){
            Toast.makeText(this, "Nhap lai", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            interest = Float.parseFloat(eInterest.getText().toString());
        }
        try {
            Bank b = new Bank(name,time, icon, interest, isOnline);
            return b;
        } catch (NumberFormatException e){
            Toast.makeText(this, "Nhap lai", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private void setItemToForm(Bank bank){
        spIcon.setSelection(iconAdapter.getIndexImage(bank.getIcon()));
        spTime.setSelection(timeAdapter.getIndexTime(bank.getTime()));
        eName.setText(bank.getName());
        eInterest.setText(bank.getInterest()+"");
        boolean online = bank.isOnline();
        System.out.println("hello"+online);
        if(online) rbtOnline.setChecked(true);
        else rbtOffline.setChecked(true);
    }

    @Override
    public void onItemClick(Bank bank) {
        int pos = list.indexOf(bank);
        this.currentPosition = pos;
        setItemToForm(bank);
        btAdd.setEnabled(false);
        btUpdate.setEnabled(true);
    }
}