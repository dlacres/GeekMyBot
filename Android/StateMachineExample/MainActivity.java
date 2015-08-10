package com.dlacres2.helloworld;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    private int count = 0;
    private EditText editText1;
    private EditText editText2;
    private EditText editTexta;
    private EditText editTextb;
    private Button btnStep;
    //private RiseEdgeTrigger riseEdgeTrigger;
    private StateMachine sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.linear_layout);

        //this.riseEdgeTrigger = new RiseEdgeTrigger();
        this.sm = new StateMachine();


        this.btnStep = (Button) findViewById(R.id.stepId);
        this.editText1 = (EditText) findViewById(R.id.editText1ID);
        this.editText2 = (EditText) findViewById(R.id.editText2ID);
        this.editTexta = (EditText) findViewById(R.id.editTextaID);
        this.editTextb = (EditText) findViewById(R.id.editTextbID);
    }

    public void Step(View view) {
        String s1="None";

        s1=editText1.getText().toString();
        sm.onStep(s1);
        editTexta.setText(sm.getOut1());
        editTextb.setText(sm.getOut2());

    }

}