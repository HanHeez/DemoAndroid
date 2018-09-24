package com.gtv.hanhee.ungdungquanan.Presenter.DanDuongGoogleMap;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadPolyline extends AsyncTask<String, Void, String> {
    StringBuffer stringBuffer;
    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            stringBuffer = new StringBuffer();
            String line ="";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }

    @Override
    protected void onPostExecute(String dataJSON) {
        super.onPostExecute(dataJSON);
    }

}
