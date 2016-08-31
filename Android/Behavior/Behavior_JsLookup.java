package com.qualcomm.ftcrobotcontroller.geekmybot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Lempia on 7/20/2015.
 * Use the left joystick to drive the robot
 * X is the direction
 * Y is the throttle
 */
public class Behavior_JsLookup implements Behavior {
    // Change the name of this behavior (for diagnostics)
    String behaviorName ="Js Gyro";

    // Add code for onEntry
    public void onEntry(){}
    // Add code for onExit
    public void onExit(){}

    // Add code for onTransition
    public void onLoop(){
        //float kHdgDot=1.0f/30000.0f;
        float kSpd=1.0f;
        float kDir=0.5f;

        float jsX = lookup.calculate(gd.jsLeftX);
        float jsY = lookup.calculate(gd.jsLeftY);

        gd.debugFloat1=jsY;
        gd.debugFloat2=jsX;

        gd.rightCmd1 = jsY*kSpd - jsX*kDir;
        gd.leftCmd1  = jsY*kSpd + jsX*kDir;

        gd.rightCmd = lim.calculate(gd.rightCmd1);
        gd.leftCmd = lim.calculate(gd.leftCmd1);
    }

    float [][] jsTable={
        {0.0f, 0.0f},
        {0.3f, 0.1f},
        {0.5f, 0.25f},
        {0.80f, 0.55f},
        {1.0f, 1.0f},
    };
    float siz=4;
    Lookup lookup;
    GlobalData gd;
    Limit lim;
    public Behavior_JsLookup(GlobalData gd, String behaviorName){
        this.lim = new Limit(1.0f);
        this.lookup = new Lookup(jsTable, siz);
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
