package com.example.serviceexample.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviceexample.R;
import com.example.serviceexample.model.Service;

import java.util.ArrayList;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>{
    private List<Service> list;
    private ServiceListener listener;

    public ServiceAdapter() {
        list = new ArrayList<>();
    }

    public ServiceAdapter(List<Service> list, ServiceListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Service service = list.get(position);
        System.out.println("brook "+service.getMeasure());
        holder.imgService.setImageResource(service.getImgService());
        holder.tvPrice.setText(service.getPrice()+"");
        holder.tvAmount.setText(service.getAmount()+"");
        holder.tvMeasure.setText(service.getMeasure());

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

    public class ServiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imgService;
        private TextView tvAmount, tvMeasure, tvPrice;
        private Button btRemove;

        public ServiceViewHolder(@NonNull View view) {
            super(view);
            imgService = view.findViewById(R.id.image);
            tvAmount = view.findViewById(R.id.tvAmount);
            tvMeasure = view.findViewById(R.id.tvMeasure);
            tvPrice = view.findViewById(R.id.tvPrice);
            btRemove = view.findViewById(R.id.btRemove);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(list.get(getAdapterPosition()));
        }
    }

    public interface ServiceListener{
        void onItemClick(Service service);
    }
}
