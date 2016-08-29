package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by dlacres2 on 8/20/2016.
 */
public class GlobalData {
    public GlobalData(){
    }
    // Gyro
    public float headingDot=0.0f, heading=0.0f, headingCmd=0.0f;

    // Motors
    public float rightCmd=0.0f, leftCmd=0.0f, rightCmd1=0.0f, leftCmd1=0.0f;

    // Joystick
    public float throttleJs=0.0f, directionJs=0.0f;
    public boolean buttonX, buttonY;
    public boolean dpadRt, dpadLt, dpadUp, dpadDn;

    // Time
    public float time,enterTime;
    public float deltaTime=0.0f;

    // IR Seeker
    public double irAngle,irStrength;
    public boolean irDetected;

    // Debug
    public float debugFloat1=0.0f, debugFloat2=0.0f;
    public int debugInt1, debugInt2;
    public double debugDouble1, debugDouble2;
    public boolean debugBoolean1, debugBoolean2;
}
