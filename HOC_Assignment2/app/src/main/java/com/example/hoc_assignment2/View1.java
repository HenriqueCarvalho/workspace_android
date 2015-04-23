package com.example.hoc_assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class View1 extends Activity {
  
    private String TAG = "ActivityOne";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view1);
        Log.i(TAG, "onCreate() method");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i(TAG, "onStart method");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG, "onResume method");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i(TAG, "onStop method");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.i(TAG, "onRestart method");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy method");
    }

    public void btnShowView2Clicked(View btnView){
        Log.i(TAG, "btnShowView2Clicked started");

        Intent myIntent = new Intent(View1.this, View2.class);
        View1.this.startActivity(myIntent);
    }
}
