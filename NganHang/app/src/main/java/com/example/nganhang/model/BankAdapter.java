package com.example.nganhang.model;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nganhang.R;

import java.util.List;

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.BankViewHolder> {
    private List<Bank> list;
    private BankListener listener;

    public BankAdapter(List<Bank> list, BankListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new BankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BankViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Bank b = list.get(position);
        holder.icon.setImageResource(b.getIcon());
        holder.tvTime.setText(b.getTime());
        holder.tvInterest.setText("lai suat "+b.getInterest());
        holder.cbOnline.setChecked(b.isOnline());

        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null) return list.size();
        return 0;
    }

    public class BankViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView icon;
        private TextView tvTime, tvInterest;
        private CheckBox cbOnline;
        private Button btRemove;

        public BankViewHolder(@NonNull View view) {
            super(view);
            icon = view.findViewById(R.id.icon);
            tvTime = view.findViewById(R.id.tvTime);
            tvInterest = view.findViewById(R.id.tvInterest);
            cbOnline = view.findViewById(R.id.cbOnline);
            btRemove = view.findViewById(R.id.btRemove);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(list.get(getAdapterPosition()));
        }
    }

    public interface BankListener{
        void onItemClick(Bank bank);
    }

}
