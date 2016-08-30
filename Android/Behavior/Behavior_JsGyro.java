package com.qualcomm.ftcrobotcontroller.geekmybot;

import com.qualcomm.robotcore.util.Range;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Lempia on 7/20/2015.
 * Use the left joystick to drive the robot
 * X is the direction
 * Y is the throttle
 */
public class Behavior_JsGyro implements Behavior {
    // Change the name of this behavior (for diagnostics)
    String behaviorName ="Js Gyro";

    // Add code for onEntry
    public void onEntry(){}
    // Add code for onExit
    public void onExit(){}

    // Add code for onLoop
    public void onLoop(){
        float kDir=1.0f;
        float kHdgDot=1.0f/30000.0f;
        float kSpd=0.5f;

        // Drive calculations using Joystick inputs;
        float directionCmd = gd.jsLeftX*kDir - gd.headingDot*kHdgDot;

        gd.rightCmd1 =  gd.jsLeftY*kSpd - directionCmd;
        gd.leftCmd1 = gd.jsLeftY*kSpd + directionCmd;

        gd.rightCmd = lim.calculate(gd.rightCmd1);
        gd.leftCmd = lim.calculate(gd.leftCmd1);
    }

    GlobalData gd;
    Limit lim;
    public Behavior_JsGyro(GlobalData gd, String behaviorName){
        this.lim = new Limit(1.0f);
        this.gd =gd;
        eventList=new ArrayList<Event>();
        this.behaviorName = behaviorName;
    }
    List <Event> eventList;

    public String getName(){return(this.behaviorName);}
    public List<Event> getEventList(){
        return(eventList);
    }
    public void addEvent(Event newEv){
        eventList.add(newEv);
    }
}
