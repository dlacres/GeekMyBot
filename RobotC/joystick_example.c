#pragma config(Hubs,  S1, MatrxRbtcs, none,     none,     none)
#pragma config(Sensor, S2,     irSensor,       sensorHiTechnicIRSeeker1200)
#pragma config(Sensor, S3,     gyroSensor,     sensorI2CHiTechnicGyro)
#pragma config(Motor,  motorA,           ,             tmotorNXT, openLoop)
#pragma config(Motor,  motorB,           ,             tmotorNXT, openLoop)
#pragma config(Motor,  motorC,           ,             tmotorNXT, openLoop)
#pragma config(Motor,  mtr_Matrix_S1_1, rtMotor,       tmotorTetrix, PIDControl, reversed, driveRight, encoder)
#pragma config(Motor,  mtr_Matrix_S1_2, ltMotor,       tmotorTetrix, PIDControl, driveLeft, encoder)
#pragma config(Motor,  mtr_Matrix_S1_3, motorF,        tmotorMatrix, openLoop)
#pragma config(Motor,  mtr_Matrix_S1_4, motorG,        tmotorMatrix, openLoop)
#pragma config(Servo,  srvo_Matrix_S1_1, servo1,               tServoNone)
#pragma config(Servo,  srvo_Matrix_S1_2, servo2,               tServoNone)
#pragma config(Servo,  srvo_Matrix_S1_3, irSvo,                tServoStandard)
#pragma config(Servo,  srvo_Matrix_S1_4, servo4,               tServoNone)
//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//
//
// As a driver I use the right joystick to control the robot.
//
// ROBOT
//  Use the gyro in NXT3, LtMotor in Motor2, RtMotor in Motor1
//
//-----------------Joystick Commands--------------------
//  joy1Btn(5) (Top Back Left)	(6) (Top Back Right)
//         (6) (Bottom)		      (8) (Bottom)
//
//                           0      (9)  (10)    (4)
//  joystick.joy1_TopHat== 6   2							(1)   (3)
//                           4                   (2)
//
//        joystick.joy1_y1 / x1  ...  y2 / x2
//------------------------------------------------------
#include "JoystickDriver.c"
#include "i_debug.c"
#include "i_deadzone.c"
#include "i_lookup.c"

//#pragma DebuggerWindows("joystickSimple");
#pragma DebuggerWindows("joystickGame");
int count=0;
int timeLeft=0; // A global variable that sticks around each frame
int gyroBias=0;
int jsB1;

// ==================Foreground Task ==========================//
// Run every second
#define FOREGROUND_MS 50
task main(){
  //---------------------------INIT-----------------------------------//
	Debug2Stream();
	for (int i=0; i<5; i++){
		gyroBias = SensorValue[gyroSensor]+gyroBias;
		wait1Msec(50);
	}
	gyroBias=gyroBias/5;
//---------------------------END INIT-------------------------------//
	while(true){
		clearTimer(T1);
		hogCPU(); //Prevent other tasks from running when this one is.
		//writeDebugStreamLine("Foreground\n"); //Let me know when the foreground runs
	  count=count+1; // Count the number of times the foreground runs.

	  //--------------------------FOREGROUND------------------------------------//
		int jstickX = 0;
		int jstickY = 0;

		// ------- Joystick Control --------//
		getJoystickSettings(joystick);

		jstickX = Lookup1(joystick.joy1_x2) - DeadZone((SensorValue[gyroSensor]-gyroBias)/2,5);
		jstickY = Lookup1(joystick.joy1_y2);

		// ------- Control the drive motors ----------//
		motor[ltMotor]=(jstickY + jstickX);
		motor[rtMotor]=(jstickY - jstickX);

		jsB1=(bool)joy1Btn(1);
		//if (RiseEdge(jsB1,btn1Z1)) sm_ir=FOLLOW_IR; // Orange button


	  //--------------------------END FOREGROUND--------------------------------//
		DebugPrint();

		timeLeft=FOREGROUND_MS-time1[T1]; // Calculate the time used in the foreground
	  releaseCPU(); // Let other tasks run now.
	  wait1Msec(timeLeft);// The time other tasks have to run before foreground takes control.
	}// While Loop
}// Foreground Main Task*/
