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
        gd.enterTime=gd.time;
    }
    // Add code for onExit. This method is called one time when exiting the behavior.
    public void onExit(){
        gd.rightCmd=0.0f;
        gd.leftCmd=0.0f;
    }
    // Add code for onLoop. This method is called from loop as long as the behavior is active.
    public void onLoop(){
        //if (gd.time<this.timeToStop){
        //gd.rightCmd=this.power_percent/100.0f;
        //gd.leftCmd=this.power_percent/100.0f;
        gd.rightCmd= -0.5f;
        gd.leftCmd=  -0.5f;
   }

    // Update with the Behaviors_xx class name. Add initial behavior values.
    private float power_percent;
    GlobalData gd;
    public Behavior_Forward(GlobalData gd, String behaviorName,
                           float power_percent){
        this.power_percent = power_percent;

        // Do not change this code
        this.gd=gd;
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
