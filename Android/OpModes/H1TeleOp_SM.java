package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.geekmybot.GlobalData;
import com.qualcomm.ftcrobotcontroller.geekmybot.JsScale;
import com.qualcomm.ftcrobotcontroller.geekmybot.Behaviors_Joystick;
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
public class H1TeleOp_SM extends OpMode {

    // Constructor
    JsScale jsScale;
    public H1TeleOp_SM(){
        jsScale=new JsScale();
    }

    // Run one time
    DcMotor motorRight;
    DcMotor motorLeft;
    GyroSensor sensorGyro;
    Behaviors_Joystick be;
    GlobalData gd;

    @Override
    public void init() {
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
        gd = new GlobalData();
        be = new Behaviors_Joystick(gd);

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

        gd.headingDot = ((float)sensorGyro.rawZ()/5000.0f); //An integer value of 1 is 1 deg/sec of rotation
        gd.heading = ((float)sensorGyro.getHeading()/360.0f); //An integer value of 1 is 1 degrees of rotation

        gd.buttonX = gamepad1.x;
        gd.buttonY = gamepad1.y;

        // Pass time to the behaviors
        gd.deltaTime = (float)time - gd.time;
        gd.time=(float)time;

        // Run the state machine
        be.onLoop();

        // write the values to the motors
        motorRight.setPower(gd.rightCmd);
        motorLeft.setPower(gd.leftCmd);

        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Behavior", be.getActiveBehavior().getName());
        //telemetry.addData("Time", "elapsed time: " + Double.toString(this.time));
        telemetry.addData("Throttle", Float.toString(gd.throttleJs));
        telemetry.addData("rightPower", Float.toString(gd.rightCmd));
        telemetry.addData("Heading", Float.toString(gd.heading));
        telemetry.addData("HeadingDot", Float.toString(gd.headingDot));

        //DbgLog.msg("Run Mode");
    }
}
