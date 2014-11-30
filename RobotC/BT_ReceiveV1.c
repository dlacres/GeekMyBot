//short nMsgXmit = 0;
//short nLastCharReceived = 0;
//const int kPacketSize = 8; // Number of hex digits that can be displayed on one line
//
// Function and task prototypes
//
void checkBTLinkConnected();
////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Main Task
//
////////////////////////////////////////////////////////////////////////////////////////////////////////
task main()
{
const int kSendSize = 1;
static ubyte BytesToSend[kSendSize];
int nNumbBytesRead;
const int kHexDigitsPerLine = 1;
int nIndex = 0;
ubyte BytesRead[kHexDigitsPerLine]; // Circular buffer of last bytes read.
//
// Test Ability to Turn Bluetooth On or Off
//
checkBTLinkConnected();
//
// Initialize Bluecore to "raw data" mode. This will be automatically reset when application terminates
//
setBluetoothRawDataMode();
while (!bBTRawMode)
{
// Wait for Bluecore to enter raw data mode
wait1Msec(1);
}
//
// Start separate tasks to read and write raw data over BT. The link operates in full-duplex mode.
//
eraseDisplay();
bNxtLCDStatusDisplay = true; // Enable top status line display
//StartTask(sendRawData);
//StartTask(readRawData);
while (true)
{
//
// Display progress results on the NXT LCD screen.
//
//nxtDisplayBigStringAt(0, 31, "Xmit Rcv");
//nxtDisplayBigStringAt(0, 15, " %02X %02X", nMsgXmit, nLastCharReceived & 0x00FF);
//
// Loop continuously, reading one byte at a time. The interface will support larger reads.
//
nNumbBytesRead = nxtReadRawBluetooth(&BytesRead[nIndex], 1);
if (nNumbBytesRead == 0)
{
wait1Msec(10);
continue;
}
else if (nNumbBytesRead == 1 && BytesRead[0] == 'S')
{
nxtDisplayString(1, "Received Start");
}
else if (nNumbBytesRead == 1 && BytesRead[0] == 'w')
{
nxtDisplayString(4, "Forward Signal");
motor[motorA] = 25;
motor[motorB] = 25;
}
else if (nNumbBytesRead == 1 && BytesRead[0] == 's')
{
nxtDisplayString(4, "Backward Signal");
motor[motorA] = -25;
motor[motorB] = -25;
}
else if (nNumbBytesRead == 1 && BytesRead[0] == 'd')
{
nxtDisplayString(4, "Right Turn Signal");
motor[motorA] = 25;
motor[motorB] = -25;
}
else if (nNumbBytesRead == 1 && BytesRead[0] == 'a')
{
nxtDisplayString(4, "Left Turn Signal");
motor[motorA] = -25;
motor[motorB] = 25;
}
else if (nNumbBytesRead == 1 && BytesRead[0] == 'z')
{
nxtDisplayString(4, "ALL STOP Signal");
motor[motorA] = 0;
motor[motorB] = 0;
}
memset(BytesToSend, BytesRead[nIndex], sizeof(BytesToSend));
nxtWriteRawBluetooth(BytesToSend, sizeof(BytesToSend));
}
return;
}
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
