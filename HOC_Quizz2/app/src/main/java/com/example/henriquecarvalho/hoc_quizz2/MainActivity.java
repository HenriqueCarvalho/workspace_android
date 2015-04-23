package com.example.henriquecarvalho.hoc_quizz2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private String TAG = "HOC_Quizz2";
    private TextView outputResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputResult = (TextView)findViewById(R.id.textOutput);
    }

    public void btnClicked(View view) {
        showAlertWithButtons("Hello!", "Show the message ?");
    }

    public void showAlertWithButtons(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message)
                .setTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i(TAG, "OK button was clicked");
                        outputResult.setText("The answer to life, the universe, and everything is 42!");

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i(TAG, "Cancel button was clicked");
                        Toast.makeText(MainActivity.this,"Dialog was cancelled", Toast.LENGTH_LONG).show();
                    }
                });

        AlertDialog dialog = builder.create();

        dialog.show();
    }

}
