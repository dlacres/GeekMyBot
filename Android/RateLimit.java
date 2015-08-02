package com.example.dllempia.myfirstapplication;

/**
 * Created by dllempia on 7/20/2015.
 */
public class RateLimit {
    private int z1=0;
    private int rate=0;

    public RateLimit(int rate, int z1){
        this.rate=rate;
        this.z1=z1;
    }
    public RateLimit(int rate){
        this.rate=rate;
        this.z1=0;
    }
    int step(int in){
        if (in-this.z1 >= this.rate) this.z1 = this.z1 + this.rate;
        else if (this.z1-in >= this.rate) this.z1 = this.z1 - this.rate;
        else this.z1=in;
        return(this.z1);
    }
}
