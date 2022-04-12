package com.example.th1work.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th1work.R;

import java.util.ArrayList;
import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkViewHolder>{
    private Context context;
    private List<Work> mList;

    public WorkAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
    }

    public void add(Work w){
        mList.add(w);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new WorkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkViewHolder holder, int position) {
        Work work = mList.get(position);
        if(work == null) return;
        holder.imgGender.setImageResource(work.getGenderImg());
        holder.tvName.setText(work.getName());
        holder.tvDetail.setText(work.getDetail());
        holder.tvDate.setText(work.getDate());

        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if(mList != null) return mList.size();
        return 0;
    }

    public class WorkViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgGender;
        private TextView tvName, tvDetail, tvDate;
        private Button btRemove;

        public WorkViewHolder(@NonNull View view) {
            super(view);
            imgGender = view.findViewById(R.id.imageGender);
            tvName = view.findViewById(R.id.txtName);
            tvDetail = view.findViewById(R.id.txtDetail);
            tvDate = view.findViewById(R.id.txtDate);
            btRemove = view.findViewById(R.id.btRemove);
        }
    }
}
