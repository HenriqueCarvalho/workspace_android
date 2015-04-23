package com.example.henriquecarvalho.hoc_assignment3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {

    private String TAG = "Detail View";
    private TextView urlName;
    private String linkOfUrl;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        urlName = (TextView)findViewById(R.id.url);

        linkOfUrl = "Unknown URL";

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            linkOfUrl = extras.getString("url");
        }

        urlName.setText(linkOfUrl);

        webView = (WebView)findViewById(R.id.webView);
        webView.loadUrl(linkOfUrl);

        Log.i(TAG, "URL selected: " + linkOfUrl);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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

    public void btnBackToMainViewClicked(View btnView){
        Log.i(TAG, "btnBackToMainViewClicked started");

        Intent activityIntent = new Intent(DetailActivity.this, MainActivity.class);
        DetailActivity.this.startActivity(activityIntent);
    }
}
