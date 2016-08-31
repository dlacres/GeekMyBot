package com.qualcomm.ftcrobotcontroller.geekmybot;

import com.qualcomm.robotcore.util.Range;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Lempia on 7/20/2015.
 *
 * Use the left joystick in arcade mode to drive the robot
 * X is the direction
 * Y is the throttle
 */
public class Behavior_JsArcade implements Behavior {
    // Change the name of this behavior (for diagnostics)
    String behaviorName="JS Basic";

    // Add code for onEntry
    public void onEntry(){

    }
    // Add code for onExit
    public void onExit(){

    }
    // Add code for onTransition
    public void onLoop(){
        // Drive calculations using Joystick inputs;
        float throttle = gd.jsLeftY/2.0f;
        float direction = gd.jsLeftX/3.0f;
        float right =  throttle - direction;
        float left = throttle + direction;
        gd.rightCmd = Range.clip(right, -1, 1);
        gd.leftCmd = Range.clip(left, -1, 1);
    }
    GlobalData gd;
    List <Event> eventList;

    public Behavior_JsArcade(GlobalData gd, String behaviorName){
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
