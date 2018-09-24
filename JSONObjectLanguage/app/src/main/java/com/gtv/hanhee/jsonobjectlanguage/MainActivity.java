package com.gtv.hanhee.jsonobjectlanguage;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageButton btnVn, btnEn;
    TextView txtInfo;
    String noidung = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddControls();
        new ReadJSON().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo3.json");

        btnVn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReadJSONLanguage("vn");
            }
        });
        btnEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReadJSONLanguage("en");
            }
        });
    }

    private void AddControls() {
        btnVn = (ImageButton) findViewById(R.id.btnVn);
        btnEn = (ImageButton) findViewById(R.id.btnEn);
        txtInfo = (TextView) findViewById(R.id.txtInfo);
    }

    private class ReadJSON extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            noidung = s;
            ReadJSONLanguage("vn");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder content = new StringBuilder();

            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line="";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);

                }

                bufferedReader.close();


            } catch (Exception ex) {
                Log.e("Loi",ex.toString());
            }
            return content.toString();

        }


    }

    private void ReadJSONLanguage(String lang) {

        try {
            JSONObject object = new JSONObject(noidung);

            JSONObject objectLanguage = object.getJSONObject("language");

            JSONObject objectVn = objectLanguage.getJSONObject(lang);


            String ten = objectVn.getString("name");
            String diachi = objectVn.getString("address");
            String khoahoc1 = objectVn.getString("course1");
            String khoahoc2 = objectVn.getString("course2");
            String khoahoc3 = objectVn.getString("course3");

            txtInfo.setText(ten + "\n" + diachi + "\n" + khoahoc1 + "\n" + khoahoc2 + "\n" + khoahoc3);
        } catch (Exception ex) {
            Log.e("Loi",ex.toString());
        }
    }
}
