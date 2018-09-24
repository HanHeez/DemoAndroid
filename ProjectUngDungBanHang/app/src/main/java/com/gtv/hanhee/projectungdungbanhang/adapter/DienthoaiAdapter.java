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
import com.gtv.hanhee.projectungdungbanhang.model.Loaisp;
import com.gtv.hanhee.projectungdungbanhang.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DienthoaiAdapter extends BaseAdapter {

    Context context;
    ArrayList<Sanpham> arrDienthoai;

    public DienthoaiAdapter(Context context, ArrayList<Sanpham> arrDienthoai) {
        this.context = context;
        this.arrDienthoai = arrDienthoai;
    }

    @Override
    public int getCount() {
        return arrDienthoai.size();
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
        public TextView txtTendienthoai, txtGiadienthoai,txtMotadienthoai;
        public ImageView imgDienthoai;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_dienthoai,null);
            viewHolder.txtTendienthoai = (TextView) view.findViewById(R.id.txtTendienthoai);
            viewHolder.txtGiadienthoai = (TextView) view.findViewById(R.id.txtGiadienthoai);
            viewHolder.txtMotadienthoai = (TextView) view.findViewById(R.id.txtMotadienthoai);
            viewHolder.imgDienthoai = (ImageView) view.findViewById(R.id.imgDienthoai);

            view.setTag(viewHolder);
        } else {
            viewHolder = (DienthoaiAdapter.ViewHolder) view.getTag();
        }
        Sanpham sanpham = (Sanpham) arrDienthoai.get(position);
        viewHolder.txtTendienthoai.setText(sanpham.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiadienthoai.setText("Giá: "+decimalFormat.format(sanpham.getGiasp())+" Đ");
        viewHolder.txtMotadienthoai.setMaxLines(2);
        viewHolder.txtMotadienthoai.setEllipsize(TextUtils.TruncateAt.END);

        viewHolder.txtMotadienthoai.setText(sanpham.getMotasp());
        Picasso.get().load(sanpham.getHinhanhsp())
                .placeholder(R.drawable.common_full_open_on_phone)
                .error(R.drawable.common_google_signin_btn_icon_dark)
                .into(viewHolder.imgDienthoai);
        return view;
    }
}
