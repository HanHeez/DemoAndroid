package com.gtv.hanhee.fragmentchangeorientation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StudentInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);
        Intent intent = getIntent();
        SinhVien sinhVien = (SinhVien) intent.getSerializableExtra("thongtinSinhVien");
        FragmentStudentInfo fragmentStudentInfo = (FragmentStudentInfo) getFragmentManager().findFragmentById(R.id.fragmentChiTiet);
        fragmentStudentInfo.SetInfo(sinhVien);
    }
}
