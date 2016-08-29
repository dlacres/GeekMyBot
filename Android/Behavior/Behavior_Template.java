package com.qualcomm.ftcrobotcontroller.geekmybot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllempia on 7/20/2015.
 *
 * Copy this template and rename it with the name of your behavior. i.e. Behavior_Forward.
 * Inputs and Outputs are put in the Behaviors_xx file. Access them using gd.xx
 * Add the behavior to the Behaviors_xx file.
 * Add inputs and outputs to the Behaviors_xx file.
 * Pass parameters in through the Event_Template Constructor.
 */
public class Behavior_Template implements Behavior {
    // Change the name of this behavior (for diagnostics)
    String behaviorName="none";

    // Add code for onEntry. This method is called one time when entering the behavior.
    public void onEntry(){

    }
    // Add code for onExit. This method is called one time when exiting the behavior.
    public void onExit(){

    }
    // Add code for onLoop. This method is called from loop as long as the behavior is active.
    public void onLoop(){

    }

    // Add new classes as needed
    List <Event> eventList;
    GlobalData gd;
    public Behavior_Template(GlobalData gd, String behaviorName){
        this.gd=gd;
        eventList=new ArrayList<Event>();
        this.behaviorName=behaviorName;
    }
    public List<Event> getEventList(){
        return(eventList);
    }
    public void addEvent(Event newEv){
        eventList.add(newEv);
    }

    @Override
    public String getName() {
        return(this.behaviorName);
    }
}
