package com.example.dllempia.myfirstapplication;
/**
 * Created by dllempia on 7/21/2015.
 */
public class ExamplePeriodicTaskStart {

    PeriodicTask mPeriodicTask = new PeriodicTask(new Runnable() {
        @Override
        public void run() {
            // do stuff ...
        }
    });

// Start updates
    mPeriodicTask.startUpdates();

// Stop updates
    mPeriodicTask.stopUpdates();
}
