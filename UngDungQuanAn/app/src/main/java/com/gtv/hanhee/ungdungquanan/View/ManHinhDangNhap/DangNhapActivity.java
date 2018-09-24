package com.gtv.hanhee.ungdungquanan.View.ManHinhDangNhap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.gtv.hanhee.ungdungquanan.Presenter.ManHinhDangNhap.MyPresenterDangNhap;
import com.gtv.hanhee.ungdungquanan.Presenter.ManHinhDangNhap.PresenterDangNhap;
import com.gtv.hanhee.ungdungquanan.R;
import com.gtv.hanhee.ungdungquanan.Utils.XoaFocus;
import com.gtv.hanhee.ungdungquanan.View.ManHinhDangKy.DangKyActivity;
import com.gtv.hanhee.ungdungquanan.View.ManHinhQuenMatKhau.QuenMatKhauActivity;
import com.gtv.hanhee.ungdungquanan.View.ManHinhTrangChu.TrangChuActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DangNhapActivity extends AppCompatActivity implements ViewDangNhap, FirebaseAuth.AuthStateListener, View.OnClickListener {

    public static Activity dnActivity;
    public static int REQUESTCODE_DN_GOOGLE = 99;
    RelativeLayout layoutDN;
    PresenterDangNhap mPresenter;
    Button btnDangnhap, btnGoogleDangnhap;
    Button btnFacebookDangnhap;
    EditText edtEmailDN, edtPasswordDN;
    FirebaseAuth firebaseAuth;
    GoogleSignInOptions gso;
    LoginManager loginManager;
    CallbackManager callbackManager;
    TextView txtDangkymoi, txtQuenmatkhau;
    int KIEMTRA_PROVIDER_DN = 0;
    List<String> permissionFb = Arrays.asList("email", "public_profile");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        firebaseAuth = FirebaseAuth.getInstance();
        LoginManager.getInstance().logOut();
        loginManager = LoginManager.getInstance();
        firebaseAuth.signOut();
        dnActivity = this;

        mPresenter = new MyPresenterDangNhap(this);
        mPresenter.XuLyDuLieu();
        new XoaFocus().XoaFocusLayout(this, layoutDN);

    }

    @Override
    public void AddControls() {
        btnDangnhap = (Button) findViewById(R.id.btnDangnhap);
        btnGoogleDangnhap = (Button) findViewById(R.id.btnDangNhapGoogle);
        btnFacebookDangnhap = (Button) findViewById(R.id.btnDangnhapFacebook);
        edtEmailDN = (EditText) findViewById(R.id.edtEmailDN);
        edtPasswordDN = (EditText) findViewById(R.id.edtPasswordDN);
        txtDangkymoi = (TextView) findViewById(R.id.txtDangkymoi);
        txtQuenmatkhau = (TextView) findViewById(R.id.txtQuenmatkhau);
        layoutDN = (RelativeLayout) findViewById(R.id.LayoutDN);
        btnDangnhap.setOnClickListener(this);
        btnGoogleDangnhap.setOnClickListener(this);
        btnFacebookDangnhap.setOnClickListener(this);
        txtDangkymoi.setOnClickListener(this);
        txtQuenmatkhau.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDangnhap:
                String email = edtEmailDN.getText().toString();
                String password = edtPasswordDN.getText().toString();
                Dangnhap(email, password);
                break;
            case R.id.btnDangnhapFacebook:
                DangnhapFb();
                break;
            case R.id.btnDangNhapGoogle:
                DangnhapGoogle();
                break;
            case R.id.txtDangkymoi:
                Intent iDangky = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(iDangky);
                break;
            case R.id.txtQuenmatkhau:
                Intent iQuenMK = new Intent(DangNhapActivity.this, QuenMatKhauActivity.class);
                startActivity(iQuenMK);
                break;

        }
    }

    public void DangnhapGoogle() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(DangNhapActivity.this, gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, REQUESTCODE_DN_GOOGLE);
    }

    public void DangnhapFb() {
        callbackManager = CallbackManager.Factory.create();
        loginManager.logInWithReadPermissions(DangNhapActivity.this, permissionFb);
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                KIEMTRA_PROVIDER_DN = 2;
                ChungThucDangNhapFacebook(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
            }
        });
    }

    public void Dangnhap(String email, String password) {
        layoutDN.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
        }
        if (email.length() > 0 && password.length() > 0) {
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(DangNhapActivity.this, getString(R.string.dangnhapthatbai), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        } else {
            Toast.makeText(DangNhapActivity.this, getString(R.string.trongthongtin), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE_DN_GOOGLE) {
            KIEMTRA_PROVIDER_DN = 1;
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                ChungThucDangNhapGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void ChungThucDangNhapGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = firebaseAuth.getCurrentUser();
                        } else {
                        }
                    }
                });
    }

    private void ChungThucDangNhapFacebook(AccessToken tokenID) {
        AuthCredential credential = FacebookAuthProvider.getCredential(tokenID.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                        } else {

                        }
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            finish();
            Intent iTrangchu = new Intent(DangNhapActivity.this, TrangChuActivity.class);
            startActivity(iTrangchu);

        }
    }


}
