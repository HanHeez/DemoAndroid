package com.gtv.hanhee.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gtv.hanhee.hieuunglistview.R;

import java.util.List;

/**
 * Created by Administrator on 4/20/2018.
 */

public class MyAdapter extends ArrayAdapter<String> {

    Activity context;
    int resource;
    List<String> objects;

    public MyAdapter(@NonNull Activity context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);
        TextView txtTen = row.findViewById(R.id.txtTen);
        txtTen.setText(this.objects.get(position));

        Animation animation= AnimationUtils.loadAnimation(this.context,R.anim.hieuunglistview);
        row.startAnimation(animation);

        return row;
    }
}
