package com.example.dllempia.myfirstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private int count=0;
    private EditText editText1;
    private EditText editText2;
    private Button btnStep;
    private RiseEdgeTrigger riseEdgeTrigger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.riseEdgeTrigger = new RiseEdgeTrigger();

        this.btnStep = (Button) findViewById(R.id.stepId);
        this.editText1 = (EditText) findViewById(R.id.editText1ID);
        this.editText2 = (EditText) findViewById(R.id.editText2ID);
    }

    public void Step(View view){

        Boolean in = Boolean.parseBoolean(editText1.getText().toString());

        Boolean out = riseEdgeTrigger.step(in);

        editText2.setText(String.valueOf(out));
    }
}
