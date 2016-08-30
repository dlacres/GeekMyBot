package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by David Lempia on 1/17/2016.
 */
public class PidControl {
    float Kp = 7;
    float Ki = 1;
    float Kd = -6;
    float iLimit=0.2f;

    float errorZ1 = 0f;
    float iError = 0f;
    float pTerm=0, iTerm=0, dTerm=0, pTerm_Wheel=0, dTerm_Wheel=0;
    Limit limitGuardGain=new Limit(iLimit);

    public PidControl(float Kp, float Ki, float Kd, float iLimit){
        this.Kp=Kp;
        this.Ki=Ki;
        this.Kd=Kd;
        this.iLimit=iLimit;
    }
    public void setKp(float Kp){ this.Kp=Kp; }
    public void setKi(float Ki){ this.Ki=Ki; }
    public void setKd(float Kd){ this.Kd=Kd; }
    public void setILimit(float iLimit){ this.iLimit=iLimit; }

    public float calculate(float targetPosition, float currentPosition, float currentRate)   {
        float error = targetPosition - currentPosition;
        pTerm = Kp * error;
        iError += error;
        iTerm = Ki * limitGuardGain.calculate(iError);
        dTerm = Kd * currentRate;
        return(pTerm + iTerm + dTerm);
    }
    public float calculate(float targetPosition, float currentPosition)   {
        float error = targetPosition - currentPosition;
        pTerm = Kp * error;
        iError += error;
        iTerm = Ki * limitGuardGain.calculate(iError);
        dTerm = Kd * (error - errorZ1);
        errorZ1 = error;
        return(pTerm + iTerm + dTerm);
    }
}
