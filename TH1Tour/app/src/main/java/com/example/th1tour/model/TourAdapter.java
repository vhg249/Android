package com.example.th1tour.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th1tour.R;

import java.util.ArrayList;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {
    private Context context;
    private List<Tour> mList;

    public TourAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
        Tour tour = mList.get(position);
        if(tour == null) return;
        holder.img.setImageResource(tour.getImage());
        holder.tvName.setText(tour.getName());
        holder.tvTime.setText(tour.getTime());
    }

    @Override
    public int getItemCount() {
        if(mList != null) return mList.size();
        return 0;
    }

    public class TourViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tvName, tvTime;
        private Button btRemove;

        public TourViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.img);
            tvName = view.findViewById(R.id.txtName);
            tvTime = view.findViewById(R.id.txtTime);
            btRemove = view.findViewById(R.id.btRemove);
        }
    }
}
