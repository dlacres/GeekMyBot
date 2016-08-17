package com.qualcomm.ftcrobotcontroller.geekmybot;

import java.util.List;

/**
 * Created by dllempia on 7/20/2015.
 */
public class Behaviors_Template {

    // Public SM Inputs and Outputs

    // Declare the states and events

    // Add the states and events used in this state machine
    public Behaviors_Template(){
        // Create the states - Input is the name of the state for debug

        // Create the events - Input is the state transitioned to

        // Add the events to the "from state"

        // The start state for the state machine
    }

    // Do not change this. This stays constant
    Behavior activeBehavior;
    public void onStep(){

        List<Event> el = activeBehavior.getEventList();

        //Log.i("info", "Made it to 1");
        for (Event el1 : el){
            //Log.i("info", "Made it to 2");
            if (el1.isTriggered()){
                // If the event happened, run exit, step, and entry code
                activeBehavior.onExit();
                el1.onStep();

                // Update the active state
                activeBehavior = el1.getToBehavior();
                activeBehavior.onEntry();
                break; // Do not check the rest of the transitions
            }
        }
        // Run active state
        activeBehavior.onStep();
    }
    public Behavior getActiveBehavior(){
        return(this.activeBehavior);
    }
}
