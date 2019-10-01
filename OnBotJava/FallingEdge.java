package org.firstinspires.ftc.teamcode;

public class FallingEdge {
    public boolean z1;
    public boolean out;

    public FallingEdge(){
        this.z1=false;
    }

    public boolean step(boolean in){

        this.out=false;
        if (in==false && this.z1==true) this.out=true;

        this.z1=in;

        return(this.out);
    }
}

