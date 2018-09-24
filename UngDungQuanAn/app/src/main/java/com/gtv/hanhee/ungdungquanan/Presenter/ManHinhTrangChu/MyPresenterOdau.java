package com.gtv.hanhee.ungdungquanan.Presenter.ManHinhTrangChu;

import android.content.Context;
import android.location.Location;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gtv.hanhee.ungdungquanan.Adapter.AdapterRecyclerOdau;
import com.gtv.hanhee.ungdungquanan.Model.BinhLuanModel;
import com.gtv.hanhee.ungdungquanan.Model.ChiNhanhQuanAnModel;
import com.gtv.hanhee.ungdungquanan.Model.QuanAnModel;
import com.gtv.hanhee.ungdungquanan.Model.ThanhVienModel;
import com.gtv.hanhee.ungdungquanan.R;
import com.gtv.hanhee.ungdungquanan.View.Fragments.OdauFragment;

import java.util.ArrayList;
import java.util.List;

public class MyPresenterOdau {

    Context context;
    QuanAnModel quanAnModel;
    AdapterRecyclerOdau adapterRecyclerOdau;
    DatabaseReference nodeRoot;
    OdauFragment odauFragment;
    List<QuanAnModel> quanAnModelList;
    Location vitrihientai;
    int itemdaco = 3;
    private View viewhientai;
    private ProgressBar progressBarhientai;
    private DataSnapshot dataRoot;

    public MyPresenterOdau(Context context) {
        this.context = context;
        quanAnModel = new QuanAnModel();
        odauFragment = new OdauFragment();
    }

    public void getDanhSachQuanAnPresenter(NestedScrollView nestedScrollView, final RecyclerView recyclerView, final ProgressBar progressBar,
                                           final View view, final Location vitrihientai) {


        viewhientai = view;
        this.vitrihientai = vitrihientai;
        progressBarhientai = progressBar;

        quanAnModelList = new ArrayList<>();
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setAutoMeasureEnabled(true);

        adapterRecyclerOdau = new AdapterRecyclerOdau(context, quanAnModelList, R.layout.custom_recycleview_odau);
        recyclerView.setAdapter(adapterRecyclerOdau);


        nodeRoot = FirebaseDatabase.getInstance().getReference();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataRoot = dataSnapshot;
                LayDanhSachQuanAn(dataRoot, vitrihientai, 3, 0);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        nodeRoot.addListenerForSingleValueEvent(valueEventListener);

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (v.getChildAt(0) != null) {
                    if ((scrollY >= (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) &&
                            scrollY > oldScrollY) {
                        itemdaco += 3;
                        LayDanhSachQuanAn(dataRoot, vitrihientai, itemdaco, itemdaco - 3);
                    }
                }

            }
        });
    }

    private void LayDanhSachQuanAn(DataSnapshot dataSnapshot, Location vitrihientai, int slItemCanHienThi, int slItemdaco) {

        DataSnapshot dataSnapshotQuanan = dataSnapshot.child("quanans");
        //Lấy danh sách quán ăn

        int vitriitemHientai = 0;
        for (DataSnapshot valueQuanAn : dataSnapshotQuanan.getChildren()) {
            if (vitriitemHientai == slItemCanHienThi) {
                break;
            }
            if (vitriitemHientai < slItemdaco) {
                vitriitemHientai++;
                continue;
            }

            vitriitemHientai++;
            quanAnModel = valueQuanAn.getValue(QuanAnModel.class);
            quanAnModel.setMaquanan(valueQuanAn.getKey());
            //Lấy hình ảnh của quán ăn
            DataSnapshot dataSnapshotHinhQA = dataSnapshot.child("hinhanhquanans").child(quanAnModel.getMaquanan());
            //quanAnModel.getMaquanan() là mã quán ăn
            List<String> hinhanhlist = new ArrayList<>();
            for (final DataSnapshot valueHinhQuanAn : dataSnapshotHinhQA.getChildren()) {
                hinhanhlist.add(valueHinhQuanAn.getValue(String.class));
            }
            //set hình ảnh
            quanAnModel.setHinhanhquanan(hinhanhlist);

            //Lấy danh sách bình luận quán ăn
            DataSnapshot dataSnapshotBinhluan = dataSnapshot.child("binhluans").child(quanAnModel.getMaquanan());
            List<BinhLuanModel> binhLuanModels = new ArrayList<>();
            for (DataSnapshot valueBinhluan : dataSnapshotBinhluan.getChildren()) {
                BinhLuanModel binhLuanModel = valueBinhluan.getValue(BinhLuanModel.class);
                binhLuanModel.setMabinhluan(valueBinhluan.getKey());

                //Lấy hình ảnh và họ tên thành viên
                ThanhVienModel thanhVienModel = dataSnapshot.child("thanhviens")
                        .child(binhLuanModel.getMauser()).getValue(ThanhVienModel.class);
                binhLuanModel.setThanhVienModel(thanhVienModel);

                List<String> hinhanhBLList = new ArrayList<>();
                DataSnapshot dataSnapshotHinhanhBl = dataSnapshot.child("hinhanhbinhluans").child(binhLuanModel.getMabinhluan());
                //Lấy hình ảnh BL
                for (DataSnapshot valueHinhAnhBL : dataSnapshotHinhanhBl.getChildren()) {
                    hinhanhBLList.add(valueHinhAnhBL.getValue(String.class));
                }
                binhLuanModel.setListhinhanhBL(hinhanhBLList);
                binhLuanModels.add(binhLuanModel);
            }
            //set bình luận
            quanAnModel.setBinhluanlist(binhLuanModels);

            //Lấy chi nhánh quán ăn
            DataSnapshot dataSnapshotChiNhanhQA = dataSnapshot.child("chinhanhquanans").child(quanAnModel.getMaquanan());
            List<ChiNhanhQuanAnModel> chiNhanhQuanAnModels = new ArrayList<>();

            for (DataSnapshot valueChiNhanhQA : dataSnapshotChiNhanhQA.getChildren()) {
                ChiNhanhQuanAnModel chiNhanhQuanAnModel = valueChiNhanhQA.getValue(ChiNhanhQuanAnModel.class);
                Location vitriquanan = new Location("");
                vitriquanan.setLatitude(chiNhanhQuanAnModel.getLatitude());
                vitriquanan.setLongitude(chiNhanhQuanAnModel.getLongitude());

                double khoangcach = vitrihientai.distanceTo(vitriquanan) / 1000;
                chiNhanhQuanAnModel.setKhoangcach(khoangcach);
                chiNhanhQuanAnModels.add(chiNhanhQuanAnModel);
            }
            //set chi nhánh quán ăn
            quanAnModel.setChiNhanhQuanAnModelList(chiNhanhQuanAnModels);

            quanAnModelList.add(quanAnModel);
            adapterRecyclerOdau.notifyDataSetChanged();
            progressBarhientai.setVisibility(viewhientai.GONE);

        }
    }


//    @Override
//    public void LoadMore(int tongitem) {
//        progressBarloadingHientai.setVisibility(viewhientai.VISIBLE);
//        tongitem += 3;
//        LayDanhSachQuanAn(dataRoot, vitrihientai, tongitem, tongitem - 3);
//    }


}
