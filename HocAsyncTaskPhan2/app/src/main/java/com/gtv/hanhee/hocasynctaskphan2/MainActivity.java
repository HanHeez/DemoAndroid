package com.gtv.hanhee.hocasynctaskphan2;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnTaihinh;
    ImageView imgHinh;
    ProgressDialog dialog;
    String link="http://2sao.vietnamnetjsc.vn/images/2017/09/03/06/48/hot-girl-3.jpg";
    String link2= "http://i.a4vn.com/2017/11/14/3-vong-cang-day-cua-hot-girl-baby-bin-01f68d.jpg";
    String link3= "http://file.vforum.vn/hinh/2018/01/top-nhung-hot-girl-trung-quoc-xinh-nhat-7.png";
    String link4= "http://streaming1.danviet.vn/upload/2-2017/images/2017-06-09/149696624673874-18698458_1886304408287789_1818659098802069551_n.jpg";

    ArrayList<String> dsHinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvent();
    }

    private void addEvent() {
        btnTaihinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulytaihinh();
            }
        });
    }

    private void xulytaihinh() {

        Random rd = new Random();
        int n = rd.nextInt(4);

        ImageTask imageTask = new ImageTask();
        imageTask.execute(dsHinh.get(n));
    }

    private void addControls() {
        btnTaihinh = (Button) findViewById(R.id.btnTaiHinh);
        imgHinh = (ImageView) findViewById(R.id.imgHinh);
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Thong Bao");
        dialog.setMessage("Dang tai hinh vui long cho");
        dialog.setCanceledOnTouchOutside(false);
        dsHinh = new ArrayList<>();
        dsHinh.add(link);
        dsHinh.add(link2);
        dsHinh.add(link3);
        dsHinh.add(link4);

    }

    class ImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgHinh.setImageBitmap(bitmap);
            dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                String link = strings[0];
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(link).getContent());
                return bitmap;

            } catch (Exception e) {
                Log.e("Loi", e.toString());
            }
            return null;
        }
    }
}
