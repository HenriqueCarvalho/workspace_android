package com.example.henriquecarvalho.hoc_assignment5;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by henriquecarvalho on 15-02-11.
 */
public class ContactArrayAdapter extends ArrayAdapter<Contact> {

    Context context;
    int layoutResourceId;
    ArrayList<Contact> contactList = null;

    public ContactArrayAdapter(Context context, int layoutResourceId, ArrayList<Contact> contactList) {
        super(context, layoutResourceId, contactList);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.contactList = contactList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ContactDetailsHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ContactDetailsHolder();
            holder.txtName = (TextView)row.findViewById(R.id.txtName);
            holder.txtPhone = (TextView)row.findViewById(R.id.txtPhone);

            row.setTag(holder);
        }
        else
        {
            holder = (ContactDetailsHolder)row.getTag();
        }

        Contact contact = this.contactList.get(position);
        holder.txtName.setText(contact.name);
        holder.txtPhone.setText(contact.phone);

        return row;
    }

    static class ContactDetailsHolder
    {
        TextView txtName;
        TextView txtPhone;
    }

}
