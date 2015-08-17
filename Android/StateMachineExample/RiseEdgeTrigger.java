package com.dlacres2.helloworld;

/**
 * Created by dllempia on 7/20/2015.
 */
public class RiseEdgeTrigger {
    public boolean z1;
    public boolean out;

    public RiseEdgeTrigger(){
        this.z1=false;
    }

    public boolean step(boolean in){

        this.out=false;
        if (in==true && this.z1==false) this.out=true;

        this.z1=in;

        return(this.out);
    }
}
