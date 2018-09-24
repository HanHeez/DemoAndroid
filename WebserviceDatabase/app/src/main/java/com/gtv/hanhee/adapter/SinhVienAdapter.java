package com.gtv.hanhee.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gtv.hanhee.webservicedatabase.MainActivity;
import com.gtv.hanhee.webservicedatabase.R;
import com.gtv.hanhee.webservicedatabase.SinhVien;
import com.gtv.hanhee.webservicedatabase.UpdateActivity;

import java.util.List;


public class SinhVienAdapter extends ArrayAdapter<SinhVien> {

    MainActivity context;
    int resource;
    List<SinhVien> objects;
    String ten;

    public SinhVienAdapter(@NonNull MainActivity context, int resource, @NonNull List<SinhVien> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);
        TextView txtHoten = (TextView) row.findViewById(R.id.txtHoten);
        TextView txtNamsinh = (TextView) row.findViewById(R.id.txtNamsinh);
        TextView txtDiachi = (TextView) row.findViewById(R.id.txtDiachi);
        ImageView imgEdit = (ImageView) row.findViewById(R.id.imgEdit);
        ImageView imgDelete = (ImageView) row.findViewById(R.id.imgDelete);

        final SinhVien sinhVien = this.objects.get(position);
        txtHoten.setText(sinhVien.getHoten());
        txtNamsinh.setText("Nam sinh: " + sinhVien.getNamsinh());
        txtDiachi.setText(sinhVien.getDiachi());

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("dataSinhVien", sinhVien);
                context.startActivity(intent);
            }
        });

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XacNhanXoa(sinhVien.getHoten(),sinhVien.getId());

            }
        });


        return row;

    }

    private void XacNhanXoa(String ten, final int id) {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context);
        dialogXoa.setMessage("Ban co muon xoa sinh vien" + ten +"khong");
        dialogXoa.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.DeleteStudent(id);


            }
        });
        dialogXoa.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }
}
