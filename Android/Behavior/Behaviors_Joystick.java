package com.qualcomm.ftcrobotcontroller.geekmybot;

import java.util.List;

/**
 * Created by dllempia on 7/20/2015.
 */
public class Behaviors_Joystick {

    // SM Inputs and Outputs
    public float throttleJs, directionJs;
    public float headingDot, heading;
    public float rightCmd, leftCmd;
    public boolean buttonX, buttonY;
    public double time,enterTime;

    // Declare the states and events
    Behavior behaviorJsBasic;
    Behavior behaviorJsGyro;
    Behavior behaviorForward;

    // Add the behaviors and events
    Behavior activeBehavior;
    public Behaviors_Joystick(){
        // Create the behaviors - Input is the name of the behavior for debug
        behaviorJsBasic = new Behavior_JsBasic(this,"Basic Behavior");
        behaviorJsGyro = new Behavior_JsGyro(this, "Gyro Behavior");
        behaviorForward = new Behavior_Forward(this, "Forward Stop Behavior", 45.0f);

        // Create the events (lines)
        behaviorJsGyro.addEvent( new Event_ButtonY( this, behaviorJsBasic));
        behaviorJsGyro.addEvent( new Event_ButtonX( this, behaviorForward));
        behaviorJsBasic.addEvent( new Event_ButtonY(this, behaviorJsGyro));
        behaviorJsBasic.addEvent( new Event_ButtonX(this, behaviorForward));
        behaviorForward.addEvent( new Event_Time(   this, behaviorJsBasic, 1.0));
        behaviorForward.addEvent( new Event_ButtonX(this, behaviorJsBasic));

        // The start state for the state machine
        activeBehavior = behaviorJsBasic;
    }

    // Do not change this. This stays constant
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
