package com.gtv.hanhee.readrss;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvTieude;
    ArrayList<String> arrTieude,arrLink;
    ArrayAdapter tieudeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        new ReadRSS().execute("https://vnexpress.net/rss/suc-khoe.rss");
        lvTieude.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,DocBaoActivity.class);
                intent.putExtra("linktintuc",arrLink.get(i));
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        lvTieude = (ListView) findViewById(R.id.lvTieude);
        arrTieude = new ArrayList<>();
        arrLink = new ArrayList<>();
        tieudeAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrTieude);
        lvTieude.setAdapter(tieudeAdapter);
    }

    class ReadRSS extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);

            NodeList nodeList = document.getElementsByTagName("item");

            String tieude = "";

            for (int i  = 0; i < nodeList.getLength(); i++ ) {
                Element element= (Element) nodeList.item(i);
                tieude = parser.getValue(element, "title");
                arrTieude.add(tieude);
                arrLink.add(parser.getValue(element, "link"));
            }

            tieudeAdapter.notifyDataSetChanged();



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

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }

                bufferedReader.close();

            } catch (Exception ex){
                Log.e("Loi",ex.toString());

            }
            return content.toString();
        }
    }
}
