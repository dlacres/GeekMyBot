package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by dlacres2 on 1/17/2016.
 *
 * Notes:
 *   1) deltaTime must be calculated every frame
 *      in the calling opmode and passed in via GlobalData.
 *      TODO test rate limit
 */
public class Rate_Limit {
    private float rate = 0;
    private float z1 = 0;
    private GlobalData gd;

    public Rate_Limit(float rate, float positionInit, GlobalData gd) {
        this.rate = rate;
        this.gd=gd;
        z1 = positionInit;
    }

    public Rate_Limit(float rate, GlobalData gd) {
        this.rate = rate;
        this.gd=gd;
        z1 = gd.heading;
    }

    public void init(float positionInit){
        z1 = positionInit;
    }

    public float calculate(float in) {

        float rateDt = rate*gd.deltaTime;

        if (in-z1 >= rateDt) z1 = z1 + rateDt;
        else if (z1-in >= rateDt) z1 = z1 - rateDt;
        else z1=in;
        return(z1);
    }
}