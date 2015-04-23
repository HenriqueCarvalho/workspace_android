package com.example.henriquecarvalho.hoc_final_app1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    TextView currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentPosition = (TextView) findViewById(R.id.currentPosition);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int positionX = (int)event.getX();
        int positionY = (int)event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
        }

        currentPosition.setText("Touched("+positionX+", "+positionY+")");

        return false;
    }
}
