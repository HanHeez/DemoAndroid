package com.gtv.hanhee.ungdungquanan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.gtv.hanhee.ungdungquanan.Model.BinhLuanModel;
import com.gtv.hanhee.ungdungquanan.R;
import com.gtv.hanhee.ungdungquanan.View.ManHinhChiTietBinhLuan.ChiTietBinhLuanActivity;

import java.util.List;

public class AdapterRecyclerHinhBinhLuan extends RecyclerView.Adapter<AdapterRecyclerHinhBinhLuan.ViewHolder> {

    Context context;
    int resource;
    List<String> listHinh;
    BinhLuanModel binhLuanModel;
    boolean isChiTietBinhLuan;

    public AdapterRecyclerHinhBinhLuan(Context context, int layout, List<String> listHinh, BinhLuanModel binhLuanModel, boolean isChiTietBinhLuan) {
        this.context = context;
        this.resource = layout;
        this.listHinh = listHinh;
        this.binhLuanModel = binhLuanModel;
        this.isChiTietBinhLuan = isChiTietBinhLuan;
    }

    @NonNull
    @Override
    public AdapterRecyclerHinhBinhLuan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerHinhBinhLuan.ViewHolder holder, int position) {
        if (listHinh.size() > 0) {
            StorageReference storageHinhanh = FirebaseStorage.getInstance().getReference()
                    .child("hinhanh").child(listHinh.get(position));
            GlideApp.with(context).load(storageHinhanh).transition(new DrawableTransitionOptions().crossFade(200)).into(holder.imgHinhBinhLuan);

            if (!isChiTietBinhLuan & position == 3) {
                int sohinhconlai = listHinh.size() - 4;
                if (sohinhconlai > 0) {
                    holder.khungSoHinhBinhLuan.setVisibility(View.VISIBLE);
                    holder.txtSoHinhBinhLuan.setText("+" + sohinhconlai);

                    holder.imgHinhBinhLuan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent iChiTietBinhLuan = new Intent(context, ChiTietBinhLuanActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("binhluanmodel", binhLuanModel);
                            iChiTietBinhLuan.putExtra("MY_PARAM", bundle);
                            context.startActivity(iChiTietBinhLuan);
                        }
                    });
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (!isChiTietBinhLuan && listHinh.size() > 4) {
            return 4;
        } else {
            return listHinh.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhBinhLuan;
        TextView txtSoHinhBinhLuan;
        FrameLayout khungSoHinhBinhLuan;

        public ViewHolder(View itemView) {
            super(itemView);
            imgHinhBinhLuan = (ImageView) itemView.findViewById(R.id.imgBinhLuan);
            txtSoHinhBinhLuan = (TextView) itemView.findViewById(R.id.txtSoHinhBL);
            khungSoHinhBinhLuan = (FrameLayout) itemView.findViewById(R.id.khungSoHinhBinhLuan);
        }
    }


}
