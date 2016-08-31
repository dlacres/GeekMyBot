package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.ftcrobotcontroller.geekmybot.Behaviors_Joystick;
import com.qualcomm.ftcrobotcontroller.geekmybot.GlobalData;
import com.qualcomm.ftcrobotcontroller.geekmybot.JsScale;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;

/**
 * Created by David Lempia on 8/12/2016.
 *
 * TODO Test - Does the Y button toggle between drive and IR?
 * TODO Test this module. Does the bot turn to the IR Beacon?
 * Features
 *   Core motor controller for left/right motor drive
 *   Joystick in arcade
 *   Rate Gyro used for steering
 */
public class H1TeleOp_Js2 extends OpMode {

    // Constructor
    Behaviors_Joystick be;
    GlobalData gd;
    public H1TeleOp_Js2(){
        // Setup the behavior model
        gd = new GlobalData();
        be = new Behaviors_Joystick(gd);
    }

    // Run one time
    DcMotor motorRight;
    DcMotor motorLeft;

    @Override
    public void init() {

        //hardwareMap.logDevices();

        motorRight = hardwareMap.dcMotor.get("m2");
        motorLeft = hardwareMap.dcMotor.get("m1");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // Initilize the past value of time.
        gd.time=(float)time;

        DbgLog.msg("Init Mode");
    }

    // Run periodically when sensors are read
    @Override
    public void loop() {
        // Pass Joystick to the behaviors
        gd.jsLeftY = gamepad1.left_stick_y;
        gd.jsRightY = gamepad1.right_stick_y;
        gd.jsLeftX = -gamepad1.left_stick_x;
        gd.buttonX = gamepad1.x;
        gd.buttonY = gamepad1.y;

        // Pass time to the behaviors
        gd.deltaTime = (float)time - gd.time;
        gd.time=(float)time;

        // Run the behaviors
        be.onLoop();

        // write the values to the motors
        motorRight.setPower(gd.rightCmd);
        motorLeft.setPower(gd.leftCmd);

        telemetry.addData("_Behavior", be.getActiveBehavior().getName());
        telemetry.addData("Power Left", Float.toString(gd.leftCmd));
        telemetry.addData("Power Right", Float.toString(gd.rightCmd));
        telemetry.addData("Left Js Y", Float.toString(gd.jsLeftY));
        telemetry.addData("Right Js Y", Float.toString(gd.jsLeftY));

        //DbgLog.msg("Run Mode");
    }
}
