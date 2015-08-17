package com.example.dllempia.myfirstapplication;

/**
 * Created by dllempia on 7/20/2015.
 */
public class LowPass {
    private float tau;
    private float lpOld=0;
    private float out=0;

    public LowPass(float tau){
        this.tau=tau;
    }
    public int step(int in){

        out = this.lpOld + this.tau * ((float)in - this.lpOld);
        this.lpOld=out;
        return((int)this.lpOld);
    }
}
