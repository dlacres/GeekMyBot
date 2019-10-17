package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.lang.Math;

@TeleOp

public class Test_Drive extends OpMode {
    BalanceBot robot = new BalanceBot();
    Periodic periodic = new Periodic(50.0); // 50 ms or 20 hertz
    
    double js_table[][]={
        {0.0,0.0},
        {0.1,0.2},
        {0.2,0.4},
        {0.4,0.6},
        {0.7,0.8},
        {1.0,1.0}};
    TableLookup js_shaper = new TableLookup(js_table);
    
    @Override
    public void init() { // Run between stop and init
        robot.init(hardwareMap);
        telemetry.addData("Say", "Hello Driver");    //
    }

    @Override
    public void init_loop() {
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();
        
        robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                          robot.leftDrive.getCurrentPosition(),
                          robot.rightDrive.getCurrentPosition());
        telemetry.update();

    }

    @Override
    public void start() { // Run one time between init and run
    }

    @Override
    public void loop() {
        double forward,turn;
        
        forward = gamepad1.right_stick_y;
        turn=js_shaper.calc(gamepad1.right_stick_x);

        robot.leftDrive.setPower(forward-turn);
        robot.rightDrive.setPower(forward+turn);
        
        telemetry.addData("Position ","%7d :%7d",
                      robot.leftDrive.getCurrentPosition(),
                      robot.rightDrive.getCurrentPosition());
        telemetry.addData("Joystick ","%1.3f :%1.3f", forward, turn);
        telemetry.update();
        periodic.Wait();
    }
    
    @Override
    public void stop() {
    }
}

