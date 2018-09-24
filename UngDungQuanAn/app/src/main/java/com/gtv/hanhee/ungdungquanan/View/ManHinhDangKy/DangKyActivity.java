package com.gtv.hanhee.ungdungquanan.View.ManHinhDangKy;

import android.animation.LayoutTransition;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gtv.hanhee.ungdungquanan.Model.ThanhVienModel;
import com.gtv.hanhee.ungdungquanan.Presenter.ManHinhDangKy.MyPresenterDangKy;
import com.gtv.hanhee.ungdungquanan.Presenter.ManHinhDangKy.PresenterDangKy;
import com.gtv.hanhee.ungdungquanan.Presenter.ManHinhTrangChu.ThongTinUserPresenter;
import com.gtv.hanhee.ungdungquanan.R;
import com.gtv.hanhee.ungdungquanan.Utils.XoaFocus;
import com.gtv.hanhee.ungdungquanan.View.ManHinhDangNhap.DangNhapActivity;
import com.gtv.hanhee.ungdungquanan.View.ManHinhTrangChu.TrangChuActivity;

import java.util.Objects;

public class DangKyActivity extends AppCompatActivity implements ViewDangKy, View.OnClickListener, View.OnTouchListener {

    PresenterDangKy mPresenter;
    EditText edtEmailDK, edtPasswordDK, edtNhaplaiPasswordDK;
    TextView txtCheckEmailDK, txtCheckPasswordDK, txtCheckNhaplaiPassworDK;
    Button btnDangky;
    RelativeLayout layoutDk;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    int loiDN = -1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);
        mPresenter = new MyPresenterDangKy(this);

        mPresenter.XulyDangky();
        btnDangky.setOnClickListener(this);
        edtEmailDK.setOnTouchListener(this);
        edtPasswordDK.setOnTouchListener(this);
        edtNhaplaiPasswordDK.setOnTouchListener(this);

        new XoaFocus().XoaFocusLayout(this, layoutDk);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ViewGroup rootView = (ViewGroup) findViewById(R.id.line1);
            LayoutTransition layoutTransition = rootView.getLayoutTransition();
            layoutTransition.setDuration(250);
            layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
        }
    }

    @Override
    public void AddControls() {
        edtEmailDK = (EditText) findViewById(R.id.edtEmailDK);
        edtPasswordDK = (EditText) findViewById(R.id.edtPasswordDK);
        edtNhaplaiPasswordDK = (EditText) findViewById(R.id.edtNhaplaiPasswordDK);
        btnDangky = (Button) findViewById(R.id.btnDangKy);
        txtCheckEmailDK = (TextView) findViewById(R.id.txtCheckEmailDK);
        txtCheckPasswordDK = (TextView) findViewById(R.id.txtCheckPasswordDK);
        txtCheckNhaplaiPassworDK = (TextView) findViewById(R.id.txtCheckNhaplaiPasswordDK);
        txtCheckEmailDK.setVisibility(View.INVISIBLE);
        txtCheckNhaplaiPassworDK.setVisibility(View.INVISIBLE);
        txtCheckPasswordDK.setVisibility(View.INVISIBLE);
        layoutDk = (RelativeLayout) findViewById(R.id.LayoutDK);
        progressDialog = new ProgressDialog(this, R.style.NewDialog);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void AddEvents() {
    }

    @Override
    public void XulyLoiEmail(int loi) {
        if (loi == 1) {
            txtCheckEmailDK.setVisibility(View.VISIBLE);
            txtCheckEmailDK.setText(getString(R.string.loitrongthongtin));
        } else if (loi == 2) {
            txtCheckEmailDK.setVisibility(View.VISIBLE);
            txtCheckEmailDK.setText(getString(R.string.loiemail));
        } else if
                (loi == 3) {
            txtCheckEmailDK.setVisibility(View.VISIBLE);
            progressDialog.dismiss();
            txtCheckEmailDK.setText(getString(R.string.loiemailtontai));
        } else {
            txtCheckEmailDK.setVisibility(View.INVISIBLE);
        }
        loiDN = loi;
    }

    @Override
    public void XulyLoiPassword(int loi) {
        if (loi == 1) {
            txtCheckPasswordDK.setVisibility(View.VISIBLE);
            txtCheckPasswordDK.setText(getString(R.string.loitrongthongtin));
        } else if (loi == 2) {
            txtCheckPasswordDK.setVisibility(View.VISIBLE);
            txtCheckPasswordDK.setText(getString(R.string.loiPass2));
        } else {
            txtCheckPasswordDK.setVisibility(View.INVISIBLE);
        }
        loiDN = loi;
    }

    @Override
    public void XulyLoiNhaplaiPassword(int loi) {
        if (loi == 1) {
            txtCheckNhaplaiPassworDK.setVisibility(View.VISIBLE);
            txtCheckNhaplaiPassworDK.setText(getString(R.string.loitrongthongtin));
        } else if (loi == 2) {
            txtCheckNhaplaiPassworDK.setVisibility(View.VISIBLE);
            txtCheckNhaplaiPassworDK.setText(getString(R.string.loiPass));
        } else {
            txtCheckNhaplaiPassworDK.setVisibility(View.INVISIBLE);
        }
        loiDN = loi;
    }

    @Override
    public void XulyDNThanhcong() {
        progressDialog.dismiss();
        String email = edtEmailDK.getText().toString();
        String password = edtPasswordDK.getText().toString();

        ThanhVienModel thanhVienModel = new ThanhVienModel();
        thanhVienModel.setHoten(email);
        thanhVienModel.setHinhanh("user_troll.jpg");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        ThongTinUserPresenter thongTinUserPresenter = new ThongTinUserPresenter();
        thongTinUserPresenter.ThemThongTinThanhVienPresenter(thanhVienModel, uid);
        if (email.length() > 0 && password.length() > 0) {
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signInWithEmailAndPassword(email, password);
            finish();
            DangNhapActivity.dnActivity.finish();
            Intent iTrangchu = new Intent(DangKyActivity.this, TrangChuActivity.class);
            startActivity(iTrangchu);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDangKy:
                layoutDk.clearFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
                }
                String email = edtEmailDK.getText().toString();
                String password = edtPasswordDK.getText().toString();
                String nhaplaipassword = edtNhaplaiPasswordDK.getText().toString();
                mPresenter.KiemtraEmail(email);
                mPresenter.KiemtraPassword(password);
                mPresenter.KiemtraNhaplaiPassword(password, nhaplaipassword);
                if (loiDN == 0) {
                    progressDialog.show();
                    mPresenter.TaotaikhoanDN(email, password);
                }
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.edtEmailDK:
                txtCheckEmailDK.setVisibility(View.INVISIBLE);
            case R.id.edtPasswordDK:
                txtCheckPasswordDK.setVisibility(View.INVISIBLE);
            case R.id.edtNhaplaiPasswordDK:
                txtCheckNhaplaiPassworDK.setVisibility(View.INVISIBLE);
        }
        return false;
    }
}
