package com.hanhee.gtv.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hanhee.gtv.model.Contact;
import com.hanhee.gtv.projectcontactmanager.R;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    Activity context;
    int resource;
    List<Contact> objects;

    public ContactAdapter(@NonNull Activity context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= this.context.getLayoutInflater();
        View row= inflater.inflate(this.resource,null);

        TextView txtTen = (TextView) row.findViewById(R.id.txtTen);
        TextView txtPhone = (TextView) row.findViewById(R.id.txtPhone);
        ImageButton btnCall = (ImageButton) row.findViewById(R.id.btnCall);
        ImageButton btnSms = (ImageButton) row.findViewById(R.id.btnSms);
        ImageButton btnDelete = (ImageButton) row.findViewById(R.id.btnDelete);

        final Contact contact = this.objects.get(position);
        txtTen.setText(contact.getTen());
        txtPhone.setText(contact.getPhone());

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyCall(contact);
            }
        });
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulySms(contact);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyDelete(contact);
            }
        });
        return row;

    }

    private void xulyDelete(Contact contact) {
    }

    private void xulySms(Contact contact) {
    }

    private void xulyCall(Contact contact) {
    }
}
