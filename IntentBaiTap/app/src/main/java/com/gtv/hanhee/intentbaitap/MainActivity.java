package com.gtv.hanhee.intentbaitap;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> arrayName;
    ImageView imgGoc, imgNhan;
    int REQUEST_CODE_IMAGE = 123;
    String tenHinhgoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        imgNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,ImageActivity.class),REQUEST_CODE_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data!= null) {
            String tenHinhnhan = data.getStringExtra("tenhinhchon");
            int idHinhnhan = getResources().getIdentifier(tenHinhnhan,"drawable", getPackageName());
            imgNhan.setImageResource(idHinhnhan);
            if (tenHinhgoc.equals(tenHinhnhan)) {
                Toast.makeText(this, "Chinh xac", Toast.LENGTH_SHORT).show();
                // doi hinh goc

                new CountDownTimer(2000, 100) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        Collections.shuffle(arrayName);
                        tenHinhgoc = arrayName.get(5);

                        int idHinh = getResources().getIdentifier(tenHinhgoc,"drawable",getPackageName());

                        imgGoc.setImageResource(idHinh);
                    }
                }.start();

                }

            else Toast.makeText(this, "Sai roi", Toast.LENGTH_SHORT).show();

        }

        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Ban chua chon hinh", Toast.LENGTH_SHORT).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addControls() {
        imgGoc = (ImageView) findViewById(R.id.imgGoc);
        imgNhan = (ImageView) findViewById(R.id.imgNhan);

        String[] mangTen = getResources().getStringArray(R.array.list_name);
        arrayName = new ArrayList<>(Arrays.asList(mangTen));

        //tron mang?
        Collections.shuffle(arrayName);
        tenHinhgoc = arrayName.get(5);

        int idHinh = getResources().getIdentifier(tenHinhgoc,"drawable",getPackageName());

        imgGoc.setImageResource(idHinh);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reload, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuReload) {
            Collections.shuffle(arrayName);
            tenHinhgoc = arrayName.get(5);

            int idHinh = getResources().getIdentifier(tenHinhgoc,"drawable",getPackageName());

            imgGoc.setImageResource(idHinh);

        }
        return super.onOptionsItemSelected(item);
    }
}
