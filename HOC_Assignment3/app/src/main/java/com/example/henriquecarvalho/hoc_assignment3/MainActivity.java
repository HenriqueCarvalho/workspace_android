package com.example.henriquecarvalho.hoc_assignment3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    private String TAG = "List View";

    private ListView listOfUrls;

    String[] urls = new String[] {"https://www.google.ca", "http://www.apple.com", "https://github.com",
            "http://www.wikipedia.org" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfUrls = (ListView)findViewById(R.id.listView);

        listOfUrls.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, urls));

        listOfUrls.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                String selected = (String)adapter.getItemAtPosition(position);

                Log.i(TAG, "URL selected: " + selected);

                showDetailActivity(selected);
            }

        });
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

    private void showDetailActivity(String selectedItem) {
        Intent activityIntent = new Intent(MainActivity.this, DetailActivity.class);

        activityIntent.putExtra("url", selectedItem);

        startActivity(activityIntent);
    }
}
