package com.example.week10_flickr;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static String TAG = "Week10_flickr";
	
	private ProgressBar pbWaitIndicator;
	private ListView lvInterestingPhotos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pbWaitIndicator = (ProgressBar) this.findViewById(R.id.pbWaitIndicator);
		lvInterestingPhotos = (ListView) this.findViewById(R.id.lvInterestingPhotos);
		
		lvInterestingPhotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			// The onItemClick method gets called when the user taps
			//   on one of the list items
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

				FlickrPhoto selectedPhoto = (FlickrPhoto)adapter.getItemAtPosition(position);

				Log.i(TAG, "flickr photo selected: " + selectedPhoto.title);

				showPhotoViewActivity(selectedPhoto);
			}
		});

		// Use an AsyncTask to fetch the list of interesting photos from flickr
	    new FetchPhotosAsyncTask().execute();
	}
	
	private void showPhotoViewActivity(FlickrPhoto photo) {
		Intent activityIntent = new Intent(MainActivity.this, PhotoViewActivity.class);
		activityIntent.putExtra("photo", photo);
		startActivity(activityIntent);
	}

	class FetchPhotosAsyncTask extends AsyncTask<String, Void, FlickrPhoto[]> {

		@Override
		protected void onPreExecute() {
			// still on the UI thread.
			pbWaitIndicator.setVisibility(View.VISIBLE);
		}

		protected FlickrPhoto[] doInBackground(String... notused) {
			return FlickrPhoto.getInterestingPhotos();
		}

		@Override
		protected void onPostExecute(FlickrPhoto[] photos) {
			// Synchronized to UI thread.
			pbWaitIndicator.setVisibility(View.INVISIBLE);
			refreshListView(photos);
		}
	}

	public void refreshListView(final FlickrPhoto[] photos) {
		
		if (photos.length < 1) {
			// show a message?
		}
		
		// give the ListView a list of cats to display		
		// This overrides the getView method of ArrayAdapter to directly access the fields of the contacts
		ArrayAdapter<FlickrPhoto> adapter = new ArrayAdapter<FlickrPhoto>(this, android.R.layout.simple_list_item_2, android.R.id.text1, photos) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				TextView text1 = (TextView) view.findViewById(android.R.id.text1);
				TextView text2 = (TextView) view.findViewById(android.R.id.text2);

				FlickrPhoto photo = photos[position];
				
				text1.setText(photo.title);
				text2.setText(photo.pictureURL);

				return view;
			}
		};

		lvInterestingPhotos.setAdapter(adapter);
	}

}
