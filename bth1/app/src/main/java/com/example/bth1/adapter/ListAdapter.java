package com.example.bth1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bth1.R;
import com.example.bth1.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemHolder>{
    private List<Tour> list;
    private Listener listener;

    public ListAdapter(List<Tour> list, Listener listener) {
        this.list = list;
        this.listener = listener;
    }

    public ListAdapter() {
        this.list = new ArrayList<>();
    }

    public ListAdapter(List<Tour> list) {
        this.list = list;
    }

    public interface Listener {
        void onItemClick(Tour item);
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView itemLichTrinh, itemTgian;
        private ImageView itemImg;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            itemLichTrinh = itemView.findViewById(R.id.itemLichTrinh);
            itemTgian = itemView.findViewById(R.id.itemTgian);
            itemImg = itemView.findViewById(R.id.itemImg);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(list.get(getAdapterPosition()));
        }
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Tour tour = list.get(position);
        holder.itemLichTrinh.setText(tour.getLichTrinh());
        holder.itemTgian.setText(tour.getThoiGian());
        holder.itemImg.setImageResource(tour.getImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
