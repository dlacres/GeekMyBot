package org.firstinspires.ftc.teamcode;


public class RateLimit {
  private double rateLimit=0.0;
  private double z1=0.0;
  private double dt=50.0;
  
  public RateLimit(double rl, double z1, double dt){
      this.dt = dt;
      rateLimit = rl * dt / 1000.0;
  }
  
  public double calc(double in){
    if (in-this.z1 >= this.rateLimit) this.z1 = this.z1 + this.rateLimit;
    else if (this.z1-in >= this.rateLimit) this.z1 = this.z1 - this.rateLimit;
    else this.z1=in;
    return(this.z1);
  }
}  