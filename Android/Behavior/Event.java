package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by David Lempia on 8/2/2015.
 */
public interface Event {

    public void onLoop();
    public boolean isTriggered();
    public Behavior getToBehavior();
}
