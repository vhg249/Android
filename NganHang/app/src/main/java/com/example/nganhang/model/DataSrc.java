package com.example.nganhang.model;

import java.util.ArrayList;
import java.util.List;

public class DataSrc {
    private List<Bank> list;

    public DataSrc() {
        list = new ArrayList<>();
    }

    public List<Bank> getList() {
        return list;
    }

    public void setList(List<Bank> list) {
        this.list = list;
    }

    public void add(Bank bank){
        list.add(bank);
    }

    public void update(Bank bank, int position){
        list.set(position, bank);
    }

    public List<Bank> search(String key){
        List<Bank> listItem = new ArrayList<>();
        for(Bank item: list){
            if(item.getName().toLowerCase().contains(key.toLowerCase())){
                listItem.add(item);
            }
        }
        return listItem;
    }
}
