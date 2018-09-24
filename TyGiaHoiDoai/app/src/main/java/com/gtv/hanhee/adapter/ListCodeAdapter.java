package com.gtv.hanhee.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gtv.hanhee.model.ListCode;
import com.gtv.hanhee.tygiahoidoai.R;

import java.util.List;

/**
 * Created by Administrator on 3/29/2018.
 */

public class ListCodeAdapter extends ArrayAdapter<ListCode> {

    Activity context;
    int resource;
    List<ListCode> objects;

    public ListCodeAdapter(@NonNull Activity context, int resource, @NonNull List<ListCode> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        ImageView imgFlag = (ImageView) row.findViewById(R.id.imgFlag);
        TextView txtMuaTM = (TextView) row.findViewById(R.id.txtMuaTM);
        TextView txtBanTM = (TextView) row.findViewById(R.id.txtBanTM);
        TextView txtMuaCK = (TextView) row.findViewById(R.id.txtMuaCK);
        TextView txtBanCK = (TextView) row.findViewById(R.id.txtBanCK);

        ListCode listcode = this.objects.get(position);
        imgFlag.setImageResource(listcode.getImgFlag());
        txtMuaTM.setText(listcode.getMuaTM());
        txtBanTM.setText(listcode.getBanTM());
        txtMuaCK.setText(listcode.getMuaCK());
        txtBanCK.setText(listcode.getBanCK());

        return row;
    }
}
