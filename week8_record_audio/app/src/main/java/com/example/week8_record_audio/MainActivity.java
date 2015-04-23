package com.example.week8_record_audio;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

// See http://developer.android.com/guide/topics/media/audio-capture.html

public class MainActivity extends Activity {

	private String TAG = "week8_record_audio";
	
	private boolean recording = false;
	private boolean playing = false;
	
	private Button btnRecord;
	private Button btnPlay;
	
    private MediaRecorder mRecorder = null;
    private MediaPlayer   mPlayer = null;

    private static String mFileName = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnRecord = (Button) this.findViewById(R.id.btnRecord);
		btnPlay = (Button) this.findViewById(R.id.btnPlay);
		
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/week8_record_audio.3gp";

        Log.i(TAG, "mFileName = " + mFileName);
	}
	
	public void btnRecordClicked(View v) {
		if (playing) {
			// ignore the click
			return;
		}

		if (!recording) {
			startRecording();
		}
		else {
			stopRecording();
		}
		
		recording = !recording;
		btnRecord.setText(recording?"Stop Recording":"Record");
	}

	public void btnPlayClicked(View v) {
		if (recording || mFileName == null) {
			// ignore the click
			return;
		}
		
		if (!playing) {
			startPlaying();
		}
		else {
			stopPlaying();
		}
		
		playing = !playing;
		btnPlay.setText(playing?"Stop":"Play");
	}

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

}
