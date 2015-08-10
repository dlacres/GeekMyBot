package com.dlacres2.helloworld;

/**
 * Created by dllempia on 7/20/2015.
 */
public class Run {
    int count=0;
    //RiseEdgeTrigger ret=new RiseEdgeTrigger();
    StateMachine sm = new StateMachine();

    public Run(){
        this.count=0;
    }
    void step(){
        this.count++;
        return;
    }
    boolean stepBool(boolean in){
        boolean out=false;

        //out = ret.step(in);

        // Run the state machine one time
        //sm.onStep();

        this.count++;
        return(out);
    }
    int stepInt(){
        int out=0;
        this.count++;
        return(out);
    }
    int getCount(){
        return(this.count);
    }
}
