package com.gtv.hanhee.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gtv.hanhee.karaoke.MainActivity;
import com.gtv.hanhee.karaoke.R;
import com.gtv.hanhee.model.Music;

import java.util.List;

/**
 * Created by Administrator on 4/7/2018.
 */

public class MusicAdapter extends ArrayAdapter<Music> {

    Activity context;
    int resource;
     List<Music> objects;

    public MusicAdapter(@NonNull Activity context, int resource, @NonNull List<Music> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);
        TextView txtMa = (TextView) row.findViewById(R.id.txtMa);
        TextView txtTenbaihat = (TextView) row.findViewById(R.id.txtTenbaihat);
        TextView txtTenCasi = (TextView) row.findViewById(R.id.txtTencasi);
        final ImageButton btnLike = (ImageButton) row.findViewById(R.id.btnLike);
        final ImageButton btnDislike = (ImageButton) row.findViewById(R.id.btnDislike);
        final Music music = this.objects.get(position);
        txtTenbaihat.setText(music.getTen());
        txtMa.setText(music.getMa());
        txtTenCasi.setText(music.getCasi());
        if (music.isThich()) {
            btnLike.setVisibility(View.INVISIBLE);
            btnDislike.setVisibility(View.VISIBLE);
        } else {
            btnLike.setVisibility(View.VISIBLE);
            btnDislike.setVisibility(View.INVISIBLE);
        }
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music.setThich(true);
                btnLike.setVisibility(View.INVISIBLE);
                btnDislike.setVisibility(View.VISIBLE);
                xulyThich(music);
            }
        });
        btnDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music.setThich(false);
                btnLike.setVisibility(View.VISIBLE);
                btnDislike.setVisibility(View.INVISIBLE);
                xulyKhongThich(music);
            }
        });
        return row;
    }

    private void xulyKhongThich(Music music) {
        ContentValues row = new ContentValues();
        row.put("YEUTHICH",0);
        MainActivity.database.update("ArirangSongList",row,"MABH=?",new String[]{music.getMa()});

    }

    private void xulyThich(Music music) {
        ContentValues row = new ContentValues();
        row.put("YEUTHICH",1);
        MainActivity.database.update("ArirangSongList",row,"MABH=?",new String[]{music.getMa()});
    }


}
