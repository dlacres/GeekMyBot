package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by dlacres2 on 1/17/2016.
 */
public class RiseEdgeTrigger {
    public boolean z1;
    public boolean out;

    public RiseEdgeTrigger(){
        this.z1=false;
    }

    public boolean calculate(boolean in){

        if (in==true && this.z1==false) this.out=true;
        else this.out=false;

        this.z1=in;

        return(this.out);
    }
}
