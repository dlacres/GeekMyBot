package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous

public class Test_Autonomous extends LinearOpMode {

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    double i;
    double tmp;
    ElapsedTime PeriodicTimer;

    // Put initialization blocks here.
    waitForStart();
    PeriodicTimer = new ElapsedTime();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Put loop blocks here.
        for (i = 1; i <= 10000; i++) {
          tmp = i * i;
        }
        telemetry.addData("key", PeriodicTimer.time());
        PeriodicTimer.reset();
        telemetry.update();
      }
    }
  }
}
