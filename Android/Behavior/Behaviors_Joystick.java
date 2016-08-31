package com.qualcomm.ftcrobotcontroller.geekmybot;

import java.util.List;

/**
 * Created by David Lempia on 7/20/2015.
 */
public class Behaviors_Joystick implements Behaviors {

    // Add the behaviors and events
    Behavior activeBehavior;
    public Behaviors_Joystick(GlobalData gd){

        // Create the behaviors - Input is the name of the behavior for debug
        Behavior behaviorJsBasic = new Behavior_JsArcade(gd, "Js Behavior");
        Behavior behaviorJsGyro = new Behavior_JsGyro(  gd, "Js Gyro Behavior");
        Behavior behaviorJsLookup = new Behavior_JsLookup(  gd, "Js Lookup Behavior");
        Behavior behaviorHeading = new Behavior_Heading(gd, "Heading");

        // Create the events (lines)
        behaviorJsBasic.addEvent( new Event_ButtonY(gd, behaviorHeading));
        behaviorHeading.addEvent( new Event_ButtonY( gd, behaviorJsGyro));
        behaviorJsGyro.addEvent( new Event_ButtonY( gd, behaviorJsLookup));
        behaviorJsLookup.addEvent( new Event_ButtonY( gd, behaviorJsBasic));

        // The start state for the state machine
        activeBehavior = behaviorJsBasic;
    }

    // Do not change this. This stays constant
    public void onLoop(){

        List<Event> el = activeBehavior.getEventList();

        //Log.i("info", "Made it to 1");
        for (Event el1 : el){
            //Log.i("info", "Made it to 2");
            if (el1.isTriggered()){
                // If the event happened, run exit, step, and entry code
                activeBehavior.onExit();
                el1.onTransition();

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
