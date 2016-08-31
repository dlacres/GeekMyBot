package com.qualcomm.ftcrobotcontroller.geekmybot;

import com.qualcomm.robotcore.util.Range;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Lempia on 7/20/2015.
 *
 * Use the joystick in tank mode to drive the robot
 * Left Y is the left motor
 * Right Y is the right motor
 */
public class Behavior_JsTank implements Behavior {
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
        gd.rightCmd = gd.jsRightY;
        gd.leftCmd = gd.jsLeftY;
    }
    GlobalData gd;
    List <Event> eventList;

    public Behavior_JsTank(GlobalData gd, String behaviorName){
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
