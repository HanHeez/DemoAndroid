package com.gtv.hanhee.ungdungquanan.View.ThemQuanAn;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.gtv.hanhee.ungdungquanan.Model.ChiNhanhQuanAnModel;
import com.gtv.hanhee.ungdungquanan.Model.MonAnModel;
import com.gtv.hanhee.ungdungquanan.Model.QuanAnModel;
import com.gtv.hanhee.ungdungquanan.Model.ThemThucDonModel;
import com.gtv.hanhee.ungdungquanan.Model.ThucDonModel;
import com.gtv.hanhee.ungdungquanan.Model.TienIchModel;
import com.gtv.hanhee.ungdungquanan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThemQuanAnActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    final int RESULT_IMG1 = 11;
    final int RESULT_IMG2 = 12;
    final int RESULT_IMG3 = 13;
    final int RESULT_IMG4 = 14;
    final int RESULT_IMG5 = 15;
    final int RESULT_IMG6 = 16;
    final int RESULT_IMGTHUCDON = 17;
    final int RESULT_VIDEO = 20;

    Button btnGioMoCua, btnGioDongCua,btnThemQuanAn;
    String giomocua,giodongcua;
    Spinner spinnerKhuvuc;

    EditText edtTenQuan,edtGiaToiDa,edtGiaToiThieu;

    List<ThucDonModel> thucDonModelList;
    List<String> khuvucList,thucdonList;
    List<String> tienIchList;
    List<String> chiNhanhList;
    List<ThemThucDonModel> themThucDonModelList;
    List<Bitmap> hinhDaChup;
    List<Uri> hinhQuanAn;
    ArrayAdapter<String> adapterKhuvuc;
    LinearLayout themkhungtienich,khungChuaChiNhanh,khungChuaThucDon;
    ImageView imgTam;
    ImageView imgHinhQuan1,imgHinhQuan2,imgHinhQuan3,imgHinhQuan4,imgHinhQuan5,imgHinhQuan6,imgVideo;
    VideoView vdVideo;
    Uri videoSelected;
    RadioGroup rdgTrangThai;
    String khuvuc;
    String maquanan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themquanan);
        btnGioMoCua = (Button) findViewById(R.id.btnGioMoCua);
        btnGioDongCua = (Button) findViewById(R.id.btnGioDongCua);
        btnThemQuanAn = (Button) findViewById(R.id.btnThemQuanAn);

        edtTenQuan = (EditText) findViewById(R.id.edtTenQuan);
        edtGiaToiDa = (EditText) findViewById(R.id.edtGiaToiDa);
        edtGiaToiThieu = (EditText) findViewById(R.id.edtGiaToiThieu);

        rdgTrangThai = (RadioGroup) findViewById(R.id.rdgTrangThai);
        spinnerKhuvuc = (Spinner) findViewById(R.id.spinnerKhuvuc);
        themkhungtienich = (LinearLayout) findViewById(R.id.themkhungtienich);
        khungChuaChiNhanh = (LinearLayout) findViewById(R.id.khungChuaChiNhanh);
        khungChuaThucDon = (LinearLayout) findViewById(R.id.khungChuaThucDon);

        imgHinhQuan1 = (ImageView) findViewById(R.id.imgHinhQuan1);
        imgHinhQuan2 = (ImageView) findViewById(R.id.imgHinhQuan2);
        imgHinhQuan3 = (ImageView) findViewById(R.id.imgHinhQuan3);
        imgHinhQuan4 = (ImageView) findViewById(R.id.imgHinhQuan4);
        imgHinhQuan5 = (ImageView) findViewById(R.id.imgHinhQuan5);
        imgHinhQuan6 = (ImageView) findViewById(R.id.imgHinhQuan6);
        imgVideo = (ImageView) findViewById(R.id.imgVideo);
        vdVideo = (VideoView) findViewById(R.id.vdVideo);

        btnGioMoCua.setOnClickListener(this);
        btnGioDongCua.setOnClickListener(this);

        thucDonModelList = new ArrayList<>();
        thucdonList = new ArrayList<>();
        khuvucList = new ArrayList<>();
        tienIchList = new ArrayList<>();
        chiNhanhList = new ArrayList<>();
        themThucDonModelList = new ArrayList<>();
        hinhDaChup = new ArrayList<>();
        hinhQuanAn = new ArrayList<>();

        adapterKhuvuc = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,khuvucList);
        spinnerKhuvuc.setAdapter(adapterKhuvuc);
        adapterKhuvuc.notifyDataSetChanged();

        LayDanhSachKhuVuc();
        LayDanhSachTienIch();
        CloneChiNhanh();
        CloneThucDon();

        spinnerKhuvuc.setOnItemSelectedListener(this);
        imgHinhQuan1.setOnClickListener(this);
        imgHinhQuan2.setOnClickListener(this);
        imgHinhQuan3.setOnClickListener(this);
        imgHinhQuan4.setOnClickListener(this);
        imgHinhQuan5.setOnClickListener(this);
        imgHinhQuan6.setOnClickListener(this);
        btnThemQuanAn.setOnClickListener(this);
        imgVideo.setOnClickListener(this);

    }

    @Override
    public void onClick(final View v) {
        Calendar calendar = Calendar.getInstance();
        int gio = calendar.get(Calendar.HOUR_OF_DAY);
        int phut = calendar.get(Calendar.MINUTE);
        switch (v.getId()) {
            case R.id.btnGioMoCua:
                TimePickerDialog timePickerDialogMoCua = new TimePickerDialog(ThemQuanAnActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        giomocua = hourOfDay + ":" + minute;
                        ((Button) v).setText(giomocua);
                    }
                }, gio, phut, true);

                timePickerDialogMoCua.show();
                break;
            case R.id.btnGioDongCua:
                TimePickerDialog timePickerDialogDongCua = new TimePickerDialog(ThemQuanAnActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        giodongcua = hourOfDay + ":" + minute;
                        ((Button) v).setText(giodongcua);
                    }
                }, gio, phut, true);

                timePickerDialogDongCua.show();
                break;
            case R.id.imgHinhQuan1:
                ChonHinhTuGallery(RESULT_IMG1);
                break;
            case R.id.imgHinhQuan2:
                ChonHinhTuGallery(RESULT_IMG2);
                break;
            case R.id.imgHinhQuan3:
                ChonHinhTuGallery(RESULT_IMG3);
                break;
            case R.id.imgHinhQuan4:
                ChonHinhTuGallery(RESULT_IMG4);
                break;
            case R.id.imgHinhQuan5:
                ChonHinhTuGallery(RESULT_IMG5);
                break;
            case R.id.imgHinhQuan6:
                ChonHinhTuGallery(RESULT_IMG6);
                break;
            case R.id.imgVideo:
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Chọn video..."),RESULT_VIDEO);
                break;
            case R.id.btnThemQuanAn:
                ThemQuanAn();
                break;
        }
    }



    private void ThemQuanAn() {
        String tenquanan = edtTenQuan.getText().toString();
        String textgiatoida = edtGiaToiDa.getText().toString();
        String textgiatoithieu = edtGiaToiThieu.getText().toString();
        long giatoida = 0, giatoithieu = 0;
        if (textgiatoida.trim().length()>0 ) {
            giatoida = Long.parseLong(textgiatoida);
        }
        if (textgiatoithieu.trim().length()>0 ) {
            giatoithieu = Long.parseLong(textgiatoithieu);
        }
        int idRadioSelected = rdgTrangThai.getCheckedRadioButtonId();
        boolean giaohang = false;
        if (idRadioSelected==R.id.rdGiaoHang) {
            giaohang = true;
        } else { giaohang = false; }


        DatabaseReference nodeRoot = FirebaseDatabase.getInstance().getReference();
        DatabaseReference nodeQuanan = nodeRoot.child("quanans");

        maquanan = nodeQuanan.push().getKey();

        nodeRoot.child("khuvucs").child(khuvuc).push().setValue(maquanan);


        for (String chinhanh: chiNhanhList) {
            String urlGeoCoding = "https://maps.googleapis.com/maps/api/geocode/json?address="+chinhanh.replace(" ","%20")+"&key=AIzaSyC0hf94OvKZD7ni-RDQwtDp5XVby6w3TuA";


            DownloadToaDo downloadToaDo = new DownloadToaDo();
            downloadToaDo.execute(urlGeoCoding);
        }


        QuanAnModel quanAnModel = new QuanAnModel();
        quanAnModel.setTenquanan(tenquanan);
        quanAnModel.setGiatoida(giatoida);
        quanAnModel.setGiatoithieu(giatoithieu);
        quanAnModel.setGiaohang(giaohang);
        quanAnModel.setGiodongcua(giodongcua);
        quanAnModel.setGiomocua(giomocua);
//        quanAnModel.setVideogioithieu(videoSelected.getLastPathSegment());
        quanAnModel.setTienich(tienIchList);
        quanAnModel.setLuotthich(0);
        nodeQuanan.child(maquanan).setValue(quanAnModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ThemQuanAnActivity.this, "Thêm quán ăn thành công", Toast.LENGTH_SHORT).show();
            }
        });

