package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "Test_RateLimit", group = "")
public class Test_RateLimit extends LinearOpMode {
  
  final double dt = 50.0;
  Periodic periodic = new Periodic(dt); // 50 ms or 20 hertz
  RateLimit rl = new RateLimit(1.0, 0.0, dt);
  long i=0;
  double out=0.0;
  double in=5.0;
  double max=5.0;
  double min=-5.0;
  
  @Override
  public void runOpMode() {
    
    waitForStart();

    while (opModeIsActive()){
      if (out >= max) {
        in = min;
      } else if (out <= min){
        in = max;
      }
      out = rl.calc(in);
      
      telemetry.addData("dt_remaining", periodic.TimeRemaining());
      telemetry.addData("Count",i++);
      telemetry.addData("RateLimit",out);
      telemetry.update();
      periodic.Wait();
    }
  }
}