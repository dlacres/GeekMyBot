#pragma config(Hubs,  S1, HTServo,  none,     none,     none)
#pragma config(Sensor, S1,     ,               sensorI2CMuxController)
#pragma config(Servo,  srvo_S1_C1_1,    Arm,                  tServoStandard)
#pragma config(Servo,  srvo_S1_C1_2,    Finger,               tServoStandard)
#pragma config(Servo,  srvo_S1_C1_3,    Elbow,                tServoStandard)
#pragma config(Servo,  srvo_S1_C1_4,    Wrist,                tServoStandard)
#pragma config(Servo,  srvo_S1_C1_5,    servo5,               tServoNone)
#pragma config(Servo,  srvo_S1_C1_6,    servo6,               tServoNone)
//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//
// As an automator, I want to move the arm on a path that can be played back so I can automate tasks
//
#include "i_debug.c"
#include "i_deadZone.c"

//#pragma DebuggerWindows("joystickSimple");

int count=0;
int timeLeft=0; // A global variable that sticks around each frame
#define MAX_POINTS 20
#define MAX_NAMES 4
int path[MAX_POINTS][MAX_NAMES];


// ------------------------Foreground Task -----------------------------//
// Run every second
#define FOREGROUND_MS 50 //I change this to 50 when I control the robot. 1000 is good for testing.
task main(){

	Debug2NXT(); // Send the debug out to the NXT display only

	while(true){
		ClearTimer(T1);
		hogCPU(); //Prevent other tasks from running when this one is.

    DebugInt("Wrst",ServoValue[Wrist]);
    DebugInt("Arm",ServoValue[Arm]);
    DebugInt("Elb",ServoValue[Elbow]);
    DebugInt("Fgr",ServoValue[Finger]);

    DebugPrint();
	  timeLeft=FOREGROUND_MS-time1[T1]; // Calculate the time used in the foreground
	  releaseCPU(); // Let other tasks run now.
	  wait1Msec(timeLeft);// The time other tasks have to run before foreground takes control.
	 	//writeDebugStreamLine("Count=[%2i] Time Left = %i\n",count,timeLeft);
	}// While Loop
}// Foreground Task
