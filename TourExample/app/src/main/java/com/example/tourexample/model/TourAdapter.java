package com.example.tourexample.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tourexample.R;

import java.util.ArrayList;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {
    private Context context;
    private List<Tour> mList;
    private TourItemListener mTourItem;

    public TourAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
    }

    public void setClickListener (TourItemListener mTourItem){
        this.mTourItem = mTourItem;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Tour tour = mList.get(position);
        if(tour == null) return;
        holder.img.setImageResource(tour.getImg());
        holder.tvName.setText(tour.getName());
        holder.tvTime.setText(tour.getTime());

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

    public void add(Tour t){
        mList.add(t);
        notifyDataSetChanged();
    }

    public void update(int position, Tour tour){
        mList.set(position, tour);
        notifyDataSetChanged();
    }

    public Tour getItem(int position){
        return mList.get(position);
    }

    public class TourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;
        private TextView tvName, tvTime;
        private Button btRemove;

        public TourViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.img);
            tvName = view.findViewById(R.id.txtName);
            tvTime = view.findViewById(R.id.txtTime);
            btRemove = view.findViewById(R.id.btRemove);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mTourItem != null){
                mTourItem.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface TourItemListener {
        void onItemClick(View view, int position);
    }
}
