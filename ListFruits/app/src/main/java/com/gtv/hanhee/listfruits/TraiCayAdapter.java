package com.gtv.hanhee.listfruits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 3/22/2018.
 */

public class TraiCayAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private List<TraiCay> traicayList;

    public TraiCayAdapter(Context context, int layout, List<TraiCay> traicayList) {
        this.context = context;
        this.layout = layout;
        this.traicayList = traicayList;
    }

    @Override
    public int getCount() {
        return traicayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        ImageView imgHinh;
        TextView txtTen, txtMota;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout,null);
            holder = new ViewHolder();

            holder.txtTen = (TextView) view.findViewById(R.id.textViewTen);
            holder.txtMota = (TextView) view.findViewById(R.id.textViewMoTa);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imageViewHinh);
            view.setTag(holder);
        } else {
            holder = (ViewHolder view.getTag();
        }

        TraiCay traiCay = traicayList.get(i);

        holder.txtTen.setText(traiCay.getTen());
        holder.txtMota.setText(traiCay.getMoTa());
        holder.imgHinh.setImageResource(traiCay.getHinh());

        return view;
    }
}
