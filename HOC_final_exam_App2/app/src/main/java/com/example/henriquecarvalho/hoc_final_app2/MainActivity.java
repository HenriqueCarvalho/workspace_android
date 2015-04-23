package com.example.henriquecarvalho.hoc_final_app2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    int counter;
    TextView counterOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = 0;
        counterOutput = (TextView) findViewById(R.id.counterOutput);
    }

    public void btnClicked(View view) {
        counter++;
        counterOutput.setText("Button presses: "+ counter);
    }



}
