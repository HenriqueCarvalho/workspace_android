package com.example.week5_alarms;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static long SECOND = 1000;
	private static String TAG = "Week5_Alarms";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.i(TAG, "Week5_alarms is starting");
		
		// See if a String was passed along with the intent - meaning it was an Alarm

		Bundle extras = getIntent().getExtras();
		
		if (extras != null) {			
			String theMessage = extras.getString("alarm");
			
			if (null != theMessage) {
				showAlert("Alarm!", theMessage);
				getIntent().removeExtra("alarm"); // remove it so it doesn't show again when rotating the device
			}
		}

	}
	
	public void setTheAlarm(View v) {

		//Get a reference to the Alarm Manager
		AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

		//Set the alarm to wake the device if sleeping.
		int alarmType = AlarmManager.ELAPSED_REALTIME_WAKEUP;

		//Schedule the alarm to repeat every half hour.
//		long timeOrLengthofWait = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
		long timeOrLengthofWait = 15 * SECOND;

	    // Create a Pending Intent that will start this Activity
	    Intent intentToFire = new Intent(this, MainActivity.class);
	    
		// Add an extra so we know it came from the Alarm
		intentToFire.putExtra("alarm", "Remember to check your email!");

		PendingIntent alarmIntent = PendingIntent.getActivity(this, 0, intentToFire, 0);

		//Wake up the device to fire an alarm in half an hour, and every 
		//half-hour after that.
		alarmManager.setInexactRepeating(alarmType,
				timeOrLengthofWait, 
				timeOrLengthofWait,
				alarmIntent);

		Toast.makeText(this, "Alarm has been set!", Toast.LENGTH_LONG).show();
	}
	
	public void cancelAlarm(View v) {
		//Get a reference to the Alarm Manager
		AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		
	    Intent intentToFire = new Intent(this, MainActivity.class);
		PendingIntent alarmIntent = PendingIntent.getActivity(this, 0, intentToFire, 0);

	    alarmManager.cancel(alarmIntent);
	    
		Toast.makeText(this, "Alarm has been cancelled", Toast.LENGTH_LONG).show();

	}
	
	// showAlert, borrowed from week4_alert example
	public void showAlert(String title, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(message)
		       .setTitle(title);

		AlertDialog dialog = builder.create();
		dialog.show();
	}

}
