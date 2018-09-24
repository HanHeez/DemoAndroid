package com.gtv.hanhee.projectungdungbanhang.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.gtv.hanhee.projectungdungbanhang.R;
import com.gtv.hanhee.projectungdungbanhang.model.Giohang;
import com.gtv.hanhee.projectungdungbanhang.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPham extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbarchitiet;
    ImageView imgchitiet;
    TextView txtTen,txtGia,txtMota;
    Spinner spinner;
    Button btndatmua;
    int id, Giachitiet, Idsanpham;
    String Tenchitiet, Hinhanhchitiet, Motachitiet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        AddControls();
        ActionToolbar();
        GetInformation();
        SpinnerEvent();
        ButtonEvent();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void ButtonEvent() {
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gio hang co hang
                if (MainActivity.arrGiohang.size() > 0) {
                    int sluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exist = false;

                    for (int i = 0;i<MainActivity.arrGiohang.size();i++) {

                        if (MainActivity.arrGiohang.get(i).getIdsp() == id) {
                            MainActivity.arrGiohang.get(i).setSoluongsp(MainActivity.arrGiohang.get(i).getSoluongsp()+sluong);
                            if (MainActivity.arrGiohang.get(i).getSoluongsp() >=10) {
                                MainActivity.arrGiohang.get(i).setSoluongsp(10);
                            }
                            MainActivity.arrGiohang.get(i).setGiasp(Giachitiet * MainActivity.arrGiohang.get(i).getSoluongsp());
                            //id co ton tai trong mang? >> exist = true;
                            exist = true;

                        }


                    }
                    // neu id khong ton tai
                    if (exist == false) {
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long Giamoi = soluong * Giachitiet;
                        MainActivity.arrGiohang.add(new Giohang(id,Tenchitiet,Giamoi,Hinhanhchitiet,soluong));

                    }
                    // ko co hang` trong gio
                } else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giamoi = soluong * Giachitiet;
                    MainActivity.arrGiohang.add(new Giohang(id, Tenchitiet, Giamoi, Hinhanhchitiet, soluong));
                }

                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void SpinnerEvent() {
        String[] soluong = new String[]{"1","2","3","4","5","6","7","8","9","10"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item,soluong);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(arrayAdapter);

    }

    private void GetInformation() {

        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanpham.getId();
        Tenchitiet = sanpham.getTensp();
        Giachitiet = sanpham.getGiasp();
        Hinhanhchitiet = sanpham.getHinhanhsp();
        Motachitiet = sanpham.getMotasp();
        Idsanpham = sanpham.getIdsp();
        txtTen.setText(Tenchitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGia.setText("Giá: " + decimalFormat.format(Giachitiet) + " Đ");
        txtMota.setText(Motachitiet);
        Picasso.get().load(Hinhanhchitiet)
                .placeholder(R.drawable.common_full_open_on_phone)
                .error(R.drawable.common_google_signin_btn_icon_dark)
                .into(imgchitiet);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarchitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarchitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AddControls() {
        toolbarchitiet = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarChitiet);
        imgchitiet = (ImageView) findViewById(R.id.imgChitietsp);
        txtTen = (TextView) findViewById(R.id.txtTenchitietsp);
        txtGia = (TextView) findViewById(R.id.txtGiachitietsp);
        txtMota = (TextView) findViewById(R.id.txtMotachitietsp);
        spinner = (Spinner) findViewById(R.id.spinner);
        btndatmua = (Button) findViewById(R.id.btnDatmua);
    }
}
