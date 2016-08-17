package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by dlacres2 on 1/10/2016.
 */
public class RateSensor {
    private static final int GYR_Y=0;                              // Gyro Y (IMU pin #4)
    private static final int ACC_Z=1;                              // Acc  Z (IMU pin #7)
    private static final int ACC_X=2;                              // Acc  X (IMU pin #9)

    RateSensor rs=new RateSensor();
    float ACC_angle;
    float GYRO_rate;

    private static final int STD_LOOP_TIME  = 9;

    float[] sensorValue = { 0, 0, 0};
    float[] sensorZero = { 0, 0, 0 };
    int lastLoopTime = STD_LOOP_TIME;
    int lastLoopUsefulTime = STD_LOOP_TIME;
    long loopStartTime = 0;

    void setup() {
        calibrateSensors();
        return;
    }

    void printOut() {
        int skip = 0;
        if (skip++ == 20) {
            skip = 0;
            //Serial.print(ACC_angle);          Serial.print(",");
            //Serial.print(GYRO_rate);          Serial.print("\\n");
            //telemetry.addData("1. x", String.format("%03d", xVal));
        }
        return;
    }

    // Sensors Module   -----------------------------------------------------------------------------------
    void calibrateSensors() {                                       // Set zero sensor values
        int v;
        for (int n = 0; n < 3; n++) {
            v = 0;
            //for (int i = 0; i < 50; i++) v += readSensor(n);
            sensorZero[n] = v / 50;
        }                                                            //(618 - 413)/2 = 102.5    330/3.3 = x/1024
        sensorZero[ACC_Z] -= 100;    //102;                          // Sensor: horizontal, upward
    }

    void updateSensors() {                                         // data acquisition
        int v;
        for (int n = 0; n < 3; n++) {
            v = 0;
            //for (int i = 0; i < 5; i++) v += readSensor(n);
            sensorValue[n] = v / 5 - sensorZero[n];
        }
    }

    float getGyroRate_rps() {                                             // ARef=3.3V, Gyro sensitivity=2mV/(deg/sec)
        return(sensorValue[GYR_Y]);// * (float) 4.583333333);                 // in quid/sec:(1024/360)/1024 * 3.3/0.002)
    }

    float getAccAngle_Degrees() {
        return (float)Math.toDegrees(Math.atan2(-sensorValue[ACC_Z], -sensorValue[ACC_X]));    // in radians not Quid: 1024/(2*PI))
    }

/* int arctan2(int y, int x) {                                    // http://www.dspguru.com/comp.dsp/tricks/alg/fxdatan2.htm
    int coeff_1 = 128;                                          // angle in Quids (1024 Quids=360°)
    int coeff_2 = 3 * coeff_1;
    float abs_y = abs(y) + 1e-10;
    float r, angle;

    if (x >= 0) {
        r = (x - abs_y) / (x + abs_y);
        angle = coeff_1 - coeff_1 * r;
    } else {
        r = (x + abs_y) / (abs_y - x);
        angle = coeff_2 - coeff_1 * r;
    }
    if (y < 0) return int(-angle);
    else return int(angle);
}*/}
