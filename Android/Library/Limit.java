package com.example.dllempia.myfirstapplication;

/**
 * Created by dllempia on 7/20/2015.
 */
public class Limit {
    private int max=0;
    private int min=0;

    public Limit(int max, int min){
        this.max=max;
        this.min=min;
    }
    public Limit(int max){
        this.max=max;
        this.min=-max;
    }
    int step(int in){
        int out;

        out = in;
        if (in>this.max) out=this.max;
        if (in<this.min) out=this.min;
        return(out);
    }
    void setMax(int max){
        this.max=max;
    }
    void setMin(int min){
        this.min=min;
    }
}
