package com.example.week8_animation_transitions;

import android.app.Activity;
import android.os.Bundle;

public class Activity2 extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view2);
	}
	
	// Catch the Android system back button press
	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_right);
	}
}
