package com.gtv.hanhee.projectungdungbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gtv.hanhee.projectungdungbanhang.R;
import com.gtv.hanhee.projectungdungbanhang.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaispAdapter extends BaseAdapter {

    ArrayList<Loaisp> arrLoaisp;
    Context context;

    public LoaispAdapter(ArrayList<Loaisp> arrLoaisp, Context context) {
        this.arrLoaisp = arrLoaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrLoaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return arrLoaisp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        TextView txttenloaisp;
        ImageView imgloaisp;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_loaisp,null);
            viewHolder.txttenloaisp = (TextView) view.findViewById(R.id.txtLoaisp);
            viewHolder.imgloaisp = (ImageView) view.findViewById(R.id.imgLoaisp);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
            Loaisp loaisp = (Loaisp) getItem(position);
            viewHolder.txttenloaisp.setText(loaisp.getTenloaisp());
            Picasso.get().load(loaisp.getHinhanhloaisp())
                    .placeholder(R.drawable.common_full_open_on_phone)
                    .error(R.drawable.common_google_signin_btn_icon_dark)
                    .into(viewHolder.imgloaisp);

        return view;
    }
}
