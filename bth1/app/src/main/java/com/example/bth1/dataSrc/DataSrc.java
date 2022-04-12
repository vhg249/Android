package com.example.bth1.dataSrc;

import com.example.bth1.model.Model;

import java.util.ArrayList;
import java.util.List;

public class DataSrc<T extends Model> {
    private List<T> list;

    public DataSrc() {
        list = new ArrayList<>();
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isUniqId(int id) {
        for(T item:list) {
            if(item.getId() == id) return false;
        }
        return true;
    }

    public void add(T item) {
        list.add(item);
    }

    public void update(int id, T item) {
        for(int i=0;i<list.size();i++) {
            if(list.get(i).getId() == id) {
                list.set(i, item);
                return;
            }
        }
    }

    public void delete(int id) {
        for(int i=0;i<list.size();i++) {
            if(list.get(i).getId() == id) {
                list.remove(i);
                return;
            }
        }
    }

    public void deleteBatch(List<Integer> ids) {
        List<T> newList = new ArrayList<>();
        for(int i = 0 ; i < list.size(); i++) {
            if(!ids.contains(list.get(i).getId())) {
                newList.add(list.get(i));
            }
        }
        list.clear();
        list.addAll(newList);
    }

    public List<T> search(String key) {
        List<T> listItem = new ArrayList<>();
        for(T item:list) {
            if(item.getLichTrinh().toLowerCase().contains(key.toLowerCase())) listItem.add(item);
        }
        return listItem;
    }
}
