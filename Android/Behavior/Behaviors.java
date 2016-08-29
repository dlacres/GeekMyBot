package com.qualcomm.ftcrobotcontroller.geekmybot;

import java.util.List;

/**
 * Created by dllempia on 8/3/2015.
 */
public interface Behaviors {
    void onLoop();
    Behavior getActiveBehavior();
}
