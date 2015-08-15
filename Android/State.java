package com.dlacres2.helloworld;

/**
 * Created by dllempia on 8/3/2015.
 */
public interface State {
    void onEntry();
    void onExit();
    void onStep();
}
