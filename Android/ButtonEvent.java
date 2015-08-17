package com.dlacres2.helloworld;

import android.util.Log;

/**
 * Created by dlacres2 on 8/2/2015.
 */
public class ButtonEvent implements Event {
    private State toState;
    private MainActivity ma;

    ButtonEvent(MainActivity ma, State toState){
        this.toState=toState;
        this.ma=ma;
    }

    public State getToState() {
        return(this.toState);
    }

    public boolean isTriggered(){
        boolean b;

        b = ma.getIn1();
        if(b)
            Log.i("info", "Button Value true");
        else
            Log.i("info", "Button Value false");

        return(b);
    }

    public void onStep(){
        Log.i("info", "ButtonEvent Step");
    }
}
