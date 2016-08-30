package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by David Lempia on 8/2/2015.
 */
public class Event_ButtonX implements Event {

    // Returning true triggers the event
    public boolean isTriggered(){
        boolean button_pressed = pressed.calculate(gd.buttonX);
        return(button_pressed);
    }

    // This runs if the event is triggered
    public void onLoop(){

    }

    // No change needed
    RiseEdgeTrigger pressed;
    private Behavior toBehavior;
    GlobalData gd;
    Event_ButtonX(GlobalData gd, Behavior toBehavior){
        this.gd =gd;
        this.toBehavior = toBehavior;
        pressed = new RiseEdgeTrigger();
    }

    // No change needed
    public Behavior getToBehavior() {
        return(this.toBehavior);
    }
}
