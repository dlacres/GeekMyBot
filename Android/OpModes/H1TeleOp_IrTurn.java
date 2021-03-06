package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.geekmybot.Behaviors_JsGyro_IRFollow;
import com.qualcomm.ftcrobotcontroller.geekmybot.GlobalData;
import com.qualcomm.ftcrobotcontroller.geekmybot.JsScale;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;

/**
 * Created by dlacres2 on 8/12/2016.
 *
 * TODO Test - Does the Y button toggle between drive and IR?
 * TODO Test this module. Does the bot turn to the IR Beacon?
 * Features
 *   Core motor controller for left/right motor drive
 *   Joystick in arcade
 *   Rate Gyro used for steering
 */
public class H1TeleOp_IrTurn extends OpMode {

    // Constructor
    JsScale jsScale;
    public H1TeleOp_IrTurn(){
        jsScale=new JsScale();
    }

    // Run one time
    DcMotor motorRight;
    DcMotor motorLeft;
    GyroSensor sensorGyro;
    Behaviors_JsGyro_IRFollow be;
    GlobalData gd;
    IrSeekerSensor irSeeker;

    @Override
    public void init() {
        irSeeker = hardwareMap.irSeekerSensor.get("ir");
        motorRight = hardwareMap.dcMotor.get("m2");
        motorLeft = hardwareMap.dcMotor.get("m1");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // get a reference to our GyroSensor object.
        sensorGyro = hardwareMap.gyroSensor.get("gyro");

        // calibrate the gyro.
        sensorGyro.calibrate();

        // make sure the gyro is calibrated.
        while (sensorGyro.isCalibrating())  {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sensorGyro.resetZAxisIntegrator();

        // Setup the behavior model
        gd = new GlobalData();
        be = new Behaviors_JsGyro_IRFollow(gd);

        //DbgLog.msg("Init Mode");
    }

    // Run periodically when sensors are read
    @Override
    public void loop() {
        // throttle: left_stick_y ranges from -1 to 1, where -1 is full up, and
        // 1 is full down
        // direction: left_stick_x ranges from -1 to 1, where -1 is full left
        // and 1 is full right
        gd.throttleJs = gamepad1.left_stick_y;
        gd.directionJs = -gamepad1.left_stick_x;

        gd.headingDot = (float)sensorGyro.rawZ(); //An integer value of 1 is 1 deg/sec of rotation
        gd.heading = (float)sensorGyro.getHeading(); //An integer value of 1 is 1 degrees of rotation

        gd.buttonX = gamepad1.x;
        gd.buttonY = gamepad1.y;
        gd.dpadRt = gamepad1.dpad_right;
        gd.dpadLt = gamepad1.dpad_left;

        // IR Seeker
        gd.irAngle=irSeeker.getAngle();
        gd.irStrength=irSeeker.getStrength();
        gd.irDetected=irSeeker.signalDetected();

        // Pass time to the behaviors
        gd.deltaTime = (float)time - gd.time;
        gd.time=(float)time;

        // Run the state machine
        be.onLoop();

        // write the values to the motors
        motorRight.setPower(gd.rightCmd);
        motorLeft.setPower(gd.leftCmd);
        //motorLeft.setPower(0.3);

        telemetry.addData("Behavior", be.getActiveBehavior().getName());
        //telemetry.addData("Time", "elapsed time: " + Double.toString(this.time));
        telemetry.addData("Throttle", Float.toString(gd.throttleJs));
        telemetry.addData("rightLeftPower", Float.toString(gd.rightCmd+gd.leftCmd));
        //telemetry.addData("rightLeftPower1", Float.toString(gd.rightCmd1+gd.leftCmd1));
        telemetry.addData("IR Strength", Double.toString(gd.irStrength));
        telemetry.addData("IR Angle", Double.toString(gd.irAngle));
        telemetry.addData("IR Detected", Boolean.toString(gd.irDetected));
        telemetry.addData("HeadingDot", Float.toString(gd.headingDot));
        //telemetry.addData("Debug Float 1", Float.toString(gd.debugFloat1));
        //telemetry.addData("Debug Float 2", Float.toString(gd.debugFloat2));

        //DbgLog.msg("Run Mode");
    }
}
