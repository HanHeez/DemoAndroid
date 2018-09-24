package com.gtv.hanhee.firebaseauthentication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuth;
    EditText edtEmail, edtPassword;
    Button btnDangnhap,btnResetPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnDangnhap = (Button) findViewById(R.id.btnDangnhap);
        btnResetPassword = (Button) findViewById(R.id.btnResetPassword);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                            Intent iUpdateUser = new Intent(MainActivity.this,UpdateUserActivity.class);
                            startActivity(iUpdateUser);
                        } else {
                            Toast.makeText(MainActivity.this, "Dang nhap that bai", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.sendPasswordResetEmail("luhansmilex@gmail.com").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Gui email lay lai pass thanh cong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


        mAuth = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth != null) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user != null) {



//                        String name = user.getDisplayName();
//                        String email = user.getEmail();
//                        Uri photoUrl = user.getPhotoUrl();
//
//                        // Check if user's email is verified
//                        boolean emailVerified = user.isEmailVerified();
//
//                        // The user's ID, unique to the Firebase project. Do NOT use this value to
//                        // authenticate with your backend server, if you have one. Use
//                        // FirebaseUser.getIdToken() instead.
//                        String uid = user.getUid();
                    }

                }
            }
        };

//        firebaseAuth.createUserWithEmailAndPassword("long@gmail.com","123457").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, "Tao tai khoan thanh cong", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuth);

    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(mAuth);
    }
}
