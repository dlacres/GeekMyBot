// Story: As a software designer I want to find the falling edge of a signal so that I can trigger an event
// Usage: Make sure globalBoolEq0 is initialized equal to false
// outBool = RiseEdge( inBool, globalBoolEqF );
bool FallEdge(bool in, bool &z1)
{
	bool out;

	out=false;
  if ((in==false) && (z1==true)) out=true;
  z1=in;

	return(out);
}
#define TEST_FALL_EDGE
#ifdef TEST_FALL_EDGE //-------------------------------------------------
// Add this code to a while loop in main
// Unit Test
// [x] Fall Edge of input results in a true output for 1 frame
// [x] Rise Edge of input results in no change to the output
// [x] True input for more than one frame results in a false output
// [x] False input results in a false output
#include "i_debug.c"

task main(){
  bool z=false;
  bool out,in=false;

  for (int i=0; i<10; i++){

    if (i<2) in=false;
    else if (i<4) in=true;
    else if (i<6) in=false;
    else if (i<8) in=true;

    out=FallEdge(in, z); //Code to test

    DebugBool("In",in);
    DebugBool("Out",out);
    DebugPrint();
  }
}
#endif
