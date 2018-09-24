package com.gtv.hanhee.ungdungquanan.Presenter.ManHinhTrangChu;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gtv.hanhee.ungdungquanan.Model.ThanhVienModel;

public class ThongTinUserPresenter {
    ThanhVienModel thanhVienModel;
    DatabaseReference dataNodeThanhvien;

    public ThongTinUserPresenter() {
        thanhVienModel = new ThanhVienModel();

    }

    public void ThemThongTinThanhVienPresenter(ThanhVienModel thanhVienModel, String uid) {
        dataNodeThanhvien = FirebaseDatabase.getInstance().getReference().child("thanhviens");
        dataNodeThanhvien.child(uid).setValue(thanhVienModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
    }
}
