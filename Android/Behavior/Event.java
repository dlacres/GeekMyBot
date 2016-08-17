package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by dlacres2 on 8/2/2015.
 */
public interface Event {

    public void onStep();
    public boolean isTriggered();
    public Behavior getToBehavior();
}
