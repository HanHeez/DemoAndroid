package com.gtv.hanhee.hocasynctaskphan1;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText txtSobutton;
    Button btnVebutton;
    ProgressBar pgbPercent;
    LinearLayout layoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvents();
    }

    private void addEvents() {
        btnVebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                veButtonthoigianthuc();
            }
        });
    }

    private void veButtonthoigianthuc() {
        int n = Integer.parseInt(txtSobutton.getText().toString());

        ButtonTask task = new ButtonTask();
        task.execute(n);
    }

    private void addControl() {
        txtSobutton = (EditText) findViewById(R.id.txtSobutton);
        btnVebutton = (Button) findViewById(R.id.btnVebutton);
        pgbPercent = (ProgressBar) findViewById(R.id.pgbPercent);
        layoutButton = (LinearLayout) findViewById(R.id.layoutButton);
    }

    class ButtonTask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            layoutButton.removeAllViews();
            pgbPercent.setProgress(0);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pgbPercent.setProgress(100);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int value=values[1];
            int percent=values[0];
            pgbPercent.setProgress(percent);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            Button btn= new Button(MainActivity.this);
            btn.setLayoutParams(params);
            btn.setText(value+"");
            layoutButton.addView(btn);
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            int n = integers[0];
            Random random = new Random();
            for (int i = 0; i < n; i ++) {
                int percent = i*100 /n;
                int value = random.nextInt(500);
                publishProgress(percent,value);
                SystemClock.sleep(100);
            }
            return null;
        }
    }

}
