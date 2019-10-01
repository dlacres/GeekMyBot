import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.android.AndroidGyroscope;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/* Tested and working */

@TeleOp(name = "Gyro_Java", group = "")
public class Gyro extends LinearOpMode {

  private AndroidGyroscope androidGyroscope;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    androidGyroscope = new AndroidGyroscope();

    // Put initialization blocks here.
    if (androidGyroscope.isAvailable()) {
      telemetry.addData("Status", "GyroAvailable");
    }
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      androidGyroscope.startListening();
      androidGyroscope.setAngleUnit(AngleUnit.DEGREES);
      while (opModeIsActive()) {
        telemetry.addData("X Axis", androidGyroscope.getX());
        telemetry.update();
      }
    }
  }
}
