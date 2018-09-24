package com.gtv.hanhee.gridview;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 3/22/2018.
 */

public class HinhAnhAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<HinhAnh> hinhAnhList;

    public HinhAnhAdapter(Context context, int layout, List<HinhAnh> hinhAnhList) {
        this.context = context;
        this.layout = layout;
        this.hinhAnhList = hinhAnhList;
    }

    @Override
    public int getCount() {
        return hinhAnhList.size();
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
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imageViewHinhAnh);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        HinhAnh hinhAnh = hinhAnhList.get(i);
        holder.imgHinh.setImageResource(hinhAnh.getHinh());

        return view;
    }
}
