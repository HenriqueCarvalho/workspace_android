package com.example.henriquecarvalho.hoc_quiz1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private EditText inputNumber;
    private TextView outputResult;
    private String TAG = "Quiz 1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumber = (EditText)findViewById(R.id.inputNumber);
        outputResult = (TextView)findViewById(R.id.outputResult);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String convertTempFStringToTempCString(String tempFString) {
        String result = "";

        if (tempFString == null || tempFString.length() == 0) {
            tempFString = "0";
        }

        try {
            Float tempF = Float.valueOf(tempFString); // convert to a number
            Float tempC = (tempF-32) * 5 / 9;	// convert number from F to C
            result = String.format("%.1f", tempC); // convert the result to a String for display
        }
        catch(Exception e) {
            result = "?";
        }

        return result;
    }


    public void btnConvertClicked(View buttonView){

        Log.i(TAG, "Convert Button was clicked");

        String input = inputNumber.getText().toString();

        outputResult.setText(this.convertTempFStringToTempCString(input));
    }

}
