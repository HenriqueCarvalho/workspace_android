package com.example.week10_flickr;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class FlickrPhoto implements Serializable {
	
	private static String TAG = "Week10_flickr";
	private static final long serialVersionUID = 1L;
	
	public String photo_id;
	public String title;
	public String pictureURL;
	
	FlickrPhoto(String photo_id, String title, String secret, String server, String farm) {
		this.photo_id = photo_id;
		this.title = title;
		
		this.pictureURL = "http://farm" + farm + ".static.flickr.com/" + server + 
				"/" + photo_id + "_" + secret + ".jpg";
	}

	// get the flickr photos for this place
	//
	// this must be called on a separate thread.
	// this also needs INTERNET permission
	public static FlickrPhoto[] getInterestingPhotos() {
		
		Log.i(TAG, "getInterestingPhotos");

		ArrayList<FlickrPhoto> photos = new ArrayList<FlickrPhoto>();
		
		// the "per_page=50" parameter limits the results to the first 50
		// separating the pieces onto separate lines helps us see what's being sent
	    String flickrPhotosURLString = "https://api.flickr.com/services/rest/?" 
	    								+ "method=flickr.interestingness.getList"
	    								+ "&per_page=50" 
	    								+ "&format=json" 
	    								+ "&nojsoncallback=1"
	    								+ "&api_key=" + FlickrAPIKEY.KEY;
	    
	    String response = getStringFromURL(flickrPhotosURLString); // see helper method further down
	    
		// At this point we have called flickr and have received some kind of response
		// show the raw results in LogCat
		Log.i(TAG, "response = \n" + response);

		if (response != "" && response.length() > 0) {
			// now that we have a response, we can parse it to get the information we need
			try {
				JSONTokener tokener = new JSONTokener(response);				
				JSONObject jsonObj = (JSONObject) tokener.nextValue();

				String strPhotosJSON = "";

				if (jsonObj.has("photos")){
					strPhotosJSON = jsonObj.getString("photos");
					JSONTokener photolistTokener = new JSONTokener(strPhotosJSON);				
					JSONObject photolistObj = (JSONObject) photolistTokener.nextValue();

					if (photolistObj.has("photo")){
						strPhotosJSON = photolistObj.getString("photo");

						if (strPhotosJSON != null && strPhotosJSON.length() > 0) {
							JSONTokener tokenerPhotos = new JSONTokener(strPhotosJSON);				
							JSONArray JsonArrayPhotos = (JSONArray) tokenerPhotos.nextValue();
							int size = JsonArrayPhotos.length();

							for (int i=0 ; i<size; i++){
								JSONObject jsonObj_photo = JsonArrayPhotos.getJSONObject(i);

								String id = "";
								String title = "";
								String secret = "";
								String server = "";
								String farm = "";

								if (jsonObj_photo.has("id")){
									id = jsonObj_photo.getString("id");
								}
								if (jsonObj_photo.has("title")){
									title = jsonObj_photo.getString("title");
								}
								if (jsonObj_photo.has("secret")){
									secret = jsonObj_photo.getString("secret");
								}
								if (jsonObj_photo.has("server")){
									server = jsonObj_photo.getString("server");
								}
								if (jsonObj_photo.has("farm")){
									farm = jsonObj_photo.getString("farm");
								}

								FlickrPhoto photo = new FlickrPhoto(id, title, secret, server, farm);
								photos.add(photo);
							}

						}

					}
				}
			}
			catch (Exception e) {
				Log.d(TAG, "problem with JSON parsing: " + e.toString());
			}

		}
		
		FlickrPhoto[] photosArray = new FlickrPhoto[photos.size()];
		return  photos.toArray(photosArray);
	}

	// We are returning a String because it's quick and easy.
	// You might want to return an object containing all of the
	//   information available from the flickr getLocation call.
	public String getPhotoLocation() {
		String locationMessage = "Oops! An error occurred.";
		
		Log.i(TAG, "getPhotoLocation");
		
	    String flickrPhotosURLString = "https://api.flickr.com/services/rest/?" 
	    								+ "method=flickr.photos.geo.getLocation"
	    								+ "&format=json" 
	    								+ "&nojsoncallback=1"
	    								+ "&photo_id=" + this.photo_id
	    								+ "&api_key=" + FlickrAPIKEY.KEY;
	    
		Log.i(TAG, "URL = " + flickrPhotosURLString);

	    String response = getStringFromURL(flickrPhotosURLString); // see helper method further down
	    
		// At this point we have called flickr and have received some kind of response
		// show the raw results in LogCat
		Log.i(TAG, "response = \n" + response);

		if (response != "" && response.length() > 0) {
			// now that we have a response, we can parse it to get the information we need
			try {
				JSONTokener tokener = new JSONTokener(response);				
				JSONObject jsonObj = (JSONObject) tokener.nextValue();

				String strJSON = "";

				if (jsonObj.has("message")){
					String message = jsonObj.getString("message");
					Log.i(TAG, "Location information not found: " + message);
					return message;
				}
				else if (jsonObj.has("photo")){
					strJSON = jsonObj.getString("photo");
					JSONTokener photoTokener = new JSONTokener(strJSON);				
					JSONObject photoObj = (JSONObject) photoTokener.nextValue();

					if (photoObj.has("location")){
						strJSON = photoObj.getString("location");
						JSONTokener photoLocationTokener = new JSONTokener(strJSON);				
						JSONObject photoLocationObj = (JSONObject) photoLocationTokener.nextValue();

						String latitude = "?";
						String longitude = "?";
						
						if (photoLocationObj.has("latitude")){
							latitude = photoLocationObj.getString("latitude");
						}
						if (photoLocationObj.has("longitude")){
							longitude = photoLocationObj.getString("longitude");
						}
						
						locationMessage = "Photo Location Coordinates = (" + latitude + ", " + longitude + ")";
					}
				}
			}
			catch (Exception e) {
				Log.d(TAG, "problem with JSON parsing: " + e.toString());
			}
		}
		
		return locationMessage;
	}
	
	//
	// Load the image for this photo from the URL
	// remember to get Internet permission (see AndroidManifest)
	//
	// Warning!
	// Network calls are not allowed on the main thread: android.os.NetworkOnMainThreadException
	//
	public Bitmap getPhoto() {
		URL url = null;
		Bitmap bmp = null;
		
		if (this.pictureURL == null || this.pictureURL.length() == 0) {
			return null;
		}

		try {
			url = new URL(this.pictureURL);
		} catch (MalformedURLException e) {
			Log.i(TAG, "Could not load URL: '" + this.pictureURL + "'");
			Log.i(TAG, "Due to: " + e.getMessage());
		}

		try {
			bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
		} catch (IOException e) {
			Log.i(TAG, "Could not load URL: '" + this.pictureURL + "'");
			Log.i(TAG, "Due to: " + e.getMessage());
		}
		
		return bmp;
	}

	// helper method to do the actual internet call and get the text result
	public static String getStringFromURL(String UrlString) {
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
		HttpConnectionParams.setSoTimeout(httpParams, 10000);
	    
	    DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
	    HttpContext httpContext = new BasicHttpContext();
	    
	    HttpPost httpPost = new HttpPost(UrlString);
//		httpPost.setHeader("Authorization", "for other servers, this is where the login/security info goes");

	    String response = "";

		try {
			HttpResponse httpresponse = httpClient.execute(httpPost, httpContext);
			
			if (httpresponse != null) {
				response = (EntityUtils.toString(httpresponse.getEntity()));
			}
			else {
				// error
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
}
