package com.example.vemaybay.model;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vemaybay.R;

import java.util.ArrayList;
import java.util.List;

public class VeAdapter extends RecyclerView.Adapter<VeAdapter.VeViewHolder> {
    private List<Ve> list, listBackup;
    private Context context;
    private VeItemListener VeItem;
    public VeAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        listBackup = new ArrayList<>();
    }

    public List<Ve> getListBackup() {
        return listBackup;
    }

    public void filterList(List<Ve> filterlist){
        list = filterlist;
        notifyDataSetChanged();
    }

    public void setClickListener(VeItemListener VeItem){
        this.VeItem = VeItem;
    }
    @NonNull
    @Override
    public VeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ve, parent, false);
        return new VeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VeViewHolder holder, int position) {
        Ve ve = list.get(position);
        if(ve==null)
            return;
        holder.thongTinVe.setText(ve.getMaVe()+" - "+ve.getLoai());
        holder.ngayBay.setText("Ngay bay: "+ve.getNgayBay());
        if (ve.isPaid()==true){
            holder.isPaid.setChecked(true);
        }
        holder.btXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thong bao xoa!!!");
                builder.setMessage("Ban co chac chan muon xoa ve " + holder.thongTinVe.getText().toString() + " nay khong?");
                builder.setIcon(R.drawable.ic_baseline_delete_24);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        list.remove(position);
                        listBackup.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog= builder.create();
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        return 0;
    }

    public void them(Ve ve){
        list.add(ve);
        listBackup.add(ve);
        notifyDataSetChanged();
    }

    public void sua(int position, Ve ve) {
        list.set(position,ve);
        listBackup.set(position, ve);
        notifyDataSetChanged();
    }

    public void xoa(int position){
        list.remove(position);
        notifyDataSetChanged();
    }

    public Ve getItem(int position){
        return list.get(position);

    }
    public class VeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       private TextView thongTinVe, ngayBay;
       private CheckBox isPaid;
       private Button btXoa;

       public VeViewHolder(@NonNull View view) {
           super(view);
           thongTinVe = view.findViewById(R.id.thongtinve);
           ngayBay = view.findViewById(R.id.ngaybay);
           isPaid = view.findViewById(R.id.daTT);
           btXoa = view.findViewById(R.id.btXoa);
           view.setOnClickListener(this);
       }

       @Override
       public void onClick(View view) {
           if(VeItem!=null){
               VeItem.onItemClick(view, getAdapterPosition());
           }
       }
    }
    public interface VeItemListener {
        void onItemClick(View view, int position);
    }
}
