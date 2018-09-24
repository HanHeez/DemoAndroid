package com.gtv.hanhee.hocoptionmenu2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtMau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {
        txtMau = (TextView) findViewById(R.id.txtMau);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.mnuMauXanh) {
            txtMau.setBackgroundColor(Color.BLUE);

        }

        else if (item.getItemId()==R.id.mnuMauDo) {
            txtMau.setBackgroundColor(Color.RED);
        }

        else if (item.getItemId()==R.id.mnuMauVang) {
            txtMau.setBackgroundColor(Color.YELLOW);
        }
        return super.onOptionsItemSelected(item);
    }
}
