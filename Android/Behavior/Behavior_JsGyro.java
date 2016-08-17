package com.qualcomm.ftcrobotcontroller.geekmybot;

import com.qualcomm.robotcore.util.Range;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllempia on 7/20/2015.
 */
public class Behavior_JsGyro implements Behavior {

    // Add code for onEntry
    public void onEntry(){

    }
    // Add code for onExit
    public void onExit(){

    }
    // Add code for onStep
    public void onStep(){
        float kDir=1.0f;

        // Drive calculations using Joystick inputs;
        float directionCmd = (be.directionJs - be.headingDot) * kDir;

        float right =  be.throttleJs - directionCmd;
        float left = be.throttleJs + directionCmd;

        be.rightCmd = Range.clip(right, -1, 1);
        be.leftCmd = Range.clip(left, -1, 1);
    }

    Behaviors_Joystick be;
    public Behavior_JsGyro(Behaviors_Joystick be, String behaviorName){
        this.be =be;
        eventList=new ArrayList<Event>();
        this.behaviorName = behaviorName;
    }
    List <Event> eventList;
    String behaviorName ="none";

    public String getName(){return(this.behaviorName);}
    public List<Event> getEventList(){
        return(eventList);
    }
    public void addEvent(Event newEv){
        eventList.add(newEv);
    }
}
