package com.gtv.hanhee.jsonobject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new READJSONObject().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json");
    }

    private class READJSONObject extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                String monhoc = object.getString("monhoc");
                String noihoc = object.getString("noihoc");
                String website = object.getString("website");
                Toast.makeText(MainActivity.this, monhoc+"\n"+noihoc+"\n"+website, Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

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
}
