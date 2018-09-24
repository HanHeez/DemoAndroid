package com.gtv.hanhee.fragmentchangeorientation;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TruyenSinhVien{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void DataStudent(SinhVien sinhVien) {
        FragmentStudentInfo fragmentStudentInfo = (FragmentStudentInfo) getFragmentManager().findFragmentById(R.id.fragmentInfo);

        Configuration configuration = getResources().getConfiguration();
        if (fragmentStudentInfo!=null && configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentStudentInfo.SetInfo(sinhVien);
        } else {
            Intent intent = new Intent(MainActivity.this,StudentInformation.class);
            intent.putExtra("thongtinSinhVien", sinhVien);
            startActivity(intent);
        }

    }
}
