package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

public class Periodic {
  
  double dt=50.0; //50 ms = 20 hertz
  ElapsedTime PeriodicTimer;

  public Periodic(double dt) {
    this.dt = dt;
    this.PeriodicTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
  }
  
  public Periodic() {
    this.PeriodicTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
  }

  public double getDt(){
    return(dt);
  }
  
  public double TimeRemaining(){
    return(dt - PeriodicTimer.time());
  }
  
  public void Wait() {
    while(PeriodicTimer.time()<this.dt){
    }
    PeriodicTimer.reset();
  }
}

