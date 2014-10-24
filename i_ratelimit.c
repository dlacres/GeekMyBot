// Story: As a software designer, I want to limit the amount a command or signal can change every periodic frame.
// Usage: Works great to shape input commands for the robot. Helps prevent wheels from slipping if they start to quickly.

// *z1 must be a global variable
// rate is the maximum amount the signal can change every frame.
// inLimInt rateLimit( inInt, rateInt, globalIntEq0);
int RateLimit(int in, int rate, int &z1)
{
	if (in-z1 >= rate) z1 = z1 + rate;
	else if (z1-in >= rate) z1 = z1 - rate;
	else z1=in;
	return(z1);
}
float RateLimitFloat(float in, float rate, float &z1)
{
	if (in-z1 >= rate) z1 = z1 + rate;
	else if (z1-in >= rate) z1 = z1 - rate;
	else z1=in;
	return(z1);
}
/* Add this code to a while loop in main
// Unit Test
// [x] Positive input
// [x] Negative input
// [] Skip number is right
#include "i_debug.c"

task main(){
	int iFrameCnt=0;
	int z=2;
	int i=0;
	int out,in=10;

  Debug2Stream();

  while(i++<10){

  	out=RateLimit(in, 1, z);
  	DebugInt("In",in);
  	DebugInt("Out",out);
  	DebugInt("Z",z);

		wait1Msec(100);
		DebugPrint();


		//------------------Stop i_debug.c output----------------------//
	}
}
//*/
