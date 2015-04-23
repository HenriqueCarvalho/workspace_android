package com.example.henriquecarvalho.hoc_midterm;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class FirstActivity extends ActionBarActivity {

    private TextView txtOutput;
    private String TAG = "First Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        txtOutput = (TextView)findViewById(R.id.txtOutput);

        Bundle extras = getIntent().getExtras();
        String userName;

        if (extras != null) {
            userName = extras.getString("username");
            txtOutput.setText(userName);
        }

    }

    public void btnLoginClicked(View btnView){
        Log.i(TAG, "btnLoginClicked started");

        Intent activity = new Intent(this, SecondActivity.class);

        String input = txtOutput.getText().toString();

        activity.putExtra("usernamelogin", input);

        this.startActivity(activity);
    }

}
