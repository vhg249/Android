package com.example.ticketexample.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketexample.R;

import java.util.ArrayList;
import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder>{
    private Context context;
    private List<Ticket> mList;
    private TicketItemListener listener;

    public TicketAdapter(Context context, List<Ticket> list) {
        this.context = context;
        mList = list;
        this.listener = (TicketItemListener) context;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Ticket t = mList.get(position);
        if(t.isPaid()){
            holder.paid.setChecked(true);
        } else holder.paid.setChecked(false);
        holder.tvType.setText(t.getType());
        holder.tvCode.setText(t.getCode());
        holder.tvDate.setText(t.getDate());

        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mList != null) return mList.size();
        return 0;
    }

    public void add(Ticket t){
        mList.add(t);
        notifyDataSetChanged();
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvCode, tvDate, tvType;
        private CheckBox paid;
        private Button btRemove;

        public TicketViewHolder(@NonNull View view) {
            super(view);
            tvCode = view.findViewById(R.id.txtCode);
            tvDate = view.findViewById(R.id.txtDate);
            tvType = view.findViewById(R.id.txtType);
            paid = view.findViewById(R.id.paymentCheckbox);
            btRemove = view.findViewById(R.id.btRemove);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            System.out.println("click items");
            listener.onItemClick(view, getAdapterPosition());
        }
    }
    public interface TicketItemListener{
        void onItemClick(View view, int position);
    }
}
