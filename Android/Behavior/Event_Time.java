package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by dlacres2 on 8/2/2015.
 */
public class Event_Time implements Event {

    // Returning true triggers the event
    public boolean isTriggered(){
        boolean timeToStop;
        timeToStop = (be.time-be.enterTime) > this.stopTime;
        return(timeToStop);
    }

    // This runs if the event is triggered
    public void onStep(){

    }

    // No change needed
    RiseEdgeTrigger pressed;
    private Behavior toBehavior;
    Behaviors_Joystick be;
    double stopTime;
    Event_Time(Behaviors_Joystick be, Behavior toBehavior, double stopTime){
        this.be =be;
        this.stopTime = stopTime;
        this.toBehavior = toBehavior;
        pressed = new RiseEdgeTrigger();
    }

    // No change needed
    public Behavior getToBehavior() {
        return(this.toBehavior);
    }
}
