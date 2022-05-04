package com.example.ticket.model;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder>{
    private List<Ticket> list;
    private Listener listener;

    public TicketAdapter(List<Ticket> list, Listener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new TicketViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Ticket t = list.get(position);
        holder.tvCode.setText(t.getCode());
        holder.tvType.setText(t.getType());
        holder.tvDate.setText(t.getDate());
        holder.cbIsPaid.setChecked(t.isPaid());
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

    public class TicketViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvCode, tvDate, tvType;
        private CheckBox cbIsPaid;
        private Button btRemove;

        public TicketViewHolder(@NonNull View view) {
            super(view);
            tvCode = view.findViewById(R.id.tvCode);
            tvDate = view.findViewById(R.id.tvDate);
            tvType = view.findViewById(R.id.tvType);
            cbIsPaid = view.findViewById(R.id.cbIsPaid);
            btRemove = view.findViewById(R.id.btRemove);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClickItem(list.get(getAdapterPosition()));
        }
    }

    public interface Listener{
        void onClickItem(Ticket ticket);
    }
}
