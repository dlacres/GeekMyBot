////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// checkBTLinkConnected
//
// Utility function to check whether Bluetooth link is set up. It should be manually set up before
// launching this program. [You can also set it up within an application program; there's a separate
// sample program for this.
//
////////////////////////////////////////////////////////////////////////////////////////////////////////
void checkBTLinkConnected()
{
	if (nBTCurrentStreamIndex >= 0)
	return; // An existing Bluetooth connection is present.
	//
	// Not connected. Audible notification and LCD error display
	//
	PlaySound(soundLowBuzz);
	PlaySound(soundLowBuzz);
	eraseDisplay();
	nxtDisplayCenteredTextLine(3, "BT not");
	nxtDisplayCenteredTextLine(4, "Connected");
	wait1Msec(3000);
	StopAllTasks();
}
bool checkBTLinkConnected2()
{

	if (nBTCurrentStreamIndex >= 0)
	  return(true);  // An existing Bluetooth connection is present.

	nxtDisplayCenteredTextLine(3, "Attempting");
	nxtDisplayCenteredTextLine(4, "To Connect");

	string pinnumber = "1234";

	setDefaultPIN(pinnumber);
//		setSessionPIN(pinnumber);
  bBTSkipPswdPrompt = true;

	// connect to GPS, here...
	btConnect(1, "PETER-PC");

	while (bBTBusy)
	{
		wait1Msec(1);
	}

	if (nBTCurrentStreamIndex >= 0)
	  return(true);  // An existing Bluetooth connection is present.

	nxtDisplayCenteredTextLine(3, "PETER-PC not");
	nxtDisplayCenteredTextLine(4, "Connected");
	wait1Msec(3000);
//	StopAllTasks();
	return(false);
}

task main()
{
//	const int kHexDigitsPerLine = 1;
	int nNumbBytesRead;
	ubyte BytesRead[1]; // Circular buffer of last bytes read.
	//
	// Test Ability to Turn Bluetooth On or Off
	//
	//checkBTLinkConnected2();
	//
	// Initialize Bluecore to "raw data" mode. This will be automatically reset when application terminates
	//
	setBluetoothRawDataMode();
	while (!bBTRawMode)
	{
	// Wait for Bluecore to enter raw data mode
	wait1Msec(1);
	}

	eraseDisplay();
	bNxtLCDStatusDisplay = true; // Enable top status line display
	while (true)
	{
		nNumbBytesRead = nxtReadRawBluetooth(&BytesRead[0], 1);
		if (nNumbBytesRead == 0){
		  wait1Msec(10);
		} else {
	    nxtDisplayString(3, "Rec [1] %d", BytesRead[0]);
	  }
		nNumbBytesRead = nxtReadRawBluetooth(&BytesRead[0], 2);
		if (nNumbBytesRead == 0){
		  wait1Msec(10);
		} else {
	    nxtDisplayString(3, "Rec [2] %d", BytesRead[0]);
	  }
		nNumbBytesRead = nxtReadRawBluetooth(&BytesRead[0], 3);
		if (nNumbBytesRead == 0){
		  wait1Msec(10);
		} else {
	    nxtDisplayString(3, "Rec [3] %d", BytesRead[0]);
	  }
	}
  return;
}
