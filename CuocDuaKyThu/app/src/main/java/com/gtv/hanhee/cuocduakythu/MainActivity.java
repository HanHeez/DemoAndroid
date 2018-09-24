package com.gtv.hanhee.cuocduakythu;


import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtPoints;
    CheckBox cb1, cb2, cb3;
    SeekBar sb1, sb2, sb3;
    ImageButton ibtnPlay;
    int sodiem = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        sb1.setEnabled(false);
        sb2.setEnabled(false);
        sb3.setEnabled(false);

        txtPoints.setText(sodiem + "");

        final CountDownTimer cdt = new CountDownTimer(20000, 300) {
            @Override
            public void onTick(long l) {
                int number = 5;

                if (sb1.getProgress()>= sb1.getMax()) {
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "ONE WIN", Toast.LENGTH_SHORT).show();
                    if (cb1.isChecked()) {
                        sodiem += 50;
                    } else {
                        sodiem -= 25;
                    }
                    txtPoints.setText(sodiem + "");
                    EnableCheckBox();
                }

                if (sb2.getProgress()>= sb2.getMax()) {
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "TWO WIN", Toast.LENGTH_SHORT).show();
                    if (cb2.isChecked()) {
                        sodiem += 50;
                    } else {
                        sodiem -= 25;
                    }
                    txtPoints.setText(sodiem + "");
                    EnableCheckBox();
                }

                if (sb3.getProgress()>= sb3.getMax()) {
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "THREE WIN", Toast.LENGTH_SHORT).show();
                    if (cb3.isChecked()) {
                        sodiem += 50;
                    } else {
                        sodiem -= 25;
                    }
                    txtPoints.setText(sodiem + "");
                    EnableCheckBox();
                }
                Random random = new Random();
                int one     = random.nextInt(number);
                int two     = random.nextInt(number);
                int three   = random.nextInt(number);
                int max;
                max = Math.max(one, two);
                max = Math.max(max,three);
                int i;
                for (i=0; i<= max; i++){
                    if  (i<=one) {
                    sb1.setProgress(sb1.getProgress() + 1 ); }
                    if  (i<=two) {
                    sb2.setProgress(sb2.getProgress() + 1 );
                        }
                    if  (i<=three) {
                        sb3.setProgress(sb3.getProgress()+ 1);
                    }
                }
            }

            @Override
            public void onFinish() {

            }
        };

        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb1.isChecked() || cb2.isChecked() || cb3.isChecked()) {
                    sb1.setProgress(0);
                    sb2.setProgress(0);
                    sb3.setProgress(0);
                    ibtnPlay.setVisibility(View.INVISIBLE);
                    cdt.start();
                    DisableCheckBox();
                } else {
                    Toast.makeText(MainActivity.this, "Vui long dat cuoc truoc khi choi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });

        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });

        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cb2.setChecked(false);
                    cb1.setChecked(false);
                }

            }
        });

    }

    private void EnableCheckBox() {
        cb1.setEnabled(true);
        cb2.setEnabled(true);
        cb3.setEnabled(true);
    }

    private void DisableCheckBox() {
        cb1.setEnabled(false);
        cb2.setEnabled(false);
        cb3.setEnabled(false);
    }

    private void AnhXa() {
        txtPoints = (TextView) findViewById(R.id.textViewPoints);
        cb1 = (CheckBox) findViewById(R.id.checkBox1);
        cb2 = (CheckBox) findViewById(R.id.checkBox2);
        cb3 = (CheckBox) findViewById(R.id.checkBox3);
        sb1 = (SeekBar) findViewById(R.id.seekBar1);
        sb2 = (SeekBar) findViewById(R.id.seekBar2);
        sb3 = (SeekBar) findViewById(R.id.seekBar3);
        ibtnPlay = (ImageButton) findViewById(R.id.imageButtonPlay);
    }

}
