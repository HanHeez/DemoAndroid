package com.gtv.hanhee.projectungdungbanhang.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gtv.hanhee.projectungdungbanhang.R;
import com.gtv.hanhee.projectungdungbanhang.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LaptopAdapter extends BaseAdapter {

    Context context;
    ArrayList<Sanpham> arrLaptop;

    public LaptopAdapter(Context context, ArrayList<Sanpham> arrLaptop) {
        this.context = context;
        this.arrLaptop = arrLaptop;
    }

    @Override
    public int getCount() {
        return arrLaptop.size();
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
        public TextView txtTenlaptop, txtGialaptop,txtMotalaptop;
        public ImageView imgLaptop;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_laptop,null);
            viewHolder.txtTenlaptop = (TextView) view.findViewById(R.id.txtTenLaptop);
            viewHolder.txtGialaptop = (TextView) view.findViewById(R.id.txtGiaLaptop);
            viewHolder.txtMotalaptop = (TextView) view.findViewById(R.id.txtMotaLaptop);
            viewHolder.imgLaptop = (ImageView) view.findViewById(R.id.imgLaptop);

            view.setTag(viewHolder);
        } else {
            viewHolder = (LaptopAdapter.ViewHolder) view.getTag();
        }
        Sanpham sanpham = (Sanpham) arrLaptop.get(position);
        viewHolder.txtTenlaptop.setText(sanpham.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGialaptop.setText("Giá: "+decimalFormat.format(sanpham.getGiasp())+" Đ");
        viewHolder.txtMotalaptop.setMaxLines(2);
        viewHolder.txtMotalaptop.setEllipsize(TextUtils.TruncateAt.END);

        viewHolder.txtMotalaptop.setText(sanpham.getMotasp());
        Picasso.get().load(sanpham.getHinhanhsp())
                .placeholder(R.drawable.common_full_open_on_phone)
                .error(R.drawable.common_google_signin_btn_icon_dark)
                .into(viewHolder.imgLaptop);
        return view;
    }
}
