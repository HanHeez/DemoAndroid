package com.gtv.hanhee.fragmentcommunicator;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentA extends Fragment{

    TextView txtA;
    EditText edtA;
    Button btnA;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_a, container, false);

        txtA = (TextView) view.findViewById(R.id.txtA);
        edtA = (EditText) view.findViewById(R.id.edtA);
        btnA = (Button) view.findViewById(R.id.btnA);

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtMain = (TextView) getActivity().findViewById(R.id.txtMain);
                txtMain.setText(edtA.getText().toString());
            }
        });

        return view;


    }

    public void GanNoiDung(String noidung) {
        txtA.setText(noidung);
    }
}
