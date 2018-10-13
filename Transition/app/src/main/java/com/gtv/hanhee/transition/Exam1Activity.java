package com.gtv.hanhee.transition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//Transtion chuyển Intent
public class Exam1Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_slide).setOnClickListener(this);
        findViewById(R.id.button_fade).setOnClickListener(this);
        findViewById(R.id.button_default).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ImageActivity.class);
        switch (view.getId()) {
            case R.id.button_slide:
                intent.putExtra("type", 1);
                startActivity(intent);
                // hàm kết nối transtion chuyển intent trong anim
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                break;
            case R.id.button_fade:
                intent.putExtra("type", 2);
                startActivity(intent);
                // hàm kết nối transtion chuyển intent trong anim
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            default:
                startActivity(intent);
                break;
        }
    }
}
