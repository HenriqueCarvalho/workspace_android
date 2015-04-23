package com.example.midterm_c_debug3;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    private String TAG = "Debug";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void btnPressAtOwnRiskClicked(View btnView) {
		showAlert("Hello!", "Thank you for pressing that button!");
        Log.i(TAG, "btnPressAtOwnRiskClicked started");
	}
	
	public void showAlert(String title, String message) {
        Log.i(TAG, "showAlert started");
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(message).setTitle(title);
		AlertDialog dialog = builder.create();
		dialog.show();
	}

}
