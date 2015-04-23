package com.example.week8_animation_transitions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void btnShowView2Clicked(View v) {
		showActivity2();
	}
	
	public void showActivity2() {
		Intent activity2Intent = new Intent(this, Activity2.class);
		startActivity(activity2Intent);
		
		// override the transition animation after the startActivity call
		overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_right);
	}
}
