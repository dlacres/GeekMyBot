package com.dlacres2.helloworld;

/**
 * Created by dlacres2 on 8/12/2015.
 */
public class DataServer{
    private String out1;
    private String out2;
    private String in1;
    private StateMachine sm;
    private State activeState;
    StateMachine.ColorEnum c;

    DataServer(StateMachine sm) {
        this.sm = sm;
    }

    // The GUI SM inputs
    public boolean getIn1(){
        if (in1.equals("t"))
            return(true);

        return (false);
    }
    public void setIn1(String in1){
        this.in1=in1;
    }

    // The step function color.
    public void setOut1(StateMachine.ColorEnum c){
        this.c=c;
    }
    public String getOut1(){
        String stateStepColor="None";
        if (c==StateMachine.ColorEnum.GREEN) stateStepColor="Green Color";
        if (c==StateMachine.ColorEnum.RED) stateStepColor="Red Color";

        return(stateStepColor);
    }
    // The state machine active state
    public String getOut2(){
        return(sm.getActiveState());
    }

}
