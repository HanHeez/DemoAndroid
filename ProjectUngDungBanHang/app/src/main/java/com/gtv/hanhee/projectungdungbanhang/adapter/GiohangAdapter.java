package com.gtv.hanhee.projectungdungbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gtv.hanhee.projectungdungbanhang.R;
import com.gtv.hanhee.projectungdungbanhang.activity.GioHangActivity;
import com.gtv.hanhee.projectungdungbanhang.activity.MainActivity;
import com.gtv.hanhee.projectungdungbanhang.model.Giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GiohangAdapter extends BaseAdapter {

    Context context;
    ArrayList<Giohang> arrGiohang;
    ViewHolder viewHolder;

    public GiohangAdapter(Context context, ArrayList<Giohang> arrGiohang) {
        this.context = context;
        this.arrGiohang = arrGiohang;
    }

    @Override
    public int getCount() {
        return arrGiohang.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder {
        public TextView txtTengiohang, txtGiagiohang;
        public ImageView imgGiohang;
        public Button btnGiam, btnTang, btnValues;

    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {


        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_giohang,null);
            viewHolder.txtTengiohang = (TextView) view.findViewById(R.id.txtTengiohang);
            viewHolder.txtGiagiohang = (TextView) view.findViewById(R.id.txtGiagiohang);
            viewHolder.imgGiohang = (ImageView) view.findViewById(R.id.imgGiohang);
            viewHolder.btnGiam = (Button) view.findViewById(R.id.btnGiam);
            viewHolder.btnTang = (Button) view.findViewById(R.id.btnTang);
            viewHolder.btnValues = (Button) view.findViewById(R.id.btnValue);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Giohang giohang = (Giohang) arrGiohang.get(position);
        viewHolder.txtTengiohang.setText(giohang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiagiohang.setText("Giá: "+decimalFormat.format(giohang.getGiasp())+" Đ");

        Picasso.get().load(giohang.getHinhsp())
                .placeholder(R.drawable.common_full_open_on_phone)
                .error(R.drawable.common_google_signin_btn_icon_dark)
                .into(viewHolder.imgGiohang);
        viewHolder.btnValues.setText(giohang.getSoluongsp()+"");
        int SoLuong = Integer.parseInt(viewHolder.btnValues.getText().toString());
        if (SoLuong >=10 ) {
            viewHolder.btnTang.setVisibility(View.INVISIBLE);
            viewHolder.btnGiam.setVisibility(View.VISIBLE);
        } else if (SoLuong <=1) {
            viewHolder.btnGiam.setVisibility(View.INVISIBLE);
        } else if (SoLuong >=1) {
            viewHolder.btnTang.setVisibility(View.VISIBLE);
            viewHolder.btnGiam.setVisibility(View.VISIBLE);
        }

        viewHolder.btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat = Integer.parseInt(viewHolder.btnValues.getText().toString()) + 1;
                int slhientai = MainActivity.arrGiohang.get(position).getSoluongsp();
                long giahientai = MainActivity.arrGiohang.get(position).getGiasp();
                MainActivity.arrGiohang.get(position).setSoluongsp(slmoinhat);
                long giamoinhat = (giahientai * slmoinhat) / slhientai;
                MainActivity.arrGiohang.get(position).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.txtGiagiohang.setText(decimalFormat.format(giamoinhat)+" Đ");
                GioHangActivity.UltilEvent();
                if (slmoinhat>9) {
                    viewHolder.btnTang.setVisibility(View.INVISIBLE);
                    viewHolder.btnGiam.setVisibility(View.VISIBLE);
                    viewHolder.btnValues.setText(String.valueOf(slmoinhat));
                } else {
                    viewHolder.btnTang.setVisibility(View.VISIBLE);
                    viewHolder.btnGiam.setVisibility(View.VISIBLE);
                    viewHolder.btnValues.setText(String.valueOf(slmoinhat));
                }
            }
        });

        viewHolder.btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat = Integer.parseInt(viewHolder.btnValues.getText().toString()) - 1;
                int slhientai = MainActivity.arrGiohang.get(position).getSoluongsp();
                long giahientai = MainActivity.arrGiohang.get(position).getGiasp();
                MainActivity.arrGiohang.get(position).setSoluongsp(slmoinhat);
                long giamoinhat = (giahientai * slmoinhat) / slhientai;
                MainActivity.arrGiohang.get(position).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.txtGiagiohang.setText(decimalFormat.format(giamoinhat)+" Đ");
                GioHangActivity.UltilEvent();
                if (slmoinhat<2) {
                    viewHolder.btnTang.setVisibility(View.VISIBLE);
                    viewHolder.btnGiam.setVisibility(View.INVISIBLE);
                    viewHolder.btnValues.setText(String.valueOf(slmoinhat));
                } else {
                    viewHolder.btnTang.setVisibility(View.VISIBLE);
                    viewHolder.btnGiam.setVisibility(View.VISIBLE);
                    viewHolder.btnValues.setText(String.valueOf(slmoinhat));
                }
            }
        });


        return view;
    }
}
