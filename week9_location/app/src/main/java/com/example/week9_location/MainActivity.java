package com.example.week9_location;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

	// NOTE: Permission was added in AndroidManifest.xml
	
	private String TAG = "week9_location";
	
	TextView tvCoordinates;
	
	LocationManager locationManager;
	myLocationListener locationListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvCoordinates = (TextView) this.findViewById(R.id.tvCoordinates);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationListener = new myLocationListener();
	}
	
	@Override
	public void onResume() {
		super.onResume();

		// start location updates
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
	}

	@Override
	public void onPause() {
		super.onPause();

		// stop location updates while our app is not active
		locationManager.removeUpdates(locationListener);
	}

	private class myLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			
			String locationMessage = "Coordinates = (" + String.format("%.4f",location.getLatitude()) + ", "
					+ String.format("%.4f",location.getLongitude()) + ")";
			Log.i(TAG, "Location changed. " + locationMessage);
			
			tvCoordinates.setText(locationMessage);
		}

		@Override
		public void onProviderDisabled(String provider) {
			//Called when the provider is disabled by the user.
			//If requestLocationUpdates is called on an already disabled provider, this method is called immediately.
		}

		@Override
		public void onProviderEnabled(String provider) {
			// Called when the provider is enabled by the user.
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			//Called when the provider status changes.
			//This method is called when a provider is unable to fetch a location
			// or if the provider has recently become available after a period of unavailability.
		}
		
	}
}
