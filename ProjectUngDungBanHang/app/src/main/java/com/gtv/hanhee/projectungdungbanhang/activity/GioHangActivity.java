package com.gtv.hanhee.projectungdungbanhang.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gtv.hanhee.projectungdungbanhang.NonScrollListView;
import com.gtv.hanhee.projectungdungbanhang.R;
import com.gtv.hanhee.projectungdungbanhang.adapter.GiohangAdapter;
import com.gtv.hanhee.projectungdungbanhang.ultil.CheckConnection;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {

     GiohangAdapter giohangAdapter;
    NonScrollListView lvGiohang;
    TextView txtThongbao;
    static TextView txtTongtien;
    Button btnThanhtoan;
    Button btnTieptucmua;
    android.support.v7.widget.Toolbar toolbarGiohang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        AddControls();
        ActionToolbar();
        CheckData();
        UltilEvent();
        CatchOnItemListView();
        ButtonEvent();
    }

    private void ButtonEvent() {
        btnTieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.arrGiohang.size() > 0) {
                    Intent intent = new Intent(getApplicationContext(),ThongTinKhachHangActivity.class);
                    startActivity(intent);
                } else {
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Giỏ hàng còn trống");
                }
            }
        });

    }

    private void CatchOnItemListView() {
        lvGiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc xóa sản phẩm này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if (MainActivity.arrGiohang.size()<=0) {
                            txtThongbao.setVisibility(View.VISIBLE);
                        } else {
                            MainActivity.arrGiohang.remove(position);
                            giohangAdapter.notifyDataSetChanged();
                            UltilEvent();
                            if (MainActivity.arrGiohang.size() <=0) {
                                txtThongbao.setVisibility(View.VISIBLE);
                            } else {
                                txtThongbao.setVisibility(View.INVISIBLE);
                                giohangAdapter.notifyDataSetChanged();
                                UltilEvent();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        giohangAdapter.notifyDataSetChanged();
                        UltilEvent();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void UltilEvent() {
        long tongtien = 0;
        for (int i =0;i<MainActivity.arrGiohang.size();i++) {
            tongtien+= MainActivity.arrGiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTongtien.setText(decimalFormat.format(tongtien)+" Đ");
    }

    private void CheckData() {
        if (MainActivity.arrGiohang.size() <= 0) {
            giohangAdapter.notifyDataSetChanged();
            txtThongbao.setVisibility(View.VISIBLE);
            lvGiohang.setVisibility(View.INVISIBLE);

        } else {
            giohangAdapter.notifyDataSetChanged();
            txtThongbao.setVisibility(View.INVISIBLE);
            lvGiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarGiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarGiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AddControls() {
        lvGiohang = (NonScrollListView) findViewById(R.id.lvGiohang);
        txtThongbao = (TextView) findViewById(R.id.txtThongbao);
        txtTongtien = (TextView) findViewById(R.id.txtTongtien);
        btnThanhtoan = (Button) findViewById(R.id.btnThanhtoan);
        btnTieptucmua = (Button) findViewById(R.id.btnTieptucmuahang);
        toolbarGiohang = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarGiohang);
        giohangAdapter = new GiohangAdapter(GioHangActivity.this, MainActivity.arrGiohang);
        lvGiohang.setAdapter(giohangAdapter);
    }
}
