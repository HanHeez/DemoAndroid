package com.hanhee.gtv.projectcontactmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.hanhee.gtv.adapter.ContactAdapter;
import com.hanhee.gtv.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText txtTen,txtPhone;
    Button btnLuu;

    ListView lvdanhba;
    ArrayList<Contact> dsdanhba;
    ContactAdapter adapterContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyLuudanhba();
            }
        });
    }

    private void xulyLuudanhba() {
        Contact contact=new Contact(
                txtTen.getText().toString(),
                txtPhone.getText().toString());
        dsdanhba.add(contact);
        adapterContact.notifyDataSetChanged();

    }
    private void addControls() {
        txtTen = (EditText) findViewById(R.id.txtTen);
        txtPhone = (EditText) findViewById(R.id.txtPhone);
        btnLuu = (Button) findViewById(R.id.btnLuu) ;
        lvdanhba = (ListView) findViewById(R.id.lvDanhba);
        dsdanhba = new ArrayList<>();
        adapterContact = new ContactAdapter(MainActivity.this,R.layout.item, dsdanhba);
        lvdanhba.setAdapter(adapterContact);

    }

}

