package com.example.ticketexample;

import com.example.ticketexample.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public List<Ticket> list = new ArrayList<>();

    public void add(Ticket t){
        list.add(t);
    }
    public void remove(int position){
        list.remove(position);
    }
    public void update(Ticket t, int position){
        list.set(position, t);
    }
    public Ticket getByPosition(int position){
        return list.get(position);
    }
    public Ticket getByCode(String code){
        for(Ticket t: list){
            if(t.getCode().equals(code)){
                return t;
            }
        }
        return null;
    }
}
