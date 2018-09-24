package com.gtv.hanhee.firebaseauthentication;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class UpdateUserActivity extends AppCompatActivity {

    Button btnUpdateuser;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_update);

        firebaseAuth = FirebaseAuth.getInstance();
        btnUpdateuser = (Button) findViewById(R.id.btnUpdateuser);


        Toast.makeText(this, firebaseAuth.getCurrentUser().getDisplayName()+"", Toast.LENGTH_SHORT).show();

        btnUpdateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                        .setDisplayName("GTV HanHee")
                        .setPhotoUri(Uri.parse("http://nguonanhdep.com/wp-content/uploads/2017/10/avatar-hoat-hinh-chibi-de-thuong-10.jpg"))
                        .build();
                user.updateProfile(profileUpdate);
            }
        });

    }
}
