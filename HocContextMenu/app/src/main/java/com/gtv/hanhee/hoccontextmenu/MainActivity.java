package com.gtv.hanhee.hoccontextmenu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3;
    Button btnlastedselected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnlastedselected=btn1;
            }
        });
        btn1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                btnlastedselected=btn1;
                return false;
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnlastedselected=btn2;
            }
        });
        btn2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                btnlastedselected=btn2;
                return false;
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnlastedselected=btn3;
            }
        });
        btn3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                btnlastedselected=btn3;
                return false;
            }
        });
    }

    private void addControls() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        registerForContextMenu(btn1);
        registerForContextMenu(btn2);
        registerForContextMenu(btn3);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.contextmenu_main,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.mnuIndam) {
            

        }
        else if (item.getItemId()==R.id.mnuMaudo) {
            btnlastedselected.setTextColor(Color.RED);

        }
        else if (item.getItemId()==R.id.mnuXoa) {
            btnlastedselected.setVisibility(View.INVISIBLE);

        }
        return super.onContextItemSelected(item);
    }
}
