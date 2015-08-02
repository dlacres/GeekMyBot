package com.example.dllempia.myfirstapplication;

/**
 * Created by dllempia on 7/20/2015.
 */
public class Run {
    int count=0;
    RiseEdgeTrigger ret=new RiseEdgeTrigger();

    public Run(){
        this.count=0;
    }
    void step(){
        this.count++;
        return;
    }
    boolean stepBool(boolean in){
        boolean out;

        out = ret.step(in);

        this.count++;
        return(out);
    }
    int stepInt(){
        int out=0;
        this.count++;
        return(out);
    }
    int getCount(){
        return(this.count);
    }
}
