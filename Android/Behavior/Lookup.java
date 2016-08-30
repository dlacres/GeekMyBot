package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by David Lempia on 8/13/2016.
 */
public class Lookup {

    /*float [][] table_1={
        {0, 0},
        {.03f, 0},
        {.05f, .03f},
        {.060f, .040f},
        {.0100f, .080f},
    };
    float s=5;*/
    float [][] table_1;
    float s;

    public Lookup(float [][] table_1, float matrixSize){
        this.table_1=table_1;
        this.s=matrixSize;
    }

    int i;
    float num,den;

    float calculate(float in){
        float out = 0.0f;
        float inAbs;

        inAbs=in;
        if (in<0) inAbs=-in;

        // Find the x region we are in
        for (i=0; i<s-1; ++i){
            if (table_1[i][0]<=inAbs && table_1[i+1][0]>inAbs)
                break;
        }
        num=table_1[i+1][1]-table_1[i][1];
        den=table_1[i+1][0]-table_1[i][0];
        out=(num*inAbs)/den + table_1[i][1] - (num*table_1[i][0])/den;

        if (in<0) out=-out;

        return(out);
    }
}
