package com.example.footballplayerexample.model;

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

import com.example.footballplayerexample.R;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>{
    private List<Player> list;
    private Listener listener;

    public PlayerAdapter(List<Player> list, Listener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Player p = list.get(position);
        holder.tvName.setText(p.getName());
        holder.tvDate.setText(p.getDate());
        holder.image.setImageResource(p.getGender());
        for(String s: p.getPosition()){
            if(s == "Hau ve") holder.cbHauVe.setChecked(true);
            if(s == "Tien ve") holder.cbTienVe.setChecked(true);
            if(s == "Tien dao") holder.cbTienDao.setChecked(true);
        }
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

    public class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView image;
        private TextView tvName, tvDate;
        private CheckBox cbHauVe, cbTienVe, cbTienDao;
        private Button btRemove;

        public PlayerViewHolder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.avatar);
            tvName = view.findViewById(R.id.tvName);
            tvDate = view.findViewById(R.id.tvDate);
            cbHauVe = view.findViewById(R.id.cbHauVe);
            cbTienVe = view.findViewById(R.id.cbTienVe);
            cbTienDao = view.findViewById(R.id.cbTienDao);
            btRemove = view.findViewById(R.id.btRemove);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(list.get(getAdapterPosition()));
        }
    }

    public interface Listener{
        void onItemClick(Player player);
    }
}
