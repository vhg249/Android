package com.example.bth1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bth1.adapter.ListAdapter;
import com.example.bth1.adapter.SpinnerAdapter;
import com.example.bth1.dataSrc.DataSrc;
import com.example.bth1.model.Tour;
import com.example.bth1.utils.Carbon;
import com.example.bth1.utils.Command;
import com.example.bth1.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements ListAdapter.Listener {
    DataSrc<Tour> dataSrc  = new DataSrc<>();
    List<Tour> list = new ArrayList<>();
    private int[] imgs = {
            R.drawable.img,
            R.drawable.img_1,
            R.drawable.img_2,
    };

    private Tour currentItem = null;
    private String currentKeySearch = "";

    ListAdapter adapter;
    EditText edLichTrinh, edTgian;

    Spinner spinner;
    SpinnerAdapter spinnerAdapter;

    Button btnAdd, btnDelete, btnEdit;
    SearchView searchView;
    RecyclerView reView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        fetchData();
        initListener();
        resetForm();
    }

    private void fetchData() {
        list.clear();
        if(this.currentKeySearch.trim().equals("")) {
            list.addAll(dataSrc.getList());
        } else {
            list.addAll(dataSrc.search(currentKeySearch));
        }
        for (Tour tour :list) {
            System.out.println("id "+ tour.getId());
            System.out.println(tour.toString());
        }
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        edLichTrinh = findViewById(R.id.edLichTrinh);
        edTgian = findViewById(R.id.edTgian);

        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);
        toggleBtn(true, false, false);

        searchView = findViewById(R.id.searchView);

        spinner = findViewById(R.id.spinner);
        spinnerAdapter = new SpinnerAdapter(imgs, this);
        spinner.setAdapter(spinnerAdapter);

        reView = findViewById(R.id.recyclerView);
        adapter = new ListAdapter(list, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        reView.setLayoutManager(manager);
        reView.setAdapter(adapter);
    }

    private void initListener() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tour item = getFromForm();
                if(item != null) {
                    list.add(item);
                    dataSrc.add(item);
                    resetForm();
                    fetchData();
                }
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tour item = getFromForm();
                if(item != null && currentItem != null) {
                    dataSrc.update(currentItem.getId(), item);
                    currentItem = null;
                    resetForm();
                    fetchData();
                    toggleBtn(true, false, false);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentItem != null) {
                    Utils.openAlertDialog(
                            MainActivity.this,
                            new Command() {
                                @Override
                                public void execute() {
                                    dataSrc.delete(currentItem.getId());
                                    resetForm();
                                    fetchData();
                                    toggleBtn(true, false, false);
                                }
                            },
                            true,
                            "Thông báo",
                            "Bạn có chắc chắn muốn xóa "+currentItem.getLichTrinh()
                    );
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
                currentKeySearch = newText;
                fetchData();
                return true;
            }
        });
    }

    private Tour getFromForm() {
        String lichTrinh = edLichTrinh.getText().toString().trim();
        String tGian = edTgian.getText().toString().trim();
        int imgSelect = (int)spinner.getSelectedItem();


        if(lichTrinh.equals("") || tGian.equals("")) {
            Toast.makeText(this, "Data nhap sai", Toast.LENGTH_SHORT).show();
            return null;
        }

        try {
          Tour item = new Tour(lichTrinh, tGian, imgSelect);
          return item;
        } catch (Exception e) {
            Toast.makeText(this, "Data nhap sai", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    public void onItemClick(Tour item) {
        this.currentItem = item;
        setItemToForm(item);
        toggleBtn(false, true, true);
    }

    private void setItemToForm(Tour item) {
        edLichTrinh.setText(item.getLichTrinh());
        edTgian.setText(item.getThoiGian());

        spinner.setSelection(spinnerAdapter.getIndexImage(item.getImg()));
    }

    private void resetForm() {
        edLichTrinh.setText("");
        edTgian.setText("");
        spinner.setSelection(0);
    }

    private void toggleBtn(boolean add, boolean edit, boolean delete) {
        btnAdd.setEnabled(add);
        btnAdd.setVisibility(add ? View.VISIBLE : View.INVISIBLE);
        btnEdit.setEnabled(edit);
        btnEdit.setVisibility(edit ? View.VISIBLE : View.INVISIBLE);
        btnDelete.setEnabled(delete);
        btnDelete.setVisibility(delete ? View.VISIBLE : View.INVISIBLE);
    }
}