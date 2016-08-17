package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by dlacres2 on 1/17/2016.
 */
public class Limit {
    private float max = 0;
    private float min = 0;

    public Limit(float max, float min) {
        this.max = max;
        this.min = min;
    }

    public Limit(float max) {
        this.max = max;
        this.min = -max;
    }

    float calculate(float in) {
        float out;

        out = in;
        if (in > this.max) out = this.max;
        if (in < this.min) out = this.min;
        return (out);
    }

    void setMax(float max) {
        this.max = max;
    }

    void setMin(float min) {
        this.min = min;
    }
}