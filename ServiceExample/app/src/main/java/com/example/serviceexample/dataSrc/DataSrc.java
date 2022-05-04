package com.example.serviceexample.dataSrc;

import com.example.serviceexample.model.Service;

import java.util.ArrayList;
import java.util.List;

public class DataSrc {
    private List<Service> list;

    public DataSrc() {
        list = new ArrayList<>();
    }

    public List<Service> getList() {
        return list;
    }

    public void setList(List<Service> list) {
        this.list = list;
    }

    public void add(Service service){
        if(list != null)  list.add(service);
    }

    public void update(Service item, int position){
        list.set(position, item);
    }
}
