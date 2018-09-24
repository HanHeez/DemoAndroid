package com.gtv.hanhee.hocksoapapi_bai2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gtv.hanhee.config.Configuration;
import com.gtv.hanhee.model.Contact;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.HttpsTransportSE;

public class MainActivity extends AppCompatActivity {

    EditText txtMa;
    TextView txtMaK, txtTen, txtPhone;
    Button btnLayChiTiet;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLayChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulylaychitiet();
            }
        });
    }

    private void xulylaychitiet() {
        int ma = Integer.parseInt(txtMa.getText().toString());
        ContactTask task = new ContactTask();
        task.execute(ma);

    }

    class ContactTask extends AsyncTask<Integer, Void, Contact> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtMaK.setText("");
            txtTen.setText("");
            txtPhone.setText("");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Contact contact) {
            super.onPostExecute(contact);
            txtMaK.setText(contact.getMa()+"");
            txtTen.setText(contact.getTen());
            txtPhone.setText(contact.getPhone());
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Contact doInBackground(Integer... integers) {

            try {
                int ma1 = integers[0];

                SoapObject request = new SoapObject(Configuration.NAMESPACE,Configuration.METHOD_NAME);
                request.addProperty(Configuration.PARAM_DETAIL_MA, ma1);

                SoapSerializationEnvelope envelope= new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE httpTransportSE= new HttpTransportSE(Configuration.SERVER_URL);
                httpTransportSE.call(Configuration.SOAP_ACTION_GET_DETAIL,envelope);

                SoapObject data= (SoapObject) envelope.getResponse();

                Contact contact = new Contact();

                if (data.hasProperty("Ma")) contact.setMa(Integer.parseInt(data.getPropertyAsString("Ma")));
                if (data.hasProperty("Ten")) contact.setTen(data.getPropertyAsString("Ten"));
                if (data.hasProperty("Phone")) contact.setPhone(data.getPropertyAsString("Phone"));
                return contact;


            } catch (Exception ex) {
                Log.e("Loi", ex.toString());
            }
            return null;
        }
    }

    private void addControls() {
        txtMa = (EditText) findViewById(R.id.txtMa);
        txtMaK = (TextView) findViewById(R.id.txtMaK);
        txtTen = (TextView) findViewById(R.id.txtTen);
        txtPhone = (TextView) findViewById(R.id.txtPhone);
        btnLayChiTiet = (Button) findViewById(R.id.btnLayChiTiet);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Thong Bao");
        progressDialog.setMessage("Dang xu ly vui long cho");
        progressDialog.setCanceledOnTouchOutside(false);
    }
}
