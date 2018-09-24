package com.gtv.hanhee.fragmentbubdle;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddControls();
    }

    private void AddControls() {
        btnAdd = (Button) findViewById(R.id.btnAdd);

        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentA fragmentA = new FragmentA();
                Bundle bundle = new Bundle();
                bundle.putString("hotenSinhVien", "Nguyen Van A");
                fragmentA.setArguments(bundle);

                fragmentTransaction.add(R.id.myLinearLayout,fragmentA);
                fragmentTransaction.commit();

            }
        });
    }
}
