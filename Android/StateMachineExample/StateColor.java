package com.dlacres2.helloworld;

import android.graphics.Color;

/**
 * Created by dllempia on 7/20/2015.
 */
public class StateColor implements State {
    StateMachine.ColorEnum color;

    public StateColor(StateMachine.ColorEnum color){
        this.color=color;
    }
    public void onEntry(){
        // Set color of widget to the correct color
    }
    public void onExit(){

    }
    public void onStep(StateMachine sm){
        sm.setOut1(this.color);
    }
}
