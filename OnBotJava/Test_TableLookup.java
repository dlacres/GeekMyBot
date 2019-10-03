package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "Test_TableLookup", group = "")
public class Test_TableLookup extends LinearOpMode {
  
  Periodic periodic = new Periodic(50.0); 
  long i=0;
  oldA
  double input=0.0;
  double output=0.0;
  double table[][]={
      {0.0, 0.0},
      {1.0, 2.0},
      {3.0, 3.0},
      {6.0, 6.0}, 
      {10.0, 10.0}};
  TableLookup lookup = new TableLookup(table); 
  @Override
  public void runOpMode() {
    
    waitForStart();

    while (opModeIsActive()){
      output = lookup.calc(input);

      if ((boolean)gamepad1.a == true and gamepad1_a_old == false){
         input = input + 1.0;
      }
      gamepad1_a_old = (boolean)gamepad1.a;
      if (input > 10.0) input = 0.0;

      telemetry.addData("X_In", input);
      telemetry.addData("Y_Out", output);
      telemetry.addData("Count",i++);
      telemetry.update();
      periodic.Wait();
    }
  }
}