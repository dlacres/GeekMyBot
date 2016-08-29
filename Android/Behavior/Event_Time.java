package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by dlacres2 on 8/2/2015.
 */
public class Event_Time implements Event {

    // Returning true triggers the event
    public boolean isTriggered(){
        boolean timeToStop;
        timeToStop = (gd.time- gd.enterTime) > this.stopTime;
        return(timeToStop);
    }

    // This runs if the event is triggered
    public void onLoop(){

    }

    // No change needed
    RiseEdgeTrigger pressed;
    private Behavior toBehavior;
    GlobalData gd;
    double stopTime;
    Event_Time(GlobalData gd, Behavior toBehavior, double stopTime){
        this.gd =gd;
        this.stopTime = stopTime;
        this.toBehavior = toBehavior;
        pressed = new RiseEdgeTrigger();
    }

    // No change needed
    public Behavior getToBehavior() {
        return(this.toBehavior);
    }
}
