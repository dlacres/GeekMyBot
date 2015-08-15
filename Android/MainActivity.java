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
    private DataServer ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.linear_layout);

        //this.riseEdgeTrigger = new RiseEdgeTrigger();
        sm = new StateMachine();
        ds = sm.getDs();


        btnStep = (Button) findViewById(R.id.stepId);
        editText1 = (EditText) findViewById(R.id.editText1ID);
        editText2 = (EditText) findViewById(R.id.editText2ID);
        editTexta = (EditText) findViewById(R.id.editTextaID);
        editTextb = (EditText) findViewById(R.id.editTextbID);
    }

    public void Step(View view) {
        String s1="None";

        s1=editText1.getText().toString();
        sm.onStep(s1);
        editTexta.setText(ds.getOut1());
        editTextb.setText(ds.getOut2());

    }

}