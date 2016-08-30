package com.qualcomm.ftcrobotcontroller.geekmybot;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Lempia on 7/20/2015.
 *
 * Turn to face the IR Beacon
 * Drive forward / backward until the signal strength is the commanded signal strength
 */
public class Behavior_IrTurn implements Behavior {
    // Add code for onEntry. This method is called one time when entering the behavior.

    // Change the name of this behavior (for diagnostics)
    String behaviorName="Ir Turn";

    public void onEntry(){

    }
    // Add code for onExit. This method is called one time when exiting the behavior.
    public void onExit(){

    }
    // Add code for onLoop. This method is called from loop as long as the behavior is active.
    public void onLoop(){
        float kHdgDot=1.0f/30000.0f;
        float direction=0.0f,speed=0.0f;
        float kDir=1.0f/400f;
        float kSpd=4.0f;
        float distCmd = 0.25f;

        // Is an IR signal detected?
        if (gd.irDetected) {
            direction= - (float)gd.irAngle*kDir - gd.headingDot*kHdgDot;
            speed = (float)gd.irStrength*kSpd - distCmd;

            // IR signal is detected
            gd.rightCmd=lim.calculate(speed-direction);
            gd.leftCmd=lim.calculate(speed+direction);
        } else {
            // no IR signal is detected
            direction= - gd.headingDot*kHdgDot;
            speed=0.0f;

            // IR signal is detected
            gd.rightCmd=lim.calculate(speed-direction);
            gd.leftCmd=lim.calculate(speed+direction);
        }
    }

    // Add new classes here
    List <Event> eventList;
    GlobalData gd;
    Limit lim;
    public Behavior_IrTurn(GlobalData gd, String behaviorName){
        this.gd=gd;
        eventList=new ArrayList<Event>();
        this.behaviorName=behaviorName;
        lim=new Limit(1.0f);
    }
    public List<Event> getEventList(){
        return(eventList);
    }
    public void addEvent(Event newEv){
        eventList.add(newEv);
    }

    @Override
    public String getName() {return(this.behaviorName);}
}
