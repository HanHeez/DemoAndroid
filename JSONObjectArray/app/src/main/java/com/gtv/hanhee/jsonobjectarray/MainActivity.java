package com.gtv.hanhee.jsonobjectarray;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvDs;
    ArrayList<String> arrayDs;
    ArrayAdapter adapterDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddControls();
        new ReadJSONArray().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo4.json");
    }

    private void AddControls() {
        lvDs = (ListView) findViewById(R.id.lvDs);
        arrayDs = new ArrayList<>();
        adapterDs = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                arrayDs);
        lvDs.setAdapter(adapterDs);
    }



    private class ReadJSONArray extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
            JSONArray array = new JSONArray(s);
            for (int i = 0; i < array.length(); i ++) {
                JSONObject object = array.getJSONObject(i);

                String khoahoc = object.getString("khoahoc");
                String hocphi = object.getString("hocphi");
                arrayDs.add(khoahoc + " - " + hocphi);

            }
            adapterDs.notifyDataSetChanged();
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
