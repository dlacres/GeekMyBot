package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by dlacres2 on 8/2/2015.
 */
public class Event_ButtonY implements Event {
    private Behavior toBehavior;

    // Returning true triggers the event
    public boolean isTriggered(){
        boolean button_pressed = pressed.calculate(be.buttonY);
        return(button_pressed);
    }

    // This runs if the event is triggered
    public void onStep(){

    }

    // No change needed
    RiseEdgeTrigger pressed;
    Behaviors_Joystick be;
    Event_ButtonY(Behaviors_Joystick be, Behavior toBehavior){
        this.be = be;
        this.toBehavior = toBehavior;
        pressed = new RiseEdgeTrigger();
    }

    // No change needed
    public Behavior getToBehavior() {
        return(this.toBehavior);
    }
}
