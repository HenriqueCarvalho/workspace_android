package com.example.henriquecarvalho.hoc_project;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MenuActivity extends ActionBarActivity {

    private String TAG = "MenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Setting the screen orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setBackgroundColor();
    }

    public void startBtnClicked(View view)
    {
        Log.i(TAG, "startBtnClicked method");
        Intent myIntent = new Intent(this, MainActivity.class);
        MenuActivity.this.startActivity(myIntent);
    }

    public void setBackgroundColor()
    {
        View layout = findViewById(R.id.menuRelativeLayout);

        GradientDrawable myGradient = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {0xff00ffff,0xff0099cc}
        );

        myGradient.setCornerRadius(0f);

        layout.setBackground(myGradient);
    }
}
