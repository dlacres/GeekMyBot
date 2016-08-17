package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by dlacres2 on 8/2/2015.
 */
public class Event_ButtonX implements Event {

    // Returning true triggers the event
    public boolean isTriggered(){
        boolean button_pressed;
        button_pressed = pressed.calculate(be.buttonX);
        return(button_pressed);
    }

    // This runs if the event is triggered
    public void onStep(){

    }

    // No change needed
    RiseEdgeTrigger pressed;
    private Behavior toBehavior;
    Behaviors_Joystick be;
    Event_ButtonX(Behaviors_Joystick be, Behavior toBehavior){
        this.be =be;
        this.toBehavior = toBehavior;
        pressed = new RiseEdgeTrigger();
    }

    // No change needed
    public Behavior getToBehavior() {
        return(this.toBehavior);
    }
}
