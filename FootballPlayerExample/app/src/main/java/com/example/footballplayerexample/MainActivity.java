package com.example.footballplayerexample;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballplayerexample.dataSrc.DataSrc;
import com.example.footballplayerexample.model.Player;
import com.example.footballplayerexample.model.PlayerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PlayerAdapter.Listener {
    private EditText eName;
    private TextView eDate;
    private RadioButton rbtMale, rbtFemale;
    private CheckBox cbHauVe, cbTienVe, cbTienDao;
    private Button btAdd, btUpdate;
    private RecyclerView recyclerView;
    private PlayerAdapter adapter;
    private List<Player> list = new ArrayList<>();
    private DataSrc dataSrc = new DataSrc();
    private int[] imgs = {R.drawable.male, R.drawable.female};
    private int currentPosition;
    private SearchView searchView;
    private String currentSearchKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fetchData();
        initListener();
    }

    public void fetchData(){
        list.clear();
        if(this.currentSearchKey.trim().equals("")){
            list.addAll(dataSrc.getList());
        } else {
            list.addAll(dataSrc.search(this.currentSearchKey));
        }
        adapter.notifyDataSetChanged();
    }

    public void initListener(){
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Player player = getFromForm();
                if(player != null){
                    list.add(player);
                    dataSrc.add(player);
                    fetchData();
                }
            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Player player = getFromForm();
                if(player != null){
                    list.set(currentPosition, player);
                    dataSrc.update(player, currentPosition);
                    fetchData();
                    btUpdate.setEnabled(false);
                    btAdd.setEnabled(true);
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
                currentSearchKey = newText;
                fetchData();
                return true;
            }
        });
    }

    public void setItemToForm(Player p){
        eName.setText(p.getName());
        eDate.setText(p.getDate());
        if(p.getGender() == imgs[0]) rbtMale.setChecked(true);
        else rbtFemale.setChecked(true);
        for(String s: p.getPosition()){
            if(s == "Hau ve") cbHauVe.setChecked(true);
            else cbHauVe.setChecked(false);
            if(s == "Tien ve") cbTienVe.setChecked(true);
            else cbTienVe.setChecked(false);
            if(s == "Tien dao") cbTienDao.setChecked(true);
            else cbTienDao.setChecked(false);
        }
    }

    public Player getFromForm(){
        String name = eName.getText().toString();
        String date = eDate.getText().toString();

        int img = imgs[0];
        if(rbtMale.isChecked()) img = imgs[0];
        else img = imgs[1];

        List<String> positions = new ArrayList<>();
        if(cbHauVe.isChecked()) positions.add("Hau ve");
        if(cbTienVe.isChecked()) positions.add("Tien ve");
        if(cbTienDao.isChecked()) positions.add("Tien dao");

        if(name.equals("") || date.equals("") || date.equals("Nhap ngay sinh") || (!cbHauVe.isChecked() && !cbTienVe.isChecked() && !cbTienDao.isChecked())){
            Toast.makeText(this, "Nhap lai", Toast.LENGTH_SHORT).show();
            return null;
        }
        try{
            Player p = new Player(name, date, img, positions);
            return p;
        } catch (Exception e){
            Toast.makeText(this, "Nhap lai", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private void initView() {
        eName = findViewById(R.id.eName);
        eDate = findViewById(R.id.eDate);
        rbtFemale = findViewById(R.id.rbtFemale);
        rbtMale = findViewById(R.id.rbtMale);
        cbHauVe = findViewById(R.id.cbHauVe);
        cbTienVe = findViewById(R.id.cbTienVe);
        cbTienDao = findViewById(R.id.cbTienDao);
        btAdd = findViewById(R.id.btAdd);
        btUpdate = findViewById(R.id.btUpdate);
        recyclerView = findViewById(R.id.recycleView);
        searchView = findViewById(R.id.searchView);

        eDate.setOnClickListener(this);
        btUpdate.setEnabled(false);
        rbtMale.setChecked(true);

        adapter = new PlayerAdapter(list, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
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
    public void onItemClick(Player player) {
        int pos = list.indexOf(player);
        this.currentPosition = pos;
        setItemToForm(player);
        btUpdate.setEnabled(true);
        btAdd.setEnabled(false);
    }
}