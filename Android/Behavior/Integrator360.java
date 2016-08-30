package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by David Lempia on 1/17/2016.
 * Integrate around a circle. At 359.999 integrate up to 0.000.
 */
public class Integrator360 {
    private GlobalData gd;
    private float positionInit=0.0f;

    public Integrator360(float positionInit, GlobalData gd) {
        this.gd=gd;
        this.positionInit=positionInit;
    }

    public float calculate(float in) {
        float out;

        positionInit = positionInit + in * gd.deltaTime;

        if (positionInit >= 360.0f) positionInit = positionInit - 360.0f;
        if (positionInit <= 0.0f) positionInit = positionInit + 360.0f;

        return (positionInit);
    }

    public void init(float positionInit){
        this.positionInit = positionInit;
    }
}