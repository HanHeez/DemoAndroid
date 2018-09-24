package com.gtv.hanhee.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gtv.hanhee.hoclistviewnangcao.R;
import com.gtv.hanhee.model.DanhBa;

import java.util.List;

/**
 * Created by Administrator on 3/29/2018.
 */

public class DanhBaAdapter extends ArrayAdapter<DanhBa> {

    Activity context;
    int resource;
    List<DanhBa> objects;
    public DanhBaAdapter(Activity context, int resource, List<DanhBa> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;


    }

      @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);
        TextView txtTen = (TextView) row.findViewById(R.id.txtTen);
        TextView txtPhone = (TextView) row.findViewById(R.id.txtPhone);
        ImageButton btnCall = (ImageButton) row.findViewById(R.id.btnCall);
        ImageButton btnSms = (ImageButton) row.findViewById(R.id.btnSms);
        ImageButton btnDetail = (ImageButton) row.findViewById(R.id.btnDetail);

        final DanhBa danhba = this.objects.get(position);
        txtTen.setText(danhba.getTen());
        txtPhone.setText(danhba.getPhone());
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyXemChiTiet(danhba);

            }
        });

        return row;
    }

    private void xulyXemChiTiet(DanhBa danhba) {
        Toast.makeText(this.context, "Ban chon: "+danhba.getTen(), Toast.LENGTH_SHORT).show();
    }
}
