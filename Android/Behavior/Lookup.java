package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by dlacres2 on 8/13/2016.
 */
public class Lookup {

    float [][] table_1={
        {0, 0},
        {.03f, 0},
        {.05f, .03f},
        {.060f, .040f},
        {.0100f, .080f},
    };
    float s=5;

    public Lookup(){
    }

    int i;
    float num,den;

    float Lookup1(int in){
        float out = 0;

        // Find the x region we are in
        for (i=0; i<s-1; ++i){
            if (table_1[i][0]<=in && table_1[i+1][0]>in)
                break;
        }
        num=table_1[i+1][0]-table_1[i][0];
        den=table_1[i+1][0]-table_1[i][0];
        out=(num*in)/den + table_1[i][0] - (num*table_1[i][0])/den;
        //out = (int)((float)(table_1[i+1][y]-table_1[i][y])/((float)(table_1[i+1][x]-table_1[i][x])))*(float)in;

        return(out);
    }
}
