package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by dlacres2 on 8/2/2015.
 *
 * Copy this template and rename it with the name of your event. i.e. Event_ButtonX.
 * Inputs and Outputs are put in the Behaviors_xx file. Access them using be.xx
 * Add the event to the Behaviors_xx file.
 * Add inputs and outputs to the Behaviors_xx file.
 * Pass parameters in through the Event_Template Constructor.
 */
public class Event_Template implements Event {
    private Behavior toBehavior;

    // Returning true triggers the event
    // Pass variables
    public boolean isTriggered(){
        return(false);
    }

    // This runs if the event is triggered
    public void onStep(){

    }

    // Change Behaviors_XX to the name of your behaviors class
    // Add parameters as needed
    Behaviors_Joystick be;
    Event_Template(Behaviors_Joystick be, Behavior toBehavior){
        this.be=be;
        this.toBehavior = toBehavior;
    }

    // No change needed
    public Behavior getToBehavior() {
        return(this.toBehavior);
    }
}
