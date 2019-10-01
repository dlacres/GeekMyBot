package org.firstinspires.ftc.teamcode;

public class Limit {
    private double max = 0;
    private double min = 0;

    public Limit(double max, double min) {
        this.max = max;
        this.min = min;
    }

    public Limit(double max) {
        this.max = max;
        this.min = -max;
    }

    double calculate(double in) {
        double out;

        out = in;
        if (in > this.max) out = this.max;
        if (in < this.min) out = this.min;
        return (out);
    }

    void setMax(double max) {
        this.max = max;
    }

    void setMin(double min) {
        this.min = min;
    }
}