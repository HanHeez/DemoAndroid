package com.gtv.hanhee.fragmentchangeorientation;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class FragmentStudentList extends ListFragment {

    ArrayList<SinhVien> arraySinhVien;
    StudentAdapter adapter;
    TruyenSinhVien truyenSv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        truyenSv = (TruyenSinhVien) getActivity();

        arraySinhVien = new ArrayList<>();
        AddArraySV();
        adapter = new StudentAdapter(getActivity(), R.layout.row_student,arraySinhVien);
        setListAdapter(adapter);

        View view = inflater.inflate(R.layout.fragment_student, container, false);
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        truyenSv.DataStudent(arraySinhVien.get(position));
    }

    private void AddArraySV() {
        arraySinhVien.add(new SinhVien("Nguyen Van A", 1999, "Ha Noi", "ngvana@gmail.com"));
        arraySinhVien.add(new SinhVien("Nguyen Thi B", 1992, "Ho Chi Minh", "ngthib@gmail.com"));
        arraySinhVien.add(new SinhVien("Hoang Van C", 1995, "Ha Nam", "hoangvanc@gmail.com"));
        arraySinhVien.add(new SinhVien("Nguyen Van D", 1999, "Phu Tho", "nvd@gmail.com"));
    }
}
