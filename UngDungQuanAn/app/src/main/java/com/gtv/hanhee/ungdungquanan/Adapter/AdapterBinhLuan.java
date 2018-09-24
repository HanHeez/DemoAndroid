package com.gtv.hanhee.ungdungquanan.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.gtv.hanhee.ungdungquanan.Model.BinhLuanModel;
import com.gtv.hanhee.ungdungquanan.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterBinhLuan extends RecyclerView.Adapter<AdapterBinhLuan.ViewHolder> {

    Context context;
    List<BinhLuanModel> binhLuanModelList;
    int layout;

    public AdapterBinhLuan(Context context, List<BinhLuanModel> binhLuanModelList, int layout) {
        this.context = context;
        this.binhLuanModelList = binhLuanModelList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public AdapterBinhLuan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder viewHolderBL = new ViewHolder(view);
        return viewHolderBL;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBinhLuan.ViewHolder holder, int position) {
        BinhLuanModel binhLuanModel = binhLuanModelList.get(position);
        holder.txtTieudebinhluan.setText(binhLuanModel.getTieude());
        holder.txtNoidungbinhluan.setText(binhLuanModel.getNoidung());
        holder.txtChamdiembinhluan.setText(binhLuanModel.getChamdiem() + "");
        setHinhAnhBinhLuan(holder.circleImageUser, binhLuanModel.getThanhVienModel().getHinhanh());
        AdapterRecyclerHinhBinhLuan adapterRecyclerHinhBinhLuan =
                new AdapterRecyclerHinhBinhLuan(context, R.layout.custom_layout_hinhbinhluan, binhLuanModel.getListhinhanhBL(), binhLuanModel, false);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2);
        holder.recyclerHinhBL.setLayoutManager(layoutManager);
        holder.recyclerHinhBL.setAdapter(adapterRecyclerHinhBinhLuan);
        adapterRecyclerHinhBinhLuan.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if (binhLuanModelList.size() > 5) {
            return 5;
        } else {
            return binhLuanModelList.size();
        }
    }

    private void setHinhAnhBinhLuan(final CircleImageView circleImageView, String linkhinh) {
        StorageReference storageHinhanh = FirebaseStorage.getInstance().getReference()
                .child("thanhvien").child(linkhinh);
        GlideApp.with(context).load(storageHinhanh).transition(new DrawableTransitionOptions().crossFade(200)).into(circleImageView);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageUser;
        TextView txtTieudebinhluan;
        TextView txtNoidungbinhluan;
        TextView txtChamdiembinhluan;
        RecyclerView recyclerHinhBL;


        public ViewHolder(View itemView) {
            super(itemView);
            circleImageUser = (CircleImageView) itemView.findViewById(R.id.cimageUser);
            txtTieudebinhluan = (TextView) itemView.findViewById(R.id.txtTieudebinhluan);
            txtNoidungbinhluan = (TextView) itemView.findViewById(R.id.txtNoidungbinhluan);
            txtChamdiembinhluan = (TextView) itemView.findViewById(R.id.txtChamdiembinhluan);
            recyclerHinhBL = (RecyclerView) itemView.findViewById(R.id.recyclerHinhBL);
        }
    }


}
