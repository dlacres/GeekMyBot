package com.dlacres2.helloworld;

import java.util.List;

/**
 * Created by dllempia on 7/20/2015.
 */
public class StateMachine {

    State stateRed;
    State stateGreen;
    Event red2Green;
    Event green2Red;
    State activeState;
    MainActivity ma;

    StateMachine(MainActivity ma){
        this.ma=ma;
        // Create the states
        stateRed = new ColorState(ma, "Red State Name");
        stateGreen = new ColorState(ma, "Green State Name");
        // Create the events
        red2Green = new ButtonEvent(ma, stateGreen);
        green2Red = new ButtonEvent(ma, stateRed);
        // Add the events to the "from state"
        stateRed.addEvent(red2Green);
        stateGreen.addEvent(green2Red);

        activeState = stateRed;
    }

    public void onStep(){

        List<Event> el = activeState.getEventList();

        //Log.i("info", "Made it to 1");
        for (Event el1 : el){
            //Log.i("info", "Made it to 2");
            if (el1.isTriggered()){
                // If the event happened, run exit, step, and entry code
                activeState.onExit();
                el1.onStep();

                // Update the active state
                activeState = el1.getToState();
                activeState.onEntry();
                break; // Do not check the rest of the trasitions
            }
        }
        // Run active state
        activeState.onStep();
    }
    public String getActiveState(){
        String actStr="None";
        if (activeState==stateGreen) actStr="Green State Active";
        if (activeState==stateRed) actStr="Red State Active";

        return(actStr);
    }
}
