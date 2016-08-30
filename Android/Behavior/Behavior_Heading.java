package com.qualcomm.ftcrobotcontroller.geekmybot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Lempia on 7/20/2015.
 *
 * dHat left/right changes the heading command
 * Left joystick y changes throttle
 */
public class Behavior_Heading implements Behavior {
    // Change the name of this behavior (for diagnostics)
    String behaviorName ="Heading";

    // Add code for onEntry
    public void onEntry(){}
    // Add code for onExit
    public void onExit(){}

    // Add code for onLoop
    public void onLoop(){
        float kDir=0.02f;
        float kInt=0.002f; //Usually about 1/10 of proportional gain
        float kHdgDot=1.0f/25000.0f;
        float kSpd=0.5f;
        float headingCmd1=0.0f;

        // Smooth changes to the heading command
        float headingCmdRate = 90.0f; // Heading Change in degrees/second
        if (gd.dpadRt){
            gd.headingCmd = integratorHdgCmd.calculate(headingCmdRate);
        }
        if (gd.dpadLt){
            gd.headingCmd = integratorHdgCmd.calculate(-headingCmdRate);
        }
        if (riseEdgeDpadUp.calculate(gd.dpadUp)) gd.headingCmd=0;

        // Control the robot heading with a PID controller
        float headingErr = - gd.headingCmd + gd.heading;

        // Protect against rolling from 0 to 360.
        if (headingErr >=180.0f) headingErr -= 360.0f;
        if (headingErr <-180.0f) headingErr += 360.0f;

        // Add a small deadzone to eliminate oscillation because of low heading sensor resolution.
        if (headingErr < 2.0f && headingErr > -2.0f){
            integrator.init(0.0f);
        }
        float headingErrInt = integrator.calculate(headingErr * kInt);
        float directionCmd = lim_5.calculate(headingErr * kDir
                - gd.headingDot * kHdgDot + headingErrInt);
        gd.debugFloat1=directionCmd;

        // Calculate the left and right motor commands
        gd.rightCmd1 =  gd.jsLeftY*kSpd - directionCmd;
        gd.leftCmd1 = gd.jsLeftY*kSpd + directionCmd;

        // Limit the left and right motor commands. Send back to global data.
        gd.rightCmd = lim.calculate(gd.rightCmd1);
        gd.leftCmd = lim.calculate(gd.leftCmd1);
    }

    GlobalData gd;
    Limit lim,lim_5;
    Rate_Limit rateLimit;
    Integrator integrator;
    Integrator360 integratorHdgCmd;
    RiseEdgeTrigger riseEdgeDpadUp;
    public Behavior_Heading(GlobalData gd, String behaviorName){
        this.lim = new Limit(1.0f);
        this.lim_5 = new Limit(0.3f);
        //this.rateLimit = new Rate_Limit(10, gd);
        this.integrator = new Integrator(0.2f, 0.0f, gd);
        this.integratorHdgCmd = new Integrator360(0.0f, gd);
        this.riseEdgeDpadUp = new RiseEdgeTrigger();
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
