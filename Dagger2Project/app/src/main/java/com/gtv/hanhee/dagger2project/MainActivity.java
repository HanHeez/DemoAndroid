package com.gtv.hanhee.dagger2project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.gtv.hanhee.dagger2project.Example.Example;

import java.util.Date;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Example myExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtDate = findViewById(R.id.txtDate);
        ((MyApplication) getApplication())
                .getExamComponent()
                .inject(MainActivity.this);
        txtDate.setText(new Date(myExample.getDate())+"");
    }
}
