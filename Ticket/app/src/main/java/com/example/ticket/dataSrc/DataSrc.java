package com.example.ticket.dataSrc;

import com.example.ticket.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class DataSrc {
    private List<Ticket> list;

    public DataSrc() {
        list = new ArrayList<>();
    }

    public List<Ticket> getList() {
        return list;
    }

    public void add(Ticket t){
        list.add(t);
    }

    public void update(int position, Ticket ticket){
        list.set(position, ticket);
    }

    public List<Ticket> search(String key){
        List<Ticket> listItem = new ArrayList<>();
        for(Ticket item: list){
            if(item.getCode().toLowerCase().contains(key.toLowerCase())){
                listItem.add(item);
            }
        }
        return listItem;
    }
}
