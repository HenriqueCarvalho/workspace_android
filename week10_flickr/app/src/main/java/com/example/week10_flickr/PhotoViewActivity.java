package com.example.week10_flickr;

import android.app.Activity;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PhotoViewActivity extends Activity {

	private static String TAG = "Week10_flickr";
	
	private ProgressBar pbWaitIndicator;
	private TextView tvPhotoTitle;
	private ImageView ivPhoto;
	private TextView tvPhotoLocation;
	
	private FlickrPhoto photo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_view);
		
		pbWaitIndicator = (ProgressBar) this.findViewById(R.id.pbWaitingForPhoto);
		tvPhotoTitle = (TextView) this.findViewById(R.id.tvPhotoTitle);
		tvPhotoLocation = (TextView) this.findViewById(R.id.tvCoordinates);
		ivPhoto = (ImageView) this.findViewById(R.id.ivPhoto);
		
		photo = null;
		
		Bundle extras = getIntent().getExtras();
		
		if (extras != null) {			
			photo = (FlickrPhoto) extras.getSerializable("photo");
		}

		if (photo != null) {
			
			Log.i(TAG, "photo URL = " + photo.pictureURL);
			
			tvPhotoTitle.setText(photo.title);
			
			// Use an AsyncTask to fetch the picture from the Internet
			new FetchPhotoAsyncTask().execute(photo);
			new FetchPhotoLocationAsyncTask().execute(photo);

		}
	}
	
	class FetchPhotoAsyncTask extends AsyncTask<FlickrPhoto, Void, Bitmap> {

		@Override
		protected void onPreExecute() {
			// still on the UI thread.
			pbWaitIndicator.setVisibility(View.VISIBLE);
		}

		protected Bitmap doInBackground(FlickrPhoto... photos) {
			FlickrPhoto photo = photos[0];
			return photo.getPhoto();
		}

		@Override
		protected void onPostExecute(Bitmap photo) {
			// Synchronized to UI thread.
			pbWaitIndicator.setVisibility(View.INVISIBLE);
			ivPhoto.setImageBitmap(photo);
		}
	}

	class FetchPhotoLocationAsyncTask extends AsyncTask<FlickrPhoto, Void, String> {

		@Override
		protected void onPreExecute() {
			// still on the UI thread.
//			pbWaitIndicator.setVisibility(View.VISIBLE);
		}

		protected String doInBackground(FlickrPhoto... photos) {
			FlickrPhoto photo = photos[0];
			return photo.getPhotoLocation();
		}

		@Override
		protected void onPostExecute(String locationMessage) {
			// Synchronized to UI thread.
//			pbWaitIndicator.setVisibility(View.INVISIBLE);
			
			if (locationMessage != null) {
				tvPhotoLocation.setText(locationMessage);
			}

		}
	}

}
