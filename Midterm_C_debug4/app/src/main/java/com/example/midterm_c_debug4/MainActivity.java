package com.example.midterm_c_debug4;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private String TAG = "MidtermCdebug4";
	private ImageView ivThePicture;
	private ProgressBar busyIndicator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ivThePicture = (ImageView) findViewById(R.id.ivThePicture);
		busyIndicator = (ProgressBar) findViewById(R.id.progressBar);
		
		busyIndicator.setVisibility(View.INVISIBLE);

	}
	
	public void btnShowPictureClicked(View v) {
		busyIndicator.setVisibility(View.VISIBLE);
	    new GetImageAsyncTask().execute("http://ntewink.imgd.ca/cats/charliex2.jpg");
	}
	
	class GetImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

		protected Bitmap doInBackground(String... urls) {
			String url = urls[0];
			return getImage(url);
		}

		@Override
		protected void onPostExecute(Bitmap thePicture) {
			// Synchronized to UI thread.
			busyIndicator.setVisibility(View.INVISIBLE);
			ivThePicture.setImageBitmap(thePicture);
		}
	}

	public Bitmap getImage(String pictureURL) {
		URL url = null;
		Bitmap bmp = null;
		
		if (pictureURL == null || pictureURL.length() == 0) {
			return null;
		}

		try {
			url = new URL(pictureURL);
		} catch (MalformedURLException e) {
			Log.i(TAG, "Could not load URL: '" + pictureURL + "'");
			Log.i(TAG, "Due to: " + e.getMessage());
		}

		try {
			bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
		} catch (IOException e) {
			Log.i(TAG, "Could not load URL: '" + pictureURL + "'");
			Log.i(TAG, "Due to: " + e.getMessage());
		}
		
		return bmp;
	}

}
