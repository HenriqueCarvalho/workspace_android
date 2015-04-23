package com.example.henriquecarvalho.hoc_assignment5;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;


public class ContactDetails extends ActionBarActivity {

    private TextView txtContactName;
    private TextView txtContactPhone;
    private ImageView ivContactPicture;
    private TextView txtContactAddress;
    private TextView txtContactEmail;
    private TextView txtContactWebsite;

    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        txtContactName = (TextView) this.findViewById(R.id.txtName);
        txtContactPhone = (TextView) this.findViewById(R.id.txtPhone);
        txtContactAddress = (TextView) this.findViewById(R.id.txtAddress);
        txtContactEmail = (TextView) this.findViewById(R.id.txtEmail);
        txtContactWebsite = (TextView) this.findViewById(R.id.txtWebsite);
        ivContactPicture = (ImageView) this.findViewById(R.id.contactPicture);

        contact = null;

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            contact = (Contact) extras.getSerializable("contact");
        }

        txtContactName.setText(contact.name);
        txtContactPhone.setText(contact.phone);
        txtContactAddress.setText(contact.address);
        txtContactEmail.setText(contact.email);
        txtContactWebsite.setText(contact.website);

        // instead, use an AsyncTask
        new GetContactImageAsyncTask().execute(contact);
    }

    public void btnBack(View btnView)
    {
        finish();
    }

    class GetContactImageAsyncTask extends AsyncTask<Contact, Void, Bitmap> {

        protected Bitmap doInBackground(Contact... contacts) {
            Contact contact = contacts[0];
            return contact.getImage();
        }

        @Override
        protected void onPostExecute(Bitmap contactPicture) {
            // Synchronized to UI thread.
            ivContactPicture.setImageBitmap(contactPicture);
        }
    }


}
