package com.example.bottomnavigation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.adapter.MessageAdapter;
import com.example.bottomnavigation.model.Message;

import java.util.ArrayList;
import java.util.List;

public class FragmentNotification extends Fragment {
    MessageAdapter messageAdapter;
    RecyclerView recyclerView;
    List<Message> list;

    public FragmentNotification() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noti, container, false);
        list = new ArrayList<>();
        list.add(new Message(R.drawable.ic_cat, "Thanh", "Em an com chua?", "19:30"));
        list.add(new Message(R.drawable.ic_chub, "Binh", "Em an com chua?", "14:30"));
        list.add(new Message(R.drawable.ic_cat, "Vinh", "Em an com chua?", "12:30"));
        list.add(new Message(R.drawable.ic_cat, "Thanh", "Em an com chua?", "10:00"));
        list.add(new Message(R.drawable.ic_cat, "Vinh", "Em an com chua?", "08:30"));
        list.add(new Message(R.drawable.ic_cat, "Thanh", "Em an com chua?", "05:20"));

        recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        messageAdapter = new MessageAdapter(view.getContext(), list);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(messageAdapter);

        return view;
    }
}
