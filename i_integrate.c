// Story: As a software designer, I want to integrate a signal.
// Usage:
//   // In global space
//   #define FOREGROUND_MS 50 //I change this to 50 when I control the robot. 1000 is good for testing.
//   #define DT (float)FOREGROUND_MS/1000
//   float inZ1=0;   // A global variable for the history in the integrator (Need a uniqe var for each integrator)
//   // Anywhere in code
//   out=Integrate(in, 2, -2, DT, inZ1); // 2 is max, -2 is min

int Integrate(int in, float min, float max, float dt, float &inZ1){

  inZ1=inZ1+(float)in * dt;

	if (inZ1>max) inZ1=max;
	if (inZ1<min) inZ1=min;
	return(inZ1);
}
//#define TEST_INTEGRATE
#ifdef TEST_INTEGRATE //-------------------------------------------------
// Unit Test
// [] in = 1; Integrate increases by dt each second;
// [] in = -1; Integrate decreases by dt each second;
// [] Integrate limited to max
// [] Integrate limited to min
#include "i_debug.c"

int in,out;
int iFrameCnt=0;
int timeLeft=0; // A global variable that sticks around each frame
float inZ1=0;   // A global variable for the history in the integrator

// ------------------------Foreground Task -----------------------------//
// Run every second
#define FOREGROUND_MS 50 //I change this to 50 when I control the robot. 1000 is good for testing.
#define DT (float)FOREGROUND_MS/1000
task main(){

  Debug2Stream();

  while(true){
		ClearTimer(T1);
		hogCPU(); //Prevent other tasks from running when this one is.

  	if (iFrameCnt<25)       in=2;
  	else if (iFrameCnt<75)  in=-2;
    else break; // All done with the test

    out=Integrate(in, 2, -2, DT, inZ1);

  	DebugInt("In",in);
  	DebugInt("Out",out);
  	DebugFloat("inZ1",inZ1);
  	DebugFloat("dt",DT);

  	DebugPrint();

    iFrameCnt++;
	  timeLeft=FOREGROUND_MS-time1[T1]; // Calculate the time used in the foreground
	  releaseCPU(); // Let other tasks run now.
	  wait1Msec(timeLeft);// The time other tasks have to run before foreground takes control.
	}
	wait1Msec(3000);
}
#endif
