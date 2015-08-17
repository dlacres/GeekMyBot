package com.dlacres2.helloworld;

import java.util.List;

/**
 * Created by dllempia on 8/3/2015.
 */
public interface State {
    void onEntry();
    void onExit();
    void onStep();
    void addEvent(Event newEv);
    List<Event> getEventList();
}
