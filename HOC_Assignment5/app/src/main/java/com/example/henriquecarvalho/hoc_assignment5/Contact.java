package com.example.henriquecarvalho.hoc_assignment5;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ListView;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by henriquecarvalho on 15-02-11.
 */
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;
    private String TAG = "Contact";

    public int id;
    public String name;
    public String address;
    public String phone;
    public String email;
    public String website;
    public String pictureURL;

    Contact(int id, String name, String address, String phone, String email, String website, String pictureURL) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.pictureURL = pictureURL;
    }

    public Bitmap getImage() {
        URL url = null;
        Bitmap bmp = null;

        if (this.pictureURL == null || this.pictureURL.length() == 0) {
            return null;
        }

        try {
            url = new URL(this.pictureURL);
        } catch (MalformedURLException e) {
            Log.i(TAG, "Could not load URL: '" + this.pictureURL + "'");
            Log.i(TAG, "Due to: " + e.getMessage());
        }

        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            Log.i(TAG, "Could not load URL: '" + this.pictureURL + "'");
            Log.i(TAG, "Due to: " + e.getMessage());
        }

        return bmp;
    }
}
