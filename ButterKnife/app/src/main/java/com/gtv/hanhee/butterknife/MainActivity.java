package com.gtv.hanhee.butterknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.txtTitle)TextView txtTitle;
    @BindView(R.id.txtBody)TextView txtBody;
    @BindView(R.id.txtFooter)TextView txtFooter;
    @BindView(R.id.button) Button button;
    @BindString(R.string.title) String title;
    @BindString(R.string.body) String body;
    @BindViews({R.id.txtTitle, R.id.txtBody, R.id.txtFooter})
    List<TextView> nameViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        nameViews.get(0).setText(title);
        txtBody.setText("This is a Body!");
        txtFooter.setText("This is a Footer!");
        txtFooter.setAlpha(0.5f);
//        ButterKnife.apply(txtFooter, View.ALPHA, 0.5f);

    }

    @OnClick(R.id.button)
    public void submit(View view) {
        txtBody.setText(body);
    }
}