//        FirebaseStorage.getInstance().getReference().child("video/"+videoSelected.getLastPathSegment()).putFile(videoSelected);
        for (int i = 0;i<hinhQuanAn.size();i++) {
            FirebaseStorage.getInstance().getReference().child("hinhanhquanan/"+hinhQuanAn.get(i).getLastPathSegment()).putFile(hinhQuanAn.get(i));
            nodeRoot.child("hinhanhquanans").child(maquanan).push().setValue(hinhQuanAn.get(i).getLastPathSegment());
        }

        for (int i = 0; i<themThucDonModelList.size();i++) {
            nodeRoot.child("thucdonquanans").child(maquanan).child(themThucDonModelList.get(i).getMathucdon()).push().setValue(themThucDonModelList.get(i).getMonAnModel());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Bitmap bitmap = hinhDaChup.get(i);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();

            FirebaseStorage.getInstance().getReference().child("hinhanh/"+themThucDonModelList.get(i).getMonAnModel().getHinhanh()).putBytes(data);
        }


    }

    class DownloadToaDo extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = bufferedReader.readLine())!=null) {
                    stringBuilder.append(line+"\n");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString() ;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray results = jsonObject.getJSONArray("results");

                for (int i = 0; i < results.length(); i++) {
                    JSONObject object = results.getJSONObject(i);
                    String address = object.getString("formatted_address");
                    JSONObject geometry = object.getJSONObject("geometry");
                    JSONObject location = geometry.getJSONObject("location");
                    double latitude = (double) location.get("lat");
                    double longitude = (double) location.get("lng");

                    ChiNhanhQuanAnModel chiNhanhQuanAnModel = new ChiNhanhQuanAnModel();
                    chiNhanhQuanAnModel.setDiachi(address);
                    chiNhanhQuanAnModel.setLatitude(latitude);
                    chiNhanhQuanAnModel.setLongitude(longitude);
                    FirebaseDatabase.getInstance().getReference().child("chinhanhquanans").child(maquanan).push().setValue(chiNhanhQuanAnModel);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void ChonHinhTuGallery(int requestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Chọn hình..."),requestCode);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_IMG1:
                if (RESULT_OK == resultCode) {
                    Uri uri = data.getData();
                    imgHinhQuan1.setImageURI(uri);
                    hinhQuanAn.add(uri);

                }
                break;
            case RESULT_IMG2:
                if (RESULT_OK == resultCode) {
                    Uri uri = data.getData();
                    imgHinhQuan2.setImageURI(uri);
                    hinhQuanAn.add(uri);

                }
                break;
            case RESULT_IMG3:
                if (RESULT_OK == resultCode) {
                    Uri uri = data.getData();
                    imgHinhQuan3.setImageURI(uri);
                    hinhQuanAn.add(uri);

                }
                break;
            case RESULT_IMG4:
                if (RESULT_OK == resultCode) {
                    Uri uri = data.getData();
                    imgHinhQuan4.setImageURI(uri);
                    hinhQuanAn.add(uri);
                }
                break;
            case RESULT_IMG5:
                if (RESULT_OK == resultCode) {
                    Uri uri = data.getData();
                    imgHinhQuan5.setImageURI(uri);
                    hinhQuanAn.add(uri);
                }
                break;
            case RESULT_IMG6:
                if (RESULT_OK == resultCode) {
                    Uri uri = data.getData();
                    imgHinhQuan6.setImageURI(uri);
                    hinhQuanAn.add(uri);
                }
                break;
            case RESULT_IMGTHUCDON:
                if (RESULT_OK == resultCode) {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    imgTam.setImageBitmap(bitmap);
                    hinhDaChup.add(bitmap);
                }
                break;
            case RESULT_VIDEO:
                if (RESULT_OK == resultCode) {
                    imgVideo.setVisibility(View.GONE);
                    Uri uri = data.getData();
                    videoSelected = uri;
                    vdVideo.setVideoURI(uri);
                    vdVideo.start();
                }
                break;
        }


    }

    private void CloneThucDon(){
        View view = LayoutInflater.from(ThemQuanAnActivity.this).inflate(R.layout.layout_clone_thucdon,null);
        final Spinner spinnerThucdon = (Spinner) view.findViewById(R.id.spinnerThucdon);
        Button btnThemThucDon = (Button) view.findViewById(R.id.btnThemThucDon);
        final EditText edtTenMon = (EditText) view.findViewById(R.id.edtTenMon);
        final EditText edtGiaTien = (EditText) view.findViewById(R.id.edtGiaTien);
        ImageView imgChupHinh = (ImageView) view.findViewById(R.id.imgChupHinh);
        imgTam = imgChupHinh;

        ArrayAdapter<String> adapterThucdon = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,thucdonList);
        spinnerThucdon.setAdapter(adapterThucdon);

        if (thucDonModelList.size()==0) {
            LayDanhSachThucDon(adapterThucdon);
        }

        imgChupHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,RESULT_IMGTHUCDON);
            }
        });

        btnThemThucDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);

                String tenhinh = String.valueOf(Calendar.getInstance().getTimeInMillis())+".jpg";
                int position = spinnerThucdon.getSelectedItemPosition();
                String mathucdon = thucDonModelList.get(position).getMathucdon();

                MonAnModel monAnModel = new MonAnModel();
                monAnModel.setTenmon(edtTenMon.getText().toString());
                monAnModel.setGiatien(Long.parseLong(edtGiaTien.getText().toString()));
                monAnModel.setHinhanh(tenhinh);

                ThemThucDonModel themThucDonModel = new ThemThucDonModel();
                themThucDonModel.setMathucdon(mathucdon);
                themThucDonModel.setMonAnModel(monAnModel);

                themThucDonModelList.add(themThucDonModel);

                CloneThucDon();
            }
        });

        khungChuaThucDon.addView(view);
    }

    private void CloneChiNhanh(){
        final View view = LayoutInflater.from(ThemQuanAnActivity.this).inflate(R.layout.layout_clone_chinhanh,null);
        ImageButton btnThemChiNhanh = (ImageButton) view.findViewById(R.id.btnThemChiNhanh);
        final ImageButton btnXoaChiNhanh = (ImageButton) view.findViewById(R.id.btnXoaChiNhanh);

        btnThemChiNhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtTenChiNhanh = (EditText) view.findViewById(R.id.edtTenChiNhanh);
                String tenChiNhanh = edtTenChiNhanh.getText().toString();

                v.setVisibility(View.GONE);
                btnXoaChiNhanh.setVisibility(View.VISIBLE);
                btnXoaChiNhanh.setTag(tenChiNhanh);

                chiNhanhList.add(tenChiNhanh);

                CloneChiNhanh();
            }
        });

        btnXoaChiNhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenChiNhanh = v.getTag().toString();
                chiNhanhList.remove(tenChiNhanh);
                khungChuaChiNhanh.removeView(view);

            }
        });
        khungChuaChiNhanh.addView(view);
    }

    private void LayDanhSachTienIch() {
        FirebaseDatabase.getInstance().getReference().child("quanlytienichs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    String matienich = snapshot.getKey();
                    TienIchModel tienIchModel = snapshot.getValue(TienIchModel.class);
                    tienIchModel.setMatienich(matienich);

                    CheckBox checkBox = new CheckBox(ThemQuanAnActivity.this);
                    checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    checkBox.setText(tienIchModel.getTentienich());
                    checkBox.setTag(matienich);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            String matienich = buttonView.getTag().toString();
                            if (isChecked) {
                                tienIchList.add(matienich);
                            } else {
                                tienIchList.remove(matienich);
                            }

                        }
                    });
                    themkhungtienich.addView(checkBox);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void LayDanhSachKhuVuc() {
        FirebaseDatabase.getInstance().getReference().child("khuvucs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot valuekhuvuc: dataSnapshot.getChildren()) {
                    String tenkhuvuc = valuekhuvuc.getKey();
                    khuvucList.add(tenkhuvuc);
                }
                adapterKhuvuc.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void LayDanhSachThucDon(final ArrayAdapter<String> adapterThucdon) {
        FirebaseDatabase.getInstance().getReference().child("thucdons").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot valuethucdon: dataSnapshot.getChildren()) {
                    ThucDonModel thucDonModel = new ThucDonModel();
                    String key = valuethucdon.getKey();
                    String value = valuethucdon.getValue(String.class);
                    thucDonModel.setTenthucdon(value);
                    thucDonModel.setMathucdon(key);
                    thucDonModelList.add(thucDonModel);
                    thucdonList.add(value);
                }
                adapterThucdon.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerKhuvuc:
                    khuvuc = khuvucList.get(position);
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
