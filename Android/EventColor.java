package com.dlacres2.helloworld;

/**
 * Created by dlacres2 on 8/2/2015.
 */
public class EventColor implements Event {
    private State fromState;
    private State toState;
    private boolean buttonZ1;
    private DataServer ds;

    EventColor(State fromState, State toState, DataServer ds){
        this.fromState=fromState;
        this.toState=toState;
        this.buttonZ1=false;
        this.ds=ds;
    }

    public State getFromState(){
        return(this.fromState);
    }
    public State getToState()   { return(this.toState);   }

    public boolean isTriggered(){
        boolean e1=false;
        boolean buttonEvent;

        buttonEvent = ds.getIn1();

        if (buttonEvent &! buttonZ1) e1=true;
        buttonZ1=buttonEvent;

        return(e1);
    }

    public void onStep(){
    }
}
