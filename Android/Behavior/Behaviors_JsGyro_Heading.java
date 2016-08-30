package com.qualcomm.ftcrobotcontroller.geekmybot;

import java.util.List;

/**
 * Created by David Lempia on 7/20/2015.
 */
public class Behaviors_JsGyro_Heading {

    // Add the states and events used in this state machine
    public Behaviors_JsGyro_Heading(GlobalData gd){

        // Create the states - Input is the name of the state for debug
        Behavior behaviorJsGyro = new Behavior_JsGyro(  gd, "Js With Gyro Behavior");
        Behavior behaviorHdgTurn = new Behavior_Heading(  gd, "Heading Turn Behavior");

        // Create the events - Behavior From, Event, Behavior To
        behaviorHdgTurn.addEvent( new Event_ButtonY(gd, behaviorJsGyro));
        behaviorJsGyro.addEvent( new Event_ButtonY(gd, behaviorHdgTurn));

        // The start state for the state machine
        activeBehavior = behaviorJsGyro;
    }

    // Do not change this. This stays constant
    Behavior activeBehavior;
    public void onLoop(){

        List<Event> el = activeBehavior.getEventList();

        //Log.i("info", "Made it to 1");
        for (Event el1 : el){
            //Log.i("info", "Made it to 2");
            if (el1.isTriggered()){
                // If the event happened, run exit, step, and entry code
                activeBehavior.onExit();
                el1.onLoop();

                // Update the active state
                activeBehavior = el1.getToBehavior();
                activeBehavior.onEntry();
                break; // Do not check the rest of the transitions
            }
        }
        // Run active state
        activeBehavior.onLoop();
    }
    public Behavior getActiveBehavior(){
        return(this.activeBehavior);
    }
}
