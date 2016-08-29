package com.qualcomm.ftcrobotcontroller.geekmybot;

import com.qualcomm.robotcore.util.Range;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllempia on 7/20/2015.
 */
public class Behavior_JsBasic implements Behavior {
    // Change the name of this behavior (for diagnostics)
    String behaviorName="JS Basic";

    // Add code for onEntry
    public void onEntry(){

    }
    // Add code for onExit
    public void onExit(){

    }
    // Add code for onLoop
    public void onLoop(){
        // Drive calculations using Joystick inputs;
        float throttle = gd.throttleJs/2.0f;
        float direction = gd.directionJs/3.0f;
        float right =  throttle - direction;
        float left = throttle + direction;
        gd.rightCmd = Range.clip(right, -1, 1);
        gd.leftCmd = Range.clip(left, -1, 1);
    }
    GlobalData gd;
    List <Event> eventList;

    public Behavior_JsBasic(GlobalData gd, String behaviorName){
        this.gd = gd;
        eventList=new ArrayList<Event>();
        this.behaviorName=behaviorName;
    }
    public String getName(){return(this.behaviorName);}
    public List<Event> getEventList(){
        return(eventList);
    }
    public void addEvent(Event newEv){
        eventList.add(newEv);
    }
}
