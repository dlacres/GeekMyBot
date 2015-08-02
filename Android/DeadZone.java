package com.example.dllempia.myfirstapplication;

/**
 * Created by dllempia on 7/20/2015.
 */
public class DeadZone {

    public DeadZone(){
    }
    int step(int in, int size){
        int out;

        out=in;
        if ((in<size && in>-size)) out=0;

        return(out);
    }
}
