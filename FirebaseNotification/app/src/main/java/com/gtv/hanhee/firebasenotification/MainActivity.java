package com.gtv.hanhee.firebasenotification;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    ImageView imgView;
    InputStream inputStream = null;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView = (ImageView) findViewById(R.id.imgView);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        CodeUpload();
        CodeDownload();


    }

    private void CodeDownload() {
        storageReference = firebaseStorage.getReference().child("photos/hinhgaidep.jpg");

        long megabytes = 1024 * 1024;
//        storageReference.getBytes(megabytes).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//            @Override
//            public void onSuccess(byte[] bytes) {
//                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
//                imgView.setImageBitmap(bitmap);
//            }
//        });

        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                //Dung thu vien Picasso

            }
        });

    }

    private void CodeUpload() {
        StorageReference store = storageReference.child("image").child("girl1.jpg");
        Log.d("kiem tra",store.getPath());


        Bitmap bitmap = ((BitmapDrawable) imgView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] data = byteArrayOutputStream.toByteArray();

        storageReference = firebaseStorage.getReference().child("photos").child("aa.jpg");

        storageReference.putBytes(data);
//
//        UploadTask uploadTask = mountainsRef.putBytes(data);
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle unsuccessful uploads
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
//                // ...
//            }
//        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    inputStream = new URL("http://giadinh.vcmedia.vn/k:2016/g13-1453447925662-crop-1453448145002/nhung-my-nhan-tuoi-than-so-huu-than-hinh-goi-cam-nhat-showbiz-viet.jpg").openStream();
                    storageReference = firebaseStorage.getReference().child("photos").child("hinhgaidep.jpg");
                    storageReference.putStream(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
