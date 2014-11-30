#pragma config(Hubs,  S1, MatrxRbtcs, none,     none,     none)
#pragma config(Sensor, S2,     irSensor,       sensorHiTechnicIRSeeker1200)
#pragma config(Motor,  mtr_Matrix_S1_1, motorD,        tmotorMatrix, openLoop)
#pragma config(Motor,  mtr_Matrix_S1_2, motorE,        tmotorMatrix, openLoop)
#pragma config(Motor,  mtr_Matrix_S1_3, motorF,        tmotorMatrix, openLoop)
#pragma config(Motor,  mtr_Matrix_S1_4, motorG,        tmotorMatrix, openLoop)
#pragma config(Servo,  srvo_Matrix_S1_1, servo1,               tServoNone)
#pragma config(Servo,  srvo_Matrix_S1_2, servo2,               tServoNone)
#pragma config(Servo,  srvo_Matrix_S1_3, irSvo,                tServoStandard)
#pragma config(Servo,  srvo_Matrix_S1_4, servo4,               tServoNone)
//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//
//
// As a programmer I want to autonomously line up with the IR beacon so that I can score points.
//
//======================================================//
// Usage:
// Region ----- Width in Degrees - Comments
// -------------------------------------------
// 1         - Sensor 1
// 2-----14  -   This is created when 2 sensors both see light
// 3-----54  - Sensor 2
// 4-----6   -   This is created when 2 sensors both see light
// 5-----67  - Sensor 3 This wide area is the front of the sensor
// 6-----6   -   This is created when 2 sensors both see light
// 7-----54  - Sensor 4
// 8-----3   -   This is created when 2 sensors both see light
// 9         - Sensor 5

//  Notes: Region to the left (top view) 1,2,3,4 Forward 5, region to right 6,7,8,9
//    Use transtions between regions to set a steering command.
#include "include/hitechnic-irseeker-v2.h"

// Create struct to hold sensor data
tHTIRS2 irSeeker;
//---------------------- IrInit --------------------------//
void IrInit(){

  // Look for the 1200 hz IR AC signal
  irSeeker.mode = DSP_1200;

  // Initialise and configure struct and port
  initSensor(&irSeeker, S2);
}
//---------------------- Ir ------------------------------//
#define CMD_GAIN 1
int Ir(){
	int irCmd=0;
	readSensor(&irSeeker);

	if (irSeeker.acDirection==0) irCmd=0;
	else if (irSeeker.acDirection<3) irCmd=3*CMD_GAIN;
	else if (irSeeker.acDirection<4) irCmd=2*CMD_GAIN;
	else if (irSeeker.acDirection>5) irCmd=-3*CMD_GAIN;
	else if (irSeeker.acDirection>4) irCmd=-2*CMD_GAIN;
	else if (irSeeker.acValues[1]>irSeeker.acValues[2]) irCmd=1*CMD_GAIN;
	else irCmd=-1*CMD_GAIN;

	return(irCmd);
}
// ==================UNIT TEST==========================//
#ifndef NO_UNIT_TEST
#include "i_debug.c"
#include "i_integrate.c"

int timeLeft=0; // A global variable used to adjust the time it takes to complet the while loop
int i=0;
int inZ1=128;
bool runTest=true;
#define INC 0
#define DEC 1
#define FOREGROUND_MS 50 //The while loop takes 50 MS to run. This means the software runs 20 times per second
task main(){
	// Initialize variables here //
	int svoCmd=0;
	int svoPos;
	int irSensorCur=0;
	int irSensorOld=0;

	IrInit();

	//Debug2File(); //Send the debug information to the file debug.txt
	//Debug2NXT();  //Send the debug information to the NXT screen
	Debug2Stream(); //Send the debug information to the PC Screen

	// End of initialize //
	while(runTest){
		clearTimer(T1);
		hogCPU(); //Prevent other tasks from running when this one is.
		// ------------- Put Unit Test code here -------------------//
		// xxxxxxx [] IR Off - Servo should sit still
		// xxxxxxx [] IR On and left of center - Servo should move to center
		// xxxxxxx [] IR On and right of center - Servo should move to center

		// USAGE NOTES:
		//   The units for a are encoder clicks
		//   Set #define NO_UNIT_TEST

		svoCmd=Ir();
		servo[irSvo]=svoPos=IntegrateInt(svoCmd, 0, 255, inZ1);

		irSensorOld=irSensorCur;
		irSensorCur=irSeeker.acDirection;
		if (irSensorCur!=irSensorOld || irSensorCur==4){
			DebugInt("SvoCmd",svoCmd);
			DebugInt("SvoPos",svoPos);
			DebugInt("IrDir",irSeeker.acDirection);
			DebugPrint();
		}

		i+=1; // Increment the frame counter for unit test
		// ------------- Unit code test is done here ---------------//
		timeLeft=FOREGROUND_MS-time1[T1]; // Calculate the time used in the foreground
		releaseCPU(); // Let other tasks run now.
		wait1Msec(timeLeft);// The time other tasks have to run before foreground takes control.
	}// While Loop
}// Main Task
#endif
