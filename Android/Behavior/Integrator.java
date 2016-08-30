package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by David Lempia on 1/17/2016.
 */
public class Integrator {
    private float max = 0;
    private float min = 0;
    private GlobalData gd;
    private float positionInit=0.0f;

    public Integrator(float max, float positionInit, GlobalData gd) {
        this.max = max;
        this.min = -max;
        this.gd=gd;
        this.positionInit=positionInit;
    }

    public float calculate(float in) {
        float out;

        positionInit = positionInit + in * gd.deltaTime;

        if (positionInit > this.max) positionInit = this.max;
        if (positionInit < this.min) positionInit = this.min;

        return (positionInit);
    }

    public void init(float positionInit){
        this.positionInit = positionInit;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public void setMin(float min) {
        this.min = min;
    }
}