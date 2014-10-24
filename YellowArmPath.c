#pragma config(Hubs,  S1, HTServo,  none,     none,     none)
#pragma config(Sensor, S1,     ,               sensorI2CMuxController)
#pragma config(Servo,  srvo_S1_C1_1,    Arm,                  tServoStandard)
#pragma config(Servo,  srvo_S1_C1_2,    Finger,               tServoStandard)
#pragma config(Servo,  srvo_S1_C1_3,    Elbow,                tServoStandard)
#pragma config(Servo,  srvo_S1_C1_4,    Wrist,                tServoStandard)
#pragma config(Servo,  srvo_S1_C1_5,    Waist,                tServoStandard)
#pragma config(Servo,  srvo_S1_C1_6,    servo6,               tServoNone)
//*!!Code automatically generated by 'ROBOTC' configuration wizard  !!*//
// Testing
// [] All joystick commands in correct direction
// [] Test
#pragma DebuggerWindows("joystickSimple");
#include "JoystickDriver.c"
#include "i_debug.c"
#include "i_limit.c"
#include "i_ratelimit.c"
#include "i_integrate.c"
#include "i_deadzone.c"
#include "i_riseedge.c"

int timeLeft=0; // A global variable that sticks around each frame
// Finger, Wrist, Elbow, Arm, Waist, ValidPath
/*int path[][]={
		{160, 85, 129, 155, 139, 1},//0
		{160, 85, 129, 180, 139, 1},//1
		{160, 85, 129, 180, 209, 1},//2
		{160, 85, 108, 146, 209, 1},//3
		{160, 85, 123, 171, 209, 1},//4
		{160, 85, 123, 171, 139, 1},//5
		{160, 85, 129, 155, 139, 1},//6
		{160, 85, 129, 155, 139, 0},//7
		{160, 85, 129, 155, 139, 0},//8
		{160, 85, 129, 155, 139, 0},//9
		{160, 85, 129, 155, 139, 0}};//10*/
int path[][]={
		{160, 85, 129, 136, 175, 1},//0
		{160, 85, 129, 180, 175, 1},//1
		{160, 85, 129, 180, 139, 1},//2
		{160, 85, 108, 146, 139, 1},//3
		{160, 85, 123, 171, 139, 1},//4
		{160, 85, 123, 171, 175, 1},//5
		{160, 85, 129, 136, 175, 1},//6
		{160, 85, 129, 136, 175, 0},//7
		{160, 85, 129, 136, 175, 0},//8
		{160, 85, 129, 136, 175, 0},//9
		{160, 85, 129, 136, 175, 0}};//10
#define PATH_IDX_MAX 10
int pathIdx=0;

#define FINGER path[pathIdx][0]
#define WRIST path[pathIdx][1]
#define ELBOW path[pathIdx][2]
#define ARM path[pathIdx][3]
#define WAIST path[pathIdx][4]
#define VALID_PATH path[pathIdx][5]

float fingerZ1=FINGER; // Finger
float wristZ1=WRIST; // Wrist
float elbowZ1=ELBOW; // Elbow
float armZ1=ARM; // Arm
float waistZ1=WAIST; // Waist
//-------------- Is the movement of the path complete? -------------//
bool PathMovementDone(){
  if (FINGER==fingerZ1 && WRIST==wristZ1 && ELBOW==elbowZ1 && ARM==armZ1 && WAIST==waistZ1)
    return(true);
  return(false);
}
//-------------- Change the path index (inc/dec)-------------//
bool ChangePathIdx(int chg){ // Return true if the index was changed.
  pathIdx+=chg;
  if (pathIdx>=PATH_IDX_MAX) {
    pathIdx=PATH_IDX_MAX;
    return(false);
  }
  if (pathIdx<=0) {
    pathIdx=0;
    return(false);
  }
  return(true);
}
bool IncPathIdxVldPth(){
  pathIdx+=1;
  pathIdx=Limit(pathIdx,0,PATH_IDX_MAX);
  if (VALID_PATH==0) { //If the path is not valid, do not let the path be incremented
    pathIdx+=(-1);
    return(false);
  }
  return(true);
}
int fingerRate = 0;
int wristRate = 0;
int elbowRate = 0;
int armRate = 0;
int waistRate = 0;

// Run every second
#define FOREGROUND_MS 50 //I change this to 50 when I control the robot. 1000 is good for testing.
#define DT (float)FOREGROUND_MS/1000
#define SVO_RATE_LIMIT_GAIN 2000/FOREGROUND_MS // Change in servo position for 2 second total move time

//------------------------Calculate change rate for servo motor playback------------------//
void CalculateChangeRate(){
  fingerRate=abs(path[pathIdx][0]-fingerZ1)/SVO_RATE_LIMIT_GAIN;
  wristRate=abs(path[pathIdx][1]-wristZ1)/SVO_RATE_LIMIT_GAIN;
  elbowRate=abs(path[pathIdx][2]-elbowZ1)/SVO_RATE_LIMIT_GAIN;
  armRate=abs(path[pathIdx][3]-armZ1)/SVO_RATE_LIMIT_GAIN;
  waistRate=abs(path[pathIdx][4]-waistZ1)/SVO_RATE_LIMIT_GAIN;
}
void LimitChangeRate(){
  fingerRate=Limit(fingerRate,1,7);
  wristRate=Limit(wristRate,1,7);
  elbowRate=Limit(elbowRate,1,7);
  armRate=Limit(armRate,1,7);
  waistRate=Limit(waistRate,1,7);
}
//------------------------Count Up (like wait)---------------------//
bool CountUp(int cnt,int &cntZ1){
  if (cntZ1++ >= cnt) {
    cntZ1=0;
    return(true);
  }
  return(false);
}
// ------------------------Test the path---------------------------//
bool btn1Z1=false;
bool btn2Z1=false;
bool btn3Z1=false;
bool btn4Z1=false;
bool btn5Z1=false;
#define TEST_PATH 5
void TestPath(){
  int temp;

  if (RiseEdge(joystick.joy1_TopHat==6,btn1Z1)){
    CalculateChangeRate();
  }

  if (RiseEdge(joystick.joy1_TopHat==0,btn2Z1)){
    //ChangePathIdx(1);
    if (!IncPathIdxVldPth()) PlayTone(2000, 500);
    CalculateChangeRate();
  }
	if (RiseEdge(joystick.joy1_TopHat==4,btn3Z1)){
	  ChangePathIdx(-1);
    CalculateChangeRate();
	}
	LimitChangeRate();

	temp=(int)RateLimitFloat((float)FINGER,(float)fingerRate,fingerZ1);
	DebugInt("Fgr",temp);
	servo[Finger]=temp;

	temp=(int)RateLimitFloat((float)WRIST,(float)wristRate,wristZ1);
	DebugInt("Wrst",temp);
	servo[Wrist]=temp;

	temp=(int)RateLimitFloat((float)ELBOW,(float)elbowRate,elbowZ1);
	DebugInt("Elb",temp);
	servo[Elbow]=temp;

	temp=(int)RateLimitFloat((float)ARM,(float)armRate,armZ1);
	DebugInt("Arm",temp);
	servo[Arm]=temp;

	temp=(int)RateLimitFloat((float)WAIST,(float)waistRate,waistZ1);
	DebugInt("Waist",temp);
	servo[Waist]=temp;
}
//------------------------Remember Path Position---------------------//
#define REMEMBER_PATH 2
void RememberPath(){
  FINGER=fingerZ1;
  WRIST=wristZ1;
  ELBOW=elbowZ1;
  ARM=armZ1;
  WAIST=waistZ1;
  VALID_PATH=1;
  if (ChangePathIdx(1)){
    VALID_PATH=0; // Set the next position to invalid.
  }
}
// -----------------------Play Joystick -----------------------------//
#define PLAY_JOYSTICK 3
short sm_cmd=PLAY_JOYSTICK;

