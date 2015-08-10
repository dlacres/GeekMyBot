package com.dlacres2.helloworld;

/**
 * Created by dllempia on 7/20/2015.
 */
public class StateMachine {

    public enum ColorEnum{RED,GREEN};
    State stateRed = new StateColor(ColorEnum.RED);
    State stateGreen = new StateColor(ColorEnum.GREEN);
    Event red2Green = new EventColor(stateRed, stateGreen);
    Event green2Red = new EventColor(stateGreen, stateRed);
    Event eventList[] = { red2Green, green2Red };
    State activeState = stateRed;

    String out1="None";
    String out2="None";
    String in1="None";

    StateMachine(){

    }

    public void onStep(String in1){
        this.in1=in1;

        // Check transitions leaving active state
        for (int i = 0; i < eventList.length; i++) {

            // Does this event leave the active state?
            if (eventList[i].getFromState()==activeState){

                //Check all events that leave this state
                if (eventList[i].isTriggered(this)){

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
        activeState.onStep(this);
    }
    public void setOut1(StateMachine.ColorEnum color) {
        if (StateMachine.ColorEnum.RED == color)
            out1="Red";
        else if (StateMachine.ColorEnum.GREEN == color)
            out1="Green";
        else
            out1="None";
    }
    public String getOut2(){
        if (activeState==stateGreen) out2="Green State";
        if (activeState==stateRed) out2="Red State";

        return(out2);
    }
    public String getOut1(){

        return(out1);
    }
    public boolean getIn1(){
        if (in1.equals("t"))
            return(true);

        return (false);
    }

}
