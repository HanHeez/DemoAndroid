package com.gtv.hanhee.fragmentchangeorientation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentStudentInfo extends Fragment {

    TextView txtHoten, txtNamsinh,txtDiachi, txtEmail;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_info, container, false);

        AddControls();
        return view;
    }

    public void SetInfo(SinhVien sv) {
        txtHoten.setText(sv.getHoTen());
        txtNamsinh.setText("Nam Sinh: "+sv.getNamSinh());
        txtDiachi.setText("Dia Chi: "+sv.getDiaChi());
        txtEmail.setText("Email: "+sv.getHoTen());

    }

    private void AddControls() {
        txtHoten = (TextView) view.findViewById(R.id.txtHoTen);
        txtDiachi = (TextView) view.findViewById(R.id.txtDiaChi);
        txtEmail = (TextView) view.findViewById(R.id.txtEmail);
        txtNamsinh = (TextView) view.findViewById(R.id.txtNamSinh);
    }
}
