package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by dlacres2 on 1/17/2016.
 *
 * Notes:
 *   1) deltaTime must be calculated every frame
 *      in the calling opmode and passed in via GlobalData.
 */
public class Lowpass {
    private float tau = 0;
    private float z1 = 0;
    private GlobalData gd;

    public Lowpass(float tau, float positionInit, GlobalData gd) {
        this.tau = tau;
        this.gd=gd;
        z1 = positionInit;
    }

    public Lowpass(float tau, GlobalData gd) {
        this.tau = tau;
        this.gd=gd;
        z1 = 0.0f;
    }

    public void init(float positionInit){
        z1 = positionInit;
    }

    public float calculate(float in) {
        z1 = z1 + tau*gd.deltaTime * (in - z1);
        return(z1);
    }
}