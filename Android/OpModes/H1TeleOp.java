package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.ftcrobotcontroller.geekmybot.JsScale;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by dlacres2 on 8/12/2016.
 *
 * Features
 *   Core motor controller for left/right motor drive
 *   Joystick in arcade
 *   Rate Gyro used for steering
 */
public class H1TeleOp extends OpMode {

    // Constructor
    JsScale jsScale;
    public H1TeleOp(){

    }

    // Run one time
    DcMotor motorRight;
    DcMotor motorLeft;
    @Override
    public void init() {
        motorRight = hardwareMap.dcMotor.get("m2");
        motorLeft = hardwareMap.dcMotor.get("m1");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    // Run periodically when sensors are read
    @Override
    public void loop() {
        // throttle: left_stick_y ranges from -1 to 1, where -1 is full up, and
        // 1 is full down
        // direction: left_stick_x ranges from -1 to 1, where -1 is full left
        // and 1 is full right
        float throttleJs = gamepad1.right_stick_y;
        float directionJs = -gamepad1.right_stick_x;

        float right = (throttleJs - directionJs);
        float left = (throttleJs + directionJs);

        // write the values to the motors
        motorRight.setPower(right);
        motorLeft.setPower(left);

        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Time", "elapsed time: " + Double.toString(this.time));
        telemetry.addData("Throttle", Float.toString(throttleJs));
        telemetry.addData("rightPower", Float.toString(right));

        DbgLog.msg("Run Mode");
    }
}
