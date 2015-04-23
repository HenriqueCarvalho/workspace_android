package com.example.hoc_assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class View2 extends Activity {
  
    private String TAG = "ActivityTwo";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view2);
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

    public void btnBackToView1Clicked(View btnView){
        Log.i(TAG, "btnBackToView1Clicked started");

        Intent myIntent = new Intent(View2.this, View1.class);
        View2.this.startActivity(myIntent);

        finish();
    }
  
}
