package com.example.workexample.dataSrc;

import com.example.workexample.model.Work;

import java.util.ArrayList;
import java.util.List;

public class DataSrc {
    public List<Work> mList = new ArrayList<>();

    public DataSrc() {
        mList = new ArrayList<>();
    }

    public void add(Work w){
        if(mList != null) mList.add(w);
    }

    public void update(Work w, int pos){
        mList.set(pos, w);
    }

    public Work getItemByPosition(int pos){
        return mList.get(pos);
    }

    public void remove(int pos){
        mList.remove(pos);
    }
}
