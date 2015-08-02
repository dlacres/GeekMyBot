package com.example.dllempia.myfirstapplication;

/**
 * Created by dllempia on 7/20/2015.
 */
public class StateMachine {
    protected enum State{
        S100,
        S200;
    }
    protected enum Transition{
        T100,
        T200;
    }

    private State state = State.S100;

    int state(){

    }
    int nextState() {

    }
}
