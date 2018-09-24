package com.gtv.hanhee.ungdungquanan.View.ManHinhQuenMatKhau;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.gtv.hanhee.ungdungquanan.R;
import com.gtv.hanhee.ungdungquanan.Utils.XoaFocus;
import com.gtv.hanhee.ungdungquanan.View.ManHinhDangKy.DangKyActivity;
import com.gtv.hanhee.ungdungquanan.View.ManHinhDangNhap.DangNhapActivity;

public class QuenMatKhauActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnGuiemail;
    TextView txtSaiemail, txtThongbao, txtDangkymoi;
    EditText edtEmailQuenMK;
    RelativeLayout layoutQmk;
    LinearLayout line2;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quenmatkhau);
        AddControls();

        new XoaFocus().XoaFocusLayout(this, layoutQmk);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ViewGroup rootView = (ViewGroup) findViewById(R.id.line2);
            LayoutTransition layoutTransition = rootView.getLayoutTransition();
            layoutTransition.setDuration(250);
            layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
        }

        AddEvents();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void AddEvents() {
        edtEmailQuenMK.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                txtThongbao.setVisibility(View.INVISIBLE);
                return false;
            }
        });

        edtEmailQuenMK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtThongbao.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void AddControls() {
        btnGuiemail = (Button) findViewById(R.id.btnGuiemail);
        txtSaiemail = (TextView) findViewById(R.id.txtSaiEmail);
        txtThongbao = (TextView) findViewById(R.id.txtThongbao);
        edtEmailQuenMK = (EditText) findViewById(R.id.edtEmailQuenMK);
        txtDangkymoi = (TextView) findViewById(R.id.txtDangkyMoi);
        layoutQmk = (RelativeLayout) findViewById(R.id.layoutQMK);
        btnGuiemail.setOnClickListener(this);
        txtDangkymoi.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this, R.style.NewDialog);
        progressDialog.setCanceledOnTouchOutside(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGuiemail:
                String email = edtEmailQuenMK.getText().toString();
                boolean kiemtraemail = KiemtraEmail(email);
                if (kiemtraemail) {
                    progressDialog.show();
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(QuenMatKhauActivity.this, "Gửi Email thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                progressDialog.dismiss();
                                txtThongbao.setVisibility(View.VISIBLE);
                                txtThongbao.setText(getString(R.string.emailkhongtontai));
                            }
                        }
                    });

                } else {
                    txtThongbao.setVisibility(View.VISIBLE);
                    txtThongbao.setText(getString(R.string.thongbaoemailkhonghople));

                }
                break;
            case R.id.txtDangkyMoi:
                finish();
                DangNhapActivity.dnActivity.finish();
                Intent iDangky = new Intent(QuenMatKhauActivity.this, DangKyActivity.class);
                startActivity(iDangky);
                break;

        }
    }

    private boolean KiemtraEmail(String email) {

        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
