package org.firstinspires.ftc.teamcode;

public class RisingEdge {
    public boolean z1;
    public boolean out;

    public RisingEdge(){
        this.z1=false;
    }

    public boolean step(boolean in){

        this.out=false;
        if (in==true && this.z1==false) this.out=true;

        this.z1=in;

        return(this.out);
    }
}
