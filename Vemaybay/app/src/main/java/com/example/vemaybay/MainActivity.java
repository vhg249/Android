package com.example.vemaybay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.vemaybay.model.Ve;
import com.example.vemaybay.model.VeAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        VeAdapter.VeItemListener, SearchView.OnQueryTextListener{
    private RecyclerView recyclerView;
    private VeAdapter adapter;
    private EditText maVe, ngayBay;
    private RadioButton vip, pt, gr, daTra, chuaTra;
    private Button btThem, btSua;
    private SearchView searchView;
    private int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        adapter = new VeAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        searchView.setOnQueryTextListener(this);
        btThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mv = maVe.getText().toString();
                String expression = "^[a-zA-Z0-9]+";
                String dateRegex = "([0-9]{1,2})/([0-9]{1,2})/([0-9]{4})";
                String nb = ngayBay.getText().toString();
                if (!mv.matches(expression) || !nb.matches(dateRegex)){
                    showAlert();
                }else{
                    String loai="";
                    Boolean isPaid;
                    if(vip.isChecked())
                        loai = "VIP";
                    if(pt.isChecked())
                        loai = "Pho thong";
                    if(gr.isChecked())
                        loai = "Gia re";
                    if(daTra.isChecked())
                        isPaid = true;
                    else
                        isPaid = false;
                    adapter.them(new Ve(mv,loai,nb,isPaid));
                }
            }
        });
        btSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ve ve = new Ve();
                ve.setMaVe(maVe.getText().toString());
                if(vip.isChecked())
                    ve.setLoai("VIP");
                if(pt.isChecked())
                    ve.setLoai("Pho Thong");
                if(gr.isChecked())
                    ve.setLoai("Gia re");
                ve.setNgayBay(ngayBay.getText().toString());
                if(daTra.isChecked())
                    ve.setPaid(true);
                else
                    ve.setPaid(false);
                adapter.sua(pos, ve);
                btThem.setEnabled(true);
                btSua.setEnabled(false);
            }
        });

    }

    private void initView(){
        recyclerView = findViewById(R.id.rv);
        maVe = findViewById(R.id.maVe);
        vip = findViewById(R.id.rbVip);
        pt = findViewById(R.id.rbPt);
        gr = findViewById(R.id.rbGr);
        ngayBay = findViewById(R.id.ngay);
        ngayBay.setOnClickListener(this);
        daTra = findViewById(R.id.rbDaTT);
        chuaTra = findViewById(R.id.rbChuaTT);
        btThem = findViewById(R.id.btThem);
        btSua = findViewById(R.id.btSua);
        btSua.setEnabled(false);
        searchView = findViewById(R.id.search);
    }

    @Override
    public void onClick(View view) {
        if(view==ngayBay){
            Calendar c=Calendar.getInstance();
            int y=c.get(Calendar.YEAR);
            int m=c.get(Calendar.MONTH);
            int d=c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                    ngayBay.setText(dd+"/"+(mm+1)+"/"+yy);
                }
            },y,m,d);
            dialog.show();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        btThem.setEnabled(false);
        btSua.setEnabled(true);
        pos = position;
        Ve ve = adapter.getItem(pos);
        maVe.setText(ve.getMaVe());
        System.out.println(ve.getLoai()+"");
        if(ve.getLoai().equalsIgnoreCase("VIP")){
            vip.setChecked(true);
        }
        if(ve.getLoai().equalsIgnoreCase("Pho thong")){
            pt.setChecked(true);
        }
        if(ve.getLoai().equalsIgnoreCase("Gia re")){
            gr.setChecked(true);
        }
        if(ve.isPaid()){
            daTra.setChecked(true);
        }
        else {
            chuaTra.setChecked(true);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        filter(s);
        return false;
    }

    private void filter(String s){
        List<Ve> filterlist=new ArrayList<>();
        for(Ve i:adapter.getListBackup()){
            if(i.getMaVe().toLowerCase().contains(s.toLowerCase())){
                filterlist.add(i);
            }
        }
        if(filterlist.isEmpty()){
            Toast.makeText(this,"No data found", Toast.LENGTH_SHORT).show();
        }
        else {
            adapter.filterList(filterlist);
        }
    }
    private void showAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Alert!!!");
        builder.setMessage("Xin vui long nhap theo yeu cau !!!");
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}