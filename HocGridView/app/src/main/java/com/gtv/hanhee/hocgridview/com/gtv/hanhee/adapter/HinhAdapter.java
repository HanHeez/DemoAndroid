package com.gtv.hanhee.hocgridview.com.gtv.hanhee.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.gtv.hanhee.hocgridview.R;

import java.util.List;

/**
 * Created by Administrator on 3/29/2018.
 */

public class HinhAdapter extends ArrayAdapter<Integer> {

    Activity context;
    int resource;
    List<Integer> objects;
    public HinhAdapter(@NonNull Activity context, int resource, @NonNull List<Integer> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row= inflater.inflate(this.resource,null);
        ImageView img = row.findViewById(R.id.imgHinh);
        int listhinh = this.objects.get(position);
        img.setImageResource(listhinh);
        return row;

    }
}
