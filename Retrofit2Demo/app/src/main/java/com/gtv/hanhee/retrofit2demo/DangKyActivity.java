package com.gtv.hanhee.retrofit2demo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gtv.hanhee.retrofit2demo.Retrofit2.APIUtils;
import com.gtv.hanhee.retrofit2demo.Retrofit2.DataClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyActivity extends AppCompatActivity {

    ImageView imgDangky;
    EditText edtUsername,edtPassword;
    Button btnHuy, btnXacnhan;
    int REQUEST_CODE_IMAGE = 123;
    String realpath = "";
    String taikhoan ="" ;
    String matkhau = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        imgDangky = findViewById(R.id.imgDangky);
        edtUsername = findViewById(R.id.edtDangkyTK);
        edtPassword = findViewById(R.id.edtDangkyPassword);
        btnHuy = findViewById(R.id.btnHuy);
        btnXacnhan = findViewById(R.id.btnXacnhan);

        imgDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_IMAGE);
            }
        });

        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                taikhoan = edtUsername.getText().toString();
                matkhau = edtPassword.getText().toString();

                if (taikhoan.length()>0 && matkhau.length()>0) {
                    File file = new File(realpath);
                    String file_path = file.getAbsolutePath();
                    String[] mangtenfile = file_path.split("\\.");
                    file_path = mangtenfile[0] + System.currentTimeMillis()+"."+mangtenfile[1];
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file",file_path,requestBody);

                    DataClient dataClient = APIUtils.getData();
                    Call<String> callback = dataClient.UploadPhoto(body);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response != null) {
                                String message = response.body();
                                if (message.length()>0) {
                                    DataClient insertdata = APIUtils.getData();
                                    Call<String> callback = insertdata.InsertData(taikhoan,matkhau,APIUtils.Base_url+"image/"+message);
                                    callback.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            String result = response.body();
                                            if (result.equals("success")) {
                                                Toast.makeText(DangKyActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {

                                        }
                                    });
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                } else {
                    Toast.makeText(DangKyActivity.this, "Hay nhap du thong tin", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            realpath = getRealPathFromURI(uri);

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgDangky.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }
}
