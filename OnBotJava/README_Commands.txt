Joystick - There is no need to define gamepad1.
  buttons
    bool button;
    button = gamepad1.a (b,x,y,left_bumper,right_bumper)
  stick
    double out;
    out = gamepad1.right_stick_x (right_stick_y,left_stick_x,left_stick_y)// Scale is -1.0 to 1.0

Motors
  init()
    robot.init(hardwareMap);
  init_loop()
    long encoder_count;
    robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // Enable PID controller
  loop()
    robot.leftDrive.setPower(1.0) // Scale is -1.0 to 1.0
    encoder_count=robot.leftDrive.getCurrentPosition()

telemetry
  loop()
    telemetry.addData("Description ","%1.3f :%1.3f", forward, turn); //%1.3 for double, %7d for integer, %2d for bool

