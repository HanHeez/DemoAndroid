package com.gtv.hanhee.ungdungquanan.Presenter.ManHinhDangKy;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.gtv.hanhee.ungdungquanan.View.ManHinhDangKy.ViewDangKy;

public class MyPresenterDangKy implements PresenterDangKy {

    ViewDangKy mView;
    FirebaseAuth firebaseAuth;

    public MyPresenterDangKy(ViewDangKy mView) {
        this.mView = mView;
    }


    @Override
    public void XulyDangky() {
        mView.AddControls();
        mView.AddEvents();
    }

    @Override
    public void KiemtraEmail(String email) {
        int loi = 0;
        if (email.trim().length() <= 0) {
            loi = 1;
        } else if (email.indexOf("@") < 1) {
            loi = 2;
        } else if (email.indexOf(".") < 3) {
            loi = 2;
        } else if (email.indexOf(".") != email.lastIndexOf(".")) {
            loi = 2;
        } else if (email.trim().length() < 5) {
            loi = 2;
        } else if (email.indexOf(" ") >= 0) {
            loi = 2;
        }
        mView.XulyLoiEmail(loi);
    }

    @Override
    public void KiemtraPassword(String password) {
        int loi = 0;
        if (password.length() <= 0) {
            loi = 1;
        } else if (password.length() <= 5) {
            loi = 2;
        } else if (password.length() > 50) {
            loi = 2;
        }
        mView.XulyLoiPassword(loi);
    }

    @Override
    public void KiemtraNhaplaiPassword(String password, String nhaplaipassword) {
        int loi = 0;
        if (nhaplaipassword.length() <= 0) {
            loi = 1;
        } else if (nhaplaipassword.equals(password) == false) {
            loi = 2;
        }
        mView.XulyLoiNhaplaiPassword(loi);
    }

    @Override
    public void TaotaikhoanDN(String email, String password) {
        final String email1 = email;
        firebaseAuth = firebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mView.XulyDNThanhcong();
                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthUserCollisionException malformedEmail) {
                                Log.d("kiem tra", malformedEmail.toString());
                                mView.XulyLoiEmail(3);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }
}
