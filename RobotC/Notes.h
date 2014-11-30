Robot C Notes:

----------Stories-------------
#include "joystick_example.c" - "As a driver I use the right joystick to control the robot."
#include "i_Ir.c"             - "As a programmer I want to autonomously line up with the IR beacon so that I can score points."
#include "i_deadzone.c"       - "As a programmer I want to ignore small noisy signals so that outputs (like motors) don't buzz or move."
#include "i_debug.c"          - "As a software debugger, I want to see a descriptive string and a variable so that I can understand what my software is doing."
#include "" - ""
#include "" - ""
#include "" - ""
#include "" - ""
#include "" - ""
#include "" - ""
#include "" - ""
#include "" - ""
#include "" - ""
#include "" - ""
#include "" - ""
#include "" - ""
#include "" - ""
#include "" - ""
#include "" - ""
#include "" - ""

----------IR Beacon-----------
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


---------JoyStick-------------
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


---------State Machine----------
#include "JoystickDriver.c"
#include "i_riseEdge.c"

bool btn_z1=false;   // Used with FallEdge

// My states
#define LIGHT_ON 1
#define LIGHT_OFF 2
int sm_cmd = LIGHT_OFF; //The default state
//Main, while loop, and wait needed
		switch (sm_cmd) {
		case LIGHT_OFF:                     //State
      // Do this when the light is off
		  nxtDisplayString(2, "Light Off");
			if (RiseEdge(joy1Btn(1),btn_z1)){ //Transition
				sm_cmd=LIGHT_ON; // Do this on a transition
			}

			break;
		case LIGHT_ON:                      //State
		  nxtDisplayString(2, "Light On");
			if (RiseEdge(joy1Btn(1),btn_z1)){ //Transition
				sm_cmd=LIGHT_OFF; // Do this on a transition
			}
	  }
