package com.example.dllempia.myfirstapplication;

/**
 * Created by dllempia on 7/20/2015.
 */
public class FallEdgeTrigger {
    public boolean z1;
    public boolean out;

    public FallEdgeTrigger(){
        this.z1=false;
    }

    public boolean step(boolean in){

        this.out=false;
        if (in==false && this.z1==true) this.out=true;

        this.z1=in;

        return(this.out);
    }
}
