package com.gtv.hanhee.ungdungquanan.View.ManHinhChiTietBinhLuan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.gtv.hanhee.ungdungquanan.Adapter.AdapterRecyclerHinhBinhLuan;
import com.gtv.hanhee.ungdungquanan.Adapter.GlideApp;
import com.gtv.hanhee.ungdungquanan.Model.BinhLuanModel;
import com.gtv.hanhee.ungdungquanan.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChiTietBinhLuanActivity extends AppCompatActivity {

    CircleImageView circleImageUser;
    TextView txtTieudebinhluan;
    TextView txtNoidungbinhluan;
    TextView txtChamdiembinhluan;
    RecyclerView recyclerHinhBL;
    BinhLuanModel binhLuanModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_layout_binhluan);
        Bundle b = getIntent().getBundleExtra("MY_PARAM");
        binhLuanModel = b.getParcelable("binhluanmodel");
        AddControls();
        AddEvents();
    }

    private void AddEvents() {
        txtTieudebinhluan.setText(binhLuanModel.getTieude());
        txtNoidungbinhluan.setText(binhLuanModel.getNoidung());
        txtChamdiembinhluan.setText(binhLuanModel.getChamdiem() + "");
        setHinhAnhBinhLuan(circleImageUser, binhLuanModel.getThanhVienModel().getHinhanh());
        AdapterRecyclerHinhBinhLuan adapterRecyclerHinhBinhLuan =
                new AdapterRecyclerHinhBinhLuan(this, R.layout.custom_layout_hinhbinhluan, binhLuanModel.getListhinhanhBL(), binhLuanModel, true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ChiTietBinhLuanActivity.this, 2);
        recyclerHinhBL.setLayoutManager(layoutManager);
        recyclerHinhBL.setAdapter(adapterRecyclerHinhBinhLuan);
        adapterRecyclerHinhBinhLuan.notifyDataSetChanged();
    }

    private void AddControls() {
        circleImageUser = (CircleImageView) findViewById(R.id.cimageUser);
        txtTieudebinhluan = (TextView) findViewById(R.id.txtTieudebinhluan);
        txtNoidungbinhluan = (TextView) findViewById(R.id.txtNoidungbinhluan);
        txtChamdiembinhluan = (TextView) findViewById(R.id.txtChamdiembinhluan);
        recyclerHinhBL = (RecyclerView) findViewById(R.id.recyclerHinhBL);

    }

    private void setHinhAnhBinhLuan(final CircleImageView circleImageView, String linkhinh) {
        StorageReference storageHinhanh = FirebaseStorage.getInstance().getReference()
                .child("thanhvien").child(linkhinh);
        GlideApp.with(this).load(storageHinhanh).transition(new DrawableTransitionOptions().crossFade(200)).into(circleImageView);

    }

}
