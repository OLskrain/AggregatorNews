package com.olskrain.aggregatornews.mvp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Ievlev on 22,Апрель,2019
 */

public final class Model {

    private static Model instance;
    private List<Integer> counters;

    private Model(){
        counters = new ArrayList<>();
        counters.add(0);
        counters.add(0);
        counters.add(0);
    }

    public int getAt(int pos){
        return  counters.get(pos);
    }

    public void setAt (int pos, int value){
        counters.set(pos, value);
    }

    public List<Integer> getCounters() {
        return counters;
    }

    public static synchronized Model getInstance(){
        if (instance == null){
            instance = new Model();
        }
        return instance;
    }
}
