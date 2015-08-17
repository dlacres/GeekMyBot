package com.dlacres2.helloworld;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllempia on 7/20/2015.
 */
public class ColorState implements State {
    MainActivity ma;
    List <Event> eventList;
    String stateName="none";

    public ColorState(MainActivity ma, String stateName){
        this.ma=ma;
        eventList=new ArrayList<Event>();
        this.stateName=stateName;
    }
    public List<Event> getEventList(){
        return(eventList);
    }
    public void addEvent(Event newEv){
        eventList.add(newEv);
    }
    public void onEntry(){
        // Set color of widget to the correct color
    }
    public void onExit(){

    }
    public void onStep(){
        ma.setOutAStr(stateName);
    }
}