void PlayJoystick(){
    int tmp;

    // Read in the joystick commands
    getJoystickSettings(joystick);

    tmp=0;
    if (joy1Btn(5)) tmp=30;
    if (joy1Btn(6)) tmp=-30;
//	  DebugInt("FgrBtn",tmp);
    tmp = Integrate(tmp, 110, 180, DT, fingerZ1);
	  DebugInt("Fgr",tmp);
    servo[Finger]=tmp;

    tmp = Integrate(DeadZone(joystick.joy1_y1, 3), 0, 250, DT, wristZ1);
	  DebugInt("Wrst",tmp);
    servo[Wrist]=tmp;


    tmp = Integrate(DeadZone(joystick.joy1_x1/2, 3), 50, 175, DT, elbowZ1);
	  DebugInt("Elb",tmp);
    servo[Elbow]=tmp;

    tmp = Integrate(DeadZone(-joystick.joy1_y2/2, 3), 110, 180, DT, armZ1);
	  DebugInt("Arm",tmp);
    servo[Arm]=tmp;

    tmp = Integrate(DeadZone(-joystick.joy1_x2/2, 3), 0, 255, DT, waistZ1);
	  DebugInt("Waist",tmp);
    servo[Waist]=tmp;
}
//------------------------Play Path -------------------------------//
#define PLAY_PATH 1
int countZ1=0;
bool PlayPath(){
    int temp;

  if (PathMovementDone()){
    if (CountUp(5,countZ1)){
      if(!IncPathIdxVldPth()) return(false); // Go back to the joystick
      CalculateChangeRate();
    }
  } else countZ1=0;
	LimitChangeRate();

	temp=(int)RateLimitFloat((float)FINGER,(float)fingerRate,fingerZ1);
	DebugInt("Fgr",temp);
	servo[Finger]=temp;

	temp=(int)RateLimitFloat((float)WRIST,(float)wristRate,wristZ1);
	DebugInt("Wrst",temp);
	servo[Wrist]=temp;

	temp=(int)RateLimitFloat((float)ELBOW,(float)elbowRate,elbowZ1);
	DebugInt("Elb",temp);
	servo[Elbow]=temp;

	temp=(int)RateLimitFloat((float)ARM,(float)armRate,armZ1);
	DebugInt("Arm",temp);
	servo[Arm]=temp;

	temp=(int)RateLimitFloat((float)WAIST,(float)waistRate,waistZ1);
	DebugInt("Waist",temp);
	servo[Waist]=temp;

  return(true); // Continue on the path
}
// ------------------------ Erase Path --------------------------//
#define ERASE_PATH 4
void ErasePath(){
  pathIdx=0;
}
// ------------------------Foreground Task -----------------------------//
task main(){

  Debug2Stream();

	while(true){
		ClearTimer(T1);
		hogCPU(); //Prevent other tasks from running when this one is.

		DebugInt("state",sm_cmd);
		DebugInt("idx",pathIdx);

    switch (sm_cmd) {
		case PLAY_JOYSTICK:                     //State

		  PlayJoystick();
			if (joy1Btn(4))sm_cmd=ERASE_PATH;
			if (RiseEdge(joy1Btn(2),btn4Z1)){ // Transition to play path
			  ErasePath();
			  sm_cmd=PLAY_PATH;
			}
			if (RiseEdge(joy1Btn(3),btn5Z1))sm_cmd=REMEMBER_PATH;  // Transition to remember path
			if (joystick.joy1_TopHat==6)sm_cmd=TEST_PATH; // Transition to test path
			break;
		case PLAY_PATH:                     //State
		  if (!PlayPath()) sm_cmd=PLAY_JOYSTICK;
			break;
		case REMEMBER_PATH:                     //State
		  RememberPath();
		  sm_cmd=PLAY_JOYSTICK;
			break;
		case ERASE_PATH:                     //State
		  ErasePath();
		  if (!joy1Btn(4))sm_cmd=PLAY_JOYSTICK;
			break;
		case TEST_PATH:                     //State
		  TestPath();
		  if (joystick.joy1_TopHat==2) sm_cmd=PLAY_JOYSTICK;
			break;
    } //

    DebugPrint();
	  timeLeft=FOREGROUND_MS-time1[T1]; // Calculate the time used in the foreground
	  releaseCPU(); // Let other tasks run now.
	  wait1Msec(timeLeft);// The time other tasks have to run before foreground takes control.

	}// While Loop
}// Foreground Task
