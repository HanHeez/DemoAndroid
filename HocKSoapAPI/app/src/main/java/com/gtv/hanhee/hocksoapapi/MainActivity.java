package com.gtv.hanhee.hocksoapapi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gtv.hanhee.config.Configuration;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.HttpsTransportSE;

public class MainActivity extends AppCompatActivity {

    EditText txtC;
    Button btnF;
    TextView txtF;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulylaydoF();
            }
        });
    }

    private void xulylaydoF() {
        String c = txtC.getText().toString();

        CtoFTask task=   new CtoFTask();
        task.execute(c);

    }

    class CtoFTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtF.setText("");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtF.setText(s);
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String c = strings[0];
                SoapObject request = new SoapObject(Configuration.NAME_SPACE,Configuration.METHOD);
                request.addProperty(Configuration.PARAM, Integer.parseInt(c));

                SoapSerializationEnvelope envelope= new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=true;
                envelope.setOutputSoapObject(request);

                HttpsTransportSE httpsTransportSE = new HttpsTransportSE(Configuration.HOST,443,Configuration.WS_OPS,2000);
                httpsTransportSE.call(Configuration.SOAP_ACTION_C_TO_F,envelope);

                SoapPrimitive data= (SoapPrimitive) envelope.getResponse();

                return data.toString();


            } catch (Exception ex) {
                Log.e("LOI",ex.toString());
            }
            return null;
        }
    }

    private void addControls() {
        txtC = (EditText) findViewById(R.id.txtC);
        btnF = (Button) findViewById(R.id.btnF);
        txtF = (TextView) findViewById(R.id.txtF);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Thong Bao");
        progressDialog.setMessage("Dang xu ly vui long cho");
        progressDialog.setCanceledOnTouchOutside(false);

    }
}
