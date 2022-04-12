package com.example.workexample.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workexample.R;

import java.util.ArrayList;
import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkViewHolder> {
    private List<Work> list;
    private WorkListener listener;

    public WorkAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new WorkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(list != null) return list.size();
        return 0;
    }

    public WorkAdapter(List<Work> mList) {
        this.list = mList;
    }

    public WorkAdapter(List<Work> list, WorkListener listener) {
        this.list = list;
        this.listener = listener;
    }

    public class WorkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img;
        private TextView tvName, tvContent, tvDate;
        private Button btRemove;

        public WorkViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.image);
            tvName = view.findViewById(R.id.tvName);
            tvContent = view.findViewById(R.id.tvContent);
            tvDate = view.findViewById(R.id.tvDate);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
    public interface WorkListener{
        void onItemClick(Work item);
    }
}
