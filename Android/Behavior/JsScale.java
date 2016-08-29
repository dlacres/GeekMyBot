package com.qualcomm.ftcrobotcontroller.geekmybot;

/**
 * Created by dlacres2 on 8/12/2016.
 */
public class JsScale {

    public JsScale() {
    }
    /*
 * This method scales the joystick input so for low joystick values, the
 * scaled value is less than linear.  This is to make it easier to drive
 * the robot more precisely at slower speeds.
 */
    public float calc(float in)  {
        float[] scaleArray = { 0.0f, 0.05f, 0.09f, 0.10f, 0.12f, 0.15f, 0.18f, 0.24f,
                0.30f, 0.36f, 0.43f, 0.50f, 0.60f, 0.72f, 0.85f, 1.00f, 1.00f };

        // get the corresponding index for the scaleInput array.
        int index = (int) (in * 16.0);

        // index should gd positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        float out = 0.0f;
        if (in < 0) {
            out = -scaleArray[index];
        } else {
            out = scaleArray[index];
        }

        // return scaled value.
        return out;
    }
}
