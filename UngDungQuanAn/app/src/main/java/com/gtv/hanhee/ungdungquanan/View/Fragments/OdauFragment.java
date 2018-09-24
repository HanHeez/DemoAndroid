package com.gtv.hanhee.ungdungquanan.View.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.gtv.hanhee.ungdungquanan.Presenter.ManHinhTrangChu.MyPresenterOdau;
import com.gtv.hanhee.ungdungquanan.R;

public class OdauFragment extends Fragment {

    MyPresenterOdau mPresenterOdau;
    RecyclerView recyclerOdau;
    ProgressBar progressBar, progressBarLoading;
    NestedScrollView nestedScroll;
    SharedPreferences sharedPreferences;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.layout_fragment_odau, container, false);
        recyclerOdau = (RecyclerView) view.findViewById(R.id.recyclerOdau);
        recyclerOdau.setNestedScrollingEnabled(false);
        nestedScroll = (NestedScrollView) view.findViewById(R.id.nestScroll);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBarODau);
        progressBarLoading = (ProgressBar) view.findViewById(R.id.progress_bar);


        sharedPreferences = getContext().getSharedPreferences("toado", Context.MODE_PRIVATE);
        Location vitrihientai = new Location("");
        vitrihientai.setLatitude(Double.parseDouble(sharedPreferences.getString("latitude", "0")));
        vitrihientai.setLongitude(Double.parseDouble(sharedPreferences.getString("longitude", "0")));
        mPresenterOdau = new MyPresenterOdau(getContext());
        mPresenterOdau.getDanhSachQuanAnPresenter(nestedScroll, recyclerOdau, progressBar, view, vitrihientai);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
//        mPresenterOdau.getDanhSachQuanAnPresenter(recyclerOdau,progressBar, progressBarLoading, view, vitrihientai);

    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
