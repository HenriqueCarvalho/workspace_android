package com.example.henriquecarvalho.hoc_midterm;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class SecondActivity extends ActionBarActivity {

    private EditText txtUserNameInput;
    private String TAG = "Second View";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtUserNameInput = (EditText)findViewById(R.id.txtUserInput);

        Bundle extras = getIntent().getExtras();
        String userName;

        if (extras != null) {
            userName = extras.getString("usernamelogin");
            if(userName.equals("Not Logged In")){
                txtUserNameInput.setText("");
            }else{
                txtUserNameInput.setText(userName);
            }

        }
    }

    public void btnOkClicked(View btnView){
        Log.i(TAG, "btnOkClicked started");

        Intent activity = new Intent(this, FirstActivity.class);

        String input = txtUserNameInput.getText().toString();

        if(input.equals("")) {
            activity.putExtra("username", "Not Logged In");
        }else{
            activity.putExtra("username", input);
        }

        startActivity(activity);

    }

    public void btnCancelClicked(View btnView){
        Log.i(TAG, "btnCancelClicked started");

        finish();
    }

}
