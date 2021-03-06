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
//	Gyro is used to smooth the turn
//
// As autonomous I use the IR sensor to turn to face the beacon.
//
//   Use the gyro in NXT3, LtMotor in Motor2, RtMotor in Motor1, ir Sensor in NXT 2

#include "JoystickDriver.c"
#include "i_debug.c"
#include "i_deadzone.c"
#include "i_lookup.c"
#include "i_riseedge.c"
#include "i_limit.c"
#include "i_directionControl.c"

#pragma DebuggerWindows("joystickSimple");
int count=0;
int timeLeft=0; // A global variable that sticks around each frame
int gyroBias=0;
int irVal=0;
int irSvoVal=128;
#define INC 0
#define DEC 1
short sm_cmd=INC;

#define JS_CONTROL 0
#define FOLLOW_IR 1
short sm_ir=JS_CONTROL;

bool btn1Z1=false;
bool btn2Z1=true;

// ------------------------Foreground Task -----------------------------//
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
		int xMotorCmd;
		bool jsB1;

		// ------- Joystick Control --------//
		getJoystickSettings(joystick);

		jstickX = Lookup1(joystick.joy1_x2) - DeadZone((SensorValue[gyroSensor]-gyroBias)/2,5);
		jstickY = Lookup1(joystick.joy1_y2);

		// --------IR Sensor -------//
		irVal=SensorValue[irSensor];

		DebugInt("IR",irVal);

		// ------- Move IR Servo -----//
		switch (sm_cmd) {
		case INC:
			if (irSvoVal>228) sm_cmd=DEC;
			irSvoVal+=5;
		break;
		case DEC:
			if (irSvoVal<28) sm_cmd=INC;
			irSvoVal-=5;
		break;
		}
		servo[irSvo]=irSvoVal;

    switch (sm_ir) {
		case JS_CONTROL:                     //State

			// ------- If orange js button is hit, use the joystick to drive
			jsB1=(bool)joy1Btn(1);
			if (RiseEdge(jsB1,btn1Z1)) sm_ir=FOLLOW_IR; // Orange button

			xMotorCmd = jstickX;

			nMotorEncoder[ltMotor]=0;
			nMotorEncoder[rtMotor]=0;
			break;
		case FOLLOW_IR:                     //State

			// ------- If orange js button is hit, turn to face the beacon. JS y still works
			jsB1=(bool)joy1Btn(1);
			if (RiseEdge(jsB1,btn2Z1)) sm_ir=JS_CONTROL; // Orange button


			int mrtLtClk = nMotorEncoder[ltMotor];
			int mtrRtClk = nMotorEncoder[rtMotor];

			int rbtDirClk = mrtLtClk - mtrRtClk;
			DebugInt(" rbtDirC",rbtDirClk);

			int dirCmdClk=0;

			xMotorCmd = DirSmooth(dirCmdClk, rbtDirClk);
			break;
		}

		// ------- Control the drive motors ----------//
		motor[ltMotor]=(jstickY + xMotorCmd);
		motor[rtMotor]=(jstickY - xMotorCmd);

	  //--------------------------END FOREGROUND--------------------------------//
		DebugPrint();

		timeLeft=FOREGROUND_MS-time1[T1]; // Calculate the time used in the foreground
	  releaseCPU(); // Let other tasks run now.
	  wait1Msec(timeLeft);// The time other tasks have to run before foreground takes control.
	}// While Loop
}// Foreground Main Task*/
