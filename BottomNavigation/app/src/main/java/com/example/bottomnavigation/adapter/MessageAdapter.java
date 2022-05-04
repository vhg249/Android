package com.example.bottomnavigation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.model.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHoleder>{
    private Context context;
    private List<Message> list;

    public MessageAdapter(Context context, List<Message> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        return new ViewHoleder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoleder holder, int position) {
        Message msg = list.get(position);
        holder.img.setImageResource(msg.getImg());
        holder.name.setText(msg.getName());
        holder.title.setText(msg.getTitle());
        holder.time.setText(msg.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoleder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView name, title, time;

        public ViewHoleder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.img);
            name = view.findViewById(R.id.tvName);
            title = view.findViewById(R.id.tvTitle);
            time = view.findViewById(R.id.tvTime);
        }
    }
}
