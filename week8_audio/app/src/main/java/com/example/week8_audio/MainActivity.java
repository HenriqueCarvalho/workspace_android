package com.example.week8_audio;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	private MediaPlayer musicPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		musicPlayer = MediaPlayer.create(this, R.raw.completion);
	}

	public void btnPlayClicked(View v) {
		musicPlayer.start();
	}
	
	public void btnPauseClicked(View v) {
		musicPlayer.pause();
	}

	public void btnStopClicked(View v) {
		musicPlayer.pause();
		musicPlayer.seekTo(0);
		
		// if we used musicPlayer.stop() we'd have to rebuild it:
//		musicPlayer.stop();
//		musicPlayer.release();
//		musicPlayer = MediaPlayer.create(this, R.raw.completion);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		musicPlayer.release();
		musicPlayer = null;
	}
	
}
