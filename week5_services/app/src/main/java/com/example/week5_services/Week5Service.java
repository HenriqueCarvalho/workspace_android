package com.example.week5_services;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

public class Week5Service extends Service {

	private static final int SECOND = 1000; // time is in milliseconds

	Timer updatesTimer;
	Context currentContext;
	long count = 0;

	@Override
	public void onCreate() {
		currentContext = this;
		
		Toast.makeText(this, "Week 5 Service is starting up.", Toast.LENGTH_LONG).show();

		updatesTimer = new Timer();
		updatesTimer.schedule(new myNotifier(), SECOND, SECOND * 10); // every 10 seconds
	}

	@Override
	public IBinder onBind(Intent intent)
    {
		return null;
	}

	@Override
    public void onDestroy()
    {
		updatesTimer.cancel();
	}

	class myNotifier extends TimerTask
    {
		private Handler handler = new Handler();

		@Override
		public void run()
        {
			// For us to handle UI, we need to run this back on the UI Thread
			handler.post(doUpdateGUI);
		}		
	};
	
	// Runnable that lets us update the UI
	private Runnable doUpdateGUI = new Runnable()
    {
		public void run()
        {
			// We are back in the UI thread here
			Toast.makeText(currentContext, "Week 5 Service says hello: " + count++, Toast.LENGTH_LONG).show();
		}
	};


}
