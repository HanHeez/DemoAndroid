package com.gtv.hanhee.albumslideshow;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView imgHinh;
    CheckBox chkTudong;
    ImageButton btnPrevious,btnNext;
    int CurrentPosition=-1;
    ArrayList<String> albums;
    Timer timer = null;
    TimerTask timerTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyxemhinhketiep();
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyxemhinhtruoc();
            }
        });

        chkTudong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b==true) {
                    btnPrevious.setEnabled(false);
                    btnNext.setEnabled(false);
                    xulytudongchayhinh();
                } else {
                    btnPrevious.setEnabled(true);
                    btnNext.setEnabled(true);
                    if (timer!=null) {
                        timer.cancel();
                    }
                }

            }
        });
    }

    private void xulytudongchayhinh() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CurrentPosition++;
                        if (CurrentPosition==albums.size()) CurrentPosition=0;
                        ImageTask task = new ImageTask();
                        task.execute(albums.get(CurrentPosition));
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask,0,3000);
    }

    private void xulyxemhinhtruoc() {
        CurrentPosition--;
        if (CurrentPosition==-1) {
            CurrentPosition=albums.size()-1;

        }
        ImageTask task = new ImageTask();
        task.execute(albums.get(CurrentPosition));
    }

    private void xulyxemhinhketiep() {
        CurrentPosition++;
        if (CurrentPosition==albums.size()) {
            CurrentPosition=0;

        }
        ImageTask task = new ImageTask();
        task.execute(albums.get(CurrentPosition));

    }

    private void addControls() {
        imgHinh = (ImageView) findViewById(R.id.imgHinh);
        chkTudong = (CheckBox) findViewById(R.id.chkTudong);
        btnPrevious = (ImageButton) findViewById(R.id.btnPrevious);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        albums = new ArrayList<>();
        albums.add("http://i.a4vn.com/2016/4/18/hot-girl-lilly-luta-tung-anh-bikini-goi-cam-chao-he-e0c8e0.jpg");
        albums.add("http://streaming1.danviet.vn/upload/2-2017/images/2017-06-09/149697496810237-haidua11.jpg");
        albums.add("https://gomibet.com/wp-content/uploads/2017/01/bo-suu-tap-nhung-hinh-anh-hot-girl-mac-bikini-goi-cam-nhat-6.jpg");
        albums.add("http://casinohappyluke.com/wp-content/uploads/2016/12/Hinh-1-Hot-girl-Thai-Lan.jpg");
        albums.add("https://1.bp.blogspot.com/-qbHa_K9gzEY/WEadstdPVwI/AAAAAAAABGA/ZxFAZp_5XdIbbrv4SRdYFiWWh2SfI6WgACLcB/s640/GaiXinhXinh-mfstar-xiuren-0435261016.jpg");
        albums.add("http://streaming1.danviet.vn/upload/1-2017/images/2017-03-13/148937071873174-14a.jpg");
        CurrentPosition=0;
        ImageTask task = new ImageTask();
        task.execute(albums.get(CurrentPosition));
    }

    class ImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgHinh.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            try {
                String link=strings[0];
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(link).getContent());
                return bitmap;

            } catch (Exception e) {
                Log.e("Loi",e.toString());
            }
            return null;
        }
    }
}
