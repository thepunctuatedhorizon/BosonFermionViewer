package com.thepunctuatedhorizon.bosonfermionstate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.txtBox);

        //State state1 = new State(0,1,0,40, 10);

        //textView.setText(state1.toString());
    }
}
