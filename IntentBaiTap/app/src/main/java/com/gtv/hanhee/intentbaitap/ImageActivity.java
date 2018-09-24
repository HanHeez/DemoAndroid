package com.gtv.hanhee.intentbaitap;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Collections;

public class ImageActivity extends Activity {

    TableLayout tableLayoutImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        addControls();

    }

    private void addControls() {
        tableLayoutImage = (TableLayout) findViewById(R.id.tableLayoutImage);

        int soDong = 6;
        int soCot = 3;

        //tron mang
        Collections.shuffle(MainActivity.arrayName);

        for (int i = 1; i <= soDong; i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 1 ; j <= soCot; j++) {
                ImageView imageView = new ImageView(this);

                Resources r = getResources();
                int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, r.getDisplayMetrics());

                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(px,px);
                imageView.setLayoutParams(layoutParams);

                final int vitri = soCot*(i-1) + j-1;

                int idHinh = getResources().getIdentifier(MainActivity.arrayName.get(vitri),
                        "drawable",getPackageName());
                imageView.setImageResource(idHinh);
                //add imageview vao table row;
                tableRow.addView(imageView);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent();
                        intent.putExtra("tenhinhchon",MainActivity.arrayName.get(vitri));
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
            //add table row vao table;
            tableLayoutImage.addView(tableRow);
        }
    }
}
