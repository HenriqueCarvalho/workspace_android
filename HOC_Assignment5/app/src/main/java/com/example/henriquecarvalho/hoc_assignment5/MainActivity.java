package com.example.henriquecarvalho.hoc_assignment5;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    private String TAG = "Assignment5";
    private ListView listOfContacts;
    private ContactList contact = new ContactList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfContacts = (ListView)findViewById(R.id.listView);

        // NEW METHOD - use a custom adapter
        ContactArrayAdapter contactArrayAdapter = new ContactArrayAdapter(this, R.layout.activity_row, ContactList.getArrayListOfContacts());
        listOfContacts.setAdapter(contactArrayAdapter);
        contactArrayAdapter.notifyDataSetChanged();

        listOfContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // The onItemClick method gets called when the user taps
            //   on one of the list items
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                Contact selected = (Contact)adapter.getItemAtPosition(position);
                Log.i(TAG, "Contact selected: " + selected.name);
                showDetailActivity(selected);
            }

        });
    }

    private void showDetailActivity(Contact contact)
    {
        Intent activityIntent = new Intent(this, ContactDetails.class);
        activityIntent.putExtra("contact", contact);
        startActivity(activityIntent);
    }

    class LoadListInBackground extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... params) {
            // need to preload all the cat images here
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Synchronized to UI thread.

        }
    }

}
