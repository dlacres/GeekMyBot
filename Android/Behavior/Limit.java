package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by David Lempia on 1/17/2016.
 */
public class Limit {
    private float max = 0;
    private float min = 0;

    public Limit(float min, float max) {
        this.max = max;
        this.min = min;
    }

    public Limit(float max) {
        this.max = max;
        this.min = -max;
    }

    public float calculate(float in) {
        float out;

        out = in;
        if (in > this.max) out = this.max;
        if (in < this.min) out = this.min;
        return (out);
    }

    public void setMax(float max) {
        this.max = max;
    }

    public void setMin(float min) {
        this.min = min;
    }
}