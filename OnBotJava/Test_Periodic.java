package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "Test_Periodic", group = "")
public class Test_Periodic extends LinearOpMode {
  
  Periodic periodic = new Periodic(50.0); // 50 ms or 20 hertz
  long i=0;
  
  @Override
  public void runOpMode() {
    
    waitForStart();

    while (opModeIsActive()){
      
      telemetry.addData("dt_remaining", periodic.TimeRemaining());
      telemetry.addData("Count",i++);
      telemetry.update();
      periodic.Wait();
    }
  }
}