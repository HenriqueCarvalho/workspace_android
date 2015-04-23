package com.example.week5_services;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void startTheService(View v) {
        startService(new Intent(this, Week5Service.class));
	}
	
	public void stopTheService(View v) {
        stopService(new Intent(this, Week5Service.class));
	}

}
