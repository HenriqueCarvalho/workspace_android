package com.example.week8_animation_objects;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements AnimationListener {

	private String TAG = "week8_animation_objects";
	
	// handles for the UI components
	TextView animatedText;
	ImageView animatedImage;
	
	// animation
	Animation animation_fadein;
	Animation animation_rotate;
	Animation animation_zoomin;
	Animation animation_zoomout;
	Animation animation_slideright;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// get a handle to the label
		animatedText = (TextView) this.findViewById(R.id.txtAnimatedText);
		animatedImage = (ImageView) this.findViewById(R.id.ivAndroidIcon);

		// load the animation
		animation_fadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
		animation_rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
		animation_zoomin = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
		animation_zoomout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);
		animation_slideright = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_right);

		// set animation listener
		animation_fadein.setAnimationListener(this);
		animation_rotate.setAnimationListener(this);
		animation_zoomin.setAnimationListener(this);
		animation_zoomout.setAnimationListener(this);
	}
	
	public void btnFadeClicked(View v) {
		animatedText.startAnimation(animation_fadein);
	}

	public void btnRotateClicked(View v) {
		animatedImage.startAnimation(animation_rotate);
	}
		
	public void btnZoomInClicked(View v) {
		animatedImage.startAnimation(animation_zoomin);
	}

	public void btnZoomOutClicked(View v) {
		animatedImage.startAnimation(animation_zoomout);
	}

	public void btnSlideRightClicked(View v) {
		animatedImage.startAnimation(animation_slideright);
	}

	public void btnResetClicked(View v) {
		animatedImage.clearAnimation();
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		Log.i(TAG,"Animation has finished");
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
		Log.i(TAG,"Animation is repeating");
	}

	@Override
	public void onAnimationStart(Animation arg0) {
		Log.i(TAG,"Animation has started");
	}
}
