package com.qualcomm.ftcrobotcontroller.geekmybot;

import java.util.List;

/**
 * Created by dllempia on 8/3/2015.
 */
public interface Behavior {
    void onEntry();
    void onExit();
    void onStep();
    void addEvent(Event newEv);
    String getName();
    List<Event> getEventList();
}
