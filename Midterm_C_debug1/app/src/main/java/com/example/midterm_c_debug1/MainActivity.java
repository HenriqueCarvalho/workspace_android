package com.example.midterm_c_debug1;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void btnShowTheAnswerClicked(View v) {
		showAlert("The Answer", "The Answer to Life, the Universe, and Everything is 42.");
	}
	
	public void showAlert(String title, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(message)
		       .setTitle(title);
		AlertDialog dialog = builder.create();
		dialog.show();
	}

}
