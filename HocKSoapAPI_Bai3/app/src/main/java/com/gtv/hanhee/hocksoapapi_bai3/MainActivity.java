package com.gtv.hanhee.hocksoapapi_bai3;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.gtv.hanhee.config.Configuration;
import com.gtv.hanhee.model.Contact;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnLayDS;
    ListView lvContact;
    ArrayList <Contact> dsContact;
    ArrayAdapter<Contact> adapterContact;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLayDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulylayDS();
            }
        });
    }

    private void xulylayDS() {
        ListContactTask task = new ListContactTask();
        task.execute();
    }

    class ListContactTask extends AsyncTask<Void,Void,ArrayList<Contact>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            adapterContact.clear();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<Contact> contacts) {
            super.onPostExecute(contacts);
            adapterContact.clear();
            adapterContact.addAll(contacts);
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<Contact> doInBackground(Void... voids) {
            ArrayList<Contact> ds = new ArrayList<>();
            try {
                SoapObject request = new SoapObject(Configuration.NAMESPACE,Configuration.METHOD_NAME);


                SoapSerializationEnvelope envelope= new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE httpTransportSE= new HttpTransportSE(Configuration.SERVER_URL);
                httpTransportSE.call(Configuration.SOAP_ACTION_GET_DETAIL,envelope);

                SoapObject data= (SoapObject) envelope.getResponse();

                for (int i = 0; i <data.getPropertyCount();i++) {
                    SoapObject soapObject = (SoapObject) data.getProperty(i);
                    Contact contact = new Contact();
                    if (soapObject.hasProperty("Ma"))
                        contact.setMa(Integer.parseInt(soapObject.getPropertyAsString("Ma")));
                    if (soapObject.hasProperty("Ten"))
                        contact.setTen(soapObject.getPropertyAsString("Ten"));
                    if (soapObject.hasProperty("Phone"))
                        contact.setPhone(soapObject.getPropertyAsString("Phone"));
                    ds.add(contact);

                }
            }
            catch (Exception ex) {
                Log.e("Loi",ex.toString());
            }

            return ds;

        }
    }

    private void addControls() {
        btnLayDS = (Button) findViewById(R.id.btnLayDs);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Thong Bao");
        progressDialog.setMessage("Dang xu ly vui long cho");
        progressDialog.setCanceledOnTouchOutside(false);
        lvContact = (ListView) findViewById(R.id.lvContact);
        dsContact = new ArrayList<>();
        adapterContact = new ArrayAdapter<Contact>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                dsContact);
        lvContact.setAdapter(adapterContact);

    }
}
