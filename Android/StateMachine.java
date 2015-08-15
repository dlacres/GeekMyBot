package com.dlacres2.helloworld;

/**
 * Created by dllempia on 7/20/2015.
 */
public class StateMachine {

    public enum ColorEnum{RED,GREEN};
    DataServer ds = new DataServer(this);
    State stateRed = new StateColor(ColorEnum.RED, ds);
    State stateGreen = new StateColor(ColorEnum.GREEN, ds);
    Event red2Green = new EventColor(stateRed, stateGreen, ds);
    Event green2Red = new EventColor(stateGreen, stateRed, ds);
    Event eventList[] = { red2Green, green2Red };
    State activeState = stateRed;

    StateMachine(){

    }

    public void onStep(String in1){

        // Check transitions leaving active state
        for (int i = 0; i < eventList.length; i++) {

            // Does this event leave the active state?
            if (eventList[i].getFromState()==activeState){

                //Check all events that leave this state
                if (eventList[i].isTriggered()){

                    // If the event happened, run exit, step, and entry code
                    activeState.onExit();
                    eventList[i].onStep();

                    // Update the active state
                    activeState = eventList[i].getToState();
                    activeState.onEntry();
                    break; // Do not check the rest of the trasitions
                }
            }
        }
        // Run active state
        activeState.onStep();
    }
    public String getActiveState(){
        String actSt="None";
        activeState=activeState;
        if (activeState==stateGreen) actSt="Green State";
        if (activeState==stateRed) actSt="Red State";

        return(actSt);
    }
    public DataServer getDs(){
        return(ds);
    }
}
