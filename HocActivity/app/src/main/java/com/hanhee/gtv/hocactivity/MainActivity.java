package com.hanhee.gtv.hocactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void xulychekhuattoanbo(View view) {
        Intent intent = new Intent(MainActivity.this,ManHinh2Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(MainActivity.this, "On Stop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(MainActivity.this, "On Destroy", Toast.LENGTH_SHORT).show();    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(MainActivity.this, "On Pause", Toast.LENGTH_SHORT).show();    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(MainActivity.this, "On Resume", Toast.LENGTH_SHORT).show();    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(MainActivity.this, "On Restart", Toast.LENGTH_SHORT).show();
    }

    public void xulychekhuat1phan(View view) {
        Intent intent = new Intent(MainActivity.this,Manhinh3Activity.class);
        startActivity(intent);
    }
}
