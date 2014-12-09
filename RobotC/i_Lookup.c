// Change the shape of the joystick signal to give better control for small signals
// Between 0 and 80 the slope is 1/2.
// Between 80 and 115 the slope is 1.
// Between 115 and 128 the slope is 2.
int table_1[][]={
		{0, 0},
		{3, 0},
		{5, 3},
		{60, 40},
		{100, 80},
						};
#define x 0
#define y 1
int i,num,den;

int Lookup1(int in){
	int out = 0;
	int inAbs;
	int s=sizeof(table_1)/sizeof(table_1[0])-1;

	inAbs=abs(in);

	// Find the x region we are in
	for (i=0; i<s-1; ++i){
		if (table_1[i][x]<=inAbs && table_1[i+1][x]>inAbs)
			break;
	}
	num=table_1[i+1][y]-table_1[i][y];
	den=table_1[i+1][x]-table_1[i][x];
	out=(num*inAbs)/den + table_1[i][y] - (num*table_1[i][x])/den;
	//out = (int)((float)(table_1[i+1][y]-table_1[i][y])/((float)(table_1[i+1][x]-table_1[i][x])))*(float)in;

	//if (in<0) out=-out;
	return(out);
}
//----------Test Code------------//
#define TEST_DEBUG //-------------------------------------------------
#ifdef TEST_DEBUG

#pragma DebuggerWindows ("debugStream")// Bring up the debug stream window

int out;
task main(){
	writeDebugStreamLine("in,out,i,num,den");

	for (int in=-150;in<150;in++){
		out=Lookup1(in);
		writeDebugStreamLine("%3i,%3i,%3i,%3i,%3i",in,out,i,num,den);
		wait1Msec(10);
	}
}//*/
#endif
