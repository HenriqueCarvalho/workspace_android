package com.example.week8_frame_animation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {

	ImageView ivEarth;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ivEarth = (ImageView) this.findViewById(R.id.ivEarth);
		
		AnimationDrawable animation = (AnimationDrawable) ivEarth.getDrawable();
		animation.start();
	}
}
