package com.example.footballplayerexample.dataSrc;

import com.example.footballplayerexample.model.Player;

import java.util.ArrayList;
import java.util.List;

public class DataSrc {
    private List<Player> list;

    public DataSrc() {
        list = new ArrayList<>();
    }

    public List<Player> getList() {
        return list;
    }

    public void setList(List<Player> list) {
        this.list = list;
    }

    public void add(Player player){
        list.add(player);
    }

    public void update(Player player, int position){
        list.set(position, player);
    }

    public List<Player> search(String key){
        List<Player> listItem = new ArrayList<>();
        for(Player item: list){
            if(item.getName().toLowerCase().contains(key.toLowerCase())){
                listItem.add(item);
            }
        }
        return listItem;
    }
}
