package com.example.dllempia.myfirstapplication;
import android.os.Handler;
import android.os.Looper;

/**
 * A class used to perform periodical updates,
 * specified inside a runnable object. An update interval
 * may be specified (otherwise, the class will perform the
 * update every 2 seconds).
 *
 * @author Carlos Simões
 */
public class PeriodicTask {

    // Create a Handler that uses the Main Looper to run in
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private Runnable mStatusChecker;
    private int UPDATE_INTERVAL = 2000; //2 Seconds

    /**
     * Creates an PeriodicTask object, that can be used to
     * perform PeriodicTask on a specified time interval.
     *
     * @param periodicTask A runnable containing the update routine.
     */
    public PeriodicTask(final Runnable periodicTask) {
        mStatusChecker = new Runnable() {
            @Override
            public void run() {
                // Run the passed runnable
                periodicTask.run();
                // Re-run it after the update interval
                mHandler.postDelayed(this, UPDATE_INTERVAL);
            }
        };
    }

    /**
     * The same as the default constructor, but specifying the
     * intended update interval.
     *
     * @param periodicTask A runnable containing the update routine.
     * @param interval  The interval over which the routine
     *                  should run (milliseconds).
     */
    public PeriodicTask(Runnable periodicTask, int interval){
        this(periodicTask);
        UPDATE_INTERVAL = interval;
    }

    /**
     * Starts the periodical update routine (mStatusChecker
     * adds the callback to the handler).
     */
    public synchronized void startUpdates(){
        mStatusChecker.run();
    }

    /**
     * Stops the periodical update routine from running,
     * by removing the callback.
     */
    public synchronized void stopUpdates(){
        mHandler.removeCallbacks(mStatusChecker);
    }
}
