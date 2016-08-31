package com.qualcomm.ftcrobotcontroller.geekmybot;

import java.util.List;

/**
 * Created by David Lempia on 7/20/2015.
 */
public class Behaviors_Joystick2 implements Behaviors {

    // Add the behaviors and events
    Behavior activeBehavior;
    public Behaviors_Joystick2(GlobalData gd){

        // Create the behaviors - Input is the name of the behavior for debug
        Behavior behaviorJsArcade = new Behavior_JsArcade(gd, "Js Arcade");
        Behavior behaviorJsTank = new Behavior_JsTank(gd, "Js Tank");

        // Create the events (lines)
        behaviorJsArcade.addEvent( new Event_ButtonY(gd, behaviorJsTank));
        behaviorJsTank.addEvent( new Event_ButtonY( gd, behaviorJsArcade));

        // The start state for the state machine
        activeBehavior = behaviorJsArcade;
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
