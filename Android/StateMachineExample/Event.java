package com.dlacres2.helloworld;

/**
 * Created by dlacres2 on 8/2/2015.
 */
public interface Event {

    public void onStep();
    public boolean isTriggered();
    public State getToState();
}
