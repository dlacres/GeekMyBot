package com.qualcomm.ftcrobotcontroller.geekmybot;

import com.qualcomm.robotcore.util.Range;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllempia on 7/20/2015.
 */
public class Behavior_JsBasic implements Behavior {
    List <Event> eventList;
    String stateName="none";

    // Add code for onEntry
    public void onEntry(){

    }
    // Add code for onExit
    public void onExit(){

    }
    // Add code for onStep
    public void onStep(){
        // Drive calculations using Joystick inputs;
        float throttle = sm.throttleJs/2.0f;
        float direction = sm.directionJs/3.0f;
        float right =  throttle - direction;
        float left = throttle + direction;
        sm.rightCmd = Range.clip(right, -1, 1);
        sm.leftCmd = Range.clip(left, -1, 1);
    }
    Behaviors_Joystick sm;
    public Behavior_JsBasic(Behaviors_Joystick sm, String stateName){
        this.sm=sm;
        eventList=new ArrayList<Event>();
        this.stateName=stateName;
    }
    public String getName(){return(this.stateName);}
    public List<Event> getEventList(){
        return(eventList);
    }
    public void addEvent(Event newEv){
        eventList.add(newEv);
    }
}
