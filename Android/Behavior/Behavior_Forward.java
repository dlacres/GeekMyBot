package com.qualcomm.ftcrobotcontroller.geekmybot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllempia on 7/20/2015.
 *
 * Drive forward. Stop after a period of time.
 */
public class Behavior_Forward implements Behavior {
    // Add code for onEntry. This method is called one time when entering the behavior.
    public void onEntry(){
        be.enterTime=be.time;
    }
    // Add code for onExit. This method is called one time when exiting the behavior.
    public void onExit(){
        be.rightCmd=0.0f;
        be.leftCmd=0.0f;
    }
    // Add code for onStep. This method is called from loop as long as the behavior is active.
    public void onStep(){
        //if (be.time<this.timeToStop){
        //be.rightCmd=this.power_percent/100.0f;
        //be.leftCmd=this.power_percent/100.0f;
        be.rightCmd= -0.5f;
        be.leftCmd=  -0.5f;
   }

    // Update with the Behaviors_xx class name. Add initial behavior values.
    Behaviors_Joystick be;
    private float power_percent;
    public Behavior_Forward(Behaviors_Joystick be, String behaviorName,
                           float power_percent){
        this.power_percent = power_percent;

        // Do not change this code
        this.be=be;
        eventList=new ArrayList<Event>();
        this.behaviorName=behaviorName;
    }
    List <Event> eventList;
    String behaviorName="none";

    public String getName(){return(this.behaviorName);}
    public List<Event> getEventList(){ return(eventList); }
    public void addEvent(Event newEv){
        eventList.add(newEv);
    }
}
