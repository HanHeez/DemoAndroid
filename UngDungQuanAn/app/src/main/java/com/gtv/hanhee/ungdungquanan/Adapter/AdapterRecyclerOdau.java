package com.gtv.hanhee.ungdungquanan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.gtv.hanhee.ungdungquanan.Model.BinhLuanModel;
import com.gtv.hanhee.ungdungquanan.Model.ChiNhanhQuanAnModel;
import com.gtv.hanhee.ungdungquanan.Model.QuanAnModel;
import com.gtv.hanhee.ungdungquanan.R;
import com.gtv.hanhee.ungdungquanan.View.ManHinhTrangChu.ChiTietQuanAnActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class AdapterRecyclerOdau extends RecyclerView.Adapter<AdapterRecyclerOdau.ViewHolder> {

    List<QuanAnModel> quanAnModelList;
    int resource;
    Context context;


    public AdapterRecyclerOdau(Context context, List<QuanAnModel> quanAnModelList, int resource) {


        this.context = context;
        this.quanAnModelList = quanAnModelList;
        this.resource = resource;
    }

    @NonNull
    @Override
    public AdapterRecyclerOdau.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerOdau.ViewHolder holder, int position) {


        final QuanAnModel quanAnModel = quanAnModelList.get(position);
        holder.txtTenQAOdau.setText(quanAnModel.getTenquanan());
        if (quanAnModel.isGiaohang()) {
            holder.btnDatmonOdau.setVisibility(View.VISIBLE);
        }
        if (quanAnModel.getHinhanhquanan().size() > 0) {
            StorageReference storageHinhanh = FirebaseStorage.getInstance().getReference()
                    .child("hinhanh").child(quanAnModel.getHinhanhquanan().get(0));
            GlideApp.with(context).load(storageHinhanh).transition(new DrawableTransitionOptions().crossFade(200)).into(holder.imgHinhQAOdau);

        }

        if (quanAnModel.getBinhluanlist().size() > 0) {
            BinhLuanModel binhLuanModel = quanAnModel.getBinhluanlist().get(0);
            holder.txtTieudebl1.setText(binhLuanModel.getTieude());
            holder.txtNoidungbl1.setText(binhLuanModel.getNoidung());
            holder.txtChamdiemBL1.setText(binhLuanModel.getChamdiem() + "");
            setHinhAnhBinhLuan(holder.cimgUser1, binhLuanModel.getThanhVienModel().getHinhanh());
            if (quanAnModel.getBinhluanlist().size() > 2) {
                BinhLuanModel binhLuanModel2 = quanAnModel.getBinhluanlist().get(1);
                holder.txtTieudebl2.setText(binhLuanModel2.getTieude());
                holder.txtNoidungbl2.setText(binhLuanModel2.getNoidung());
                holder.txtChamdiemBL2.setText(binhLuanModel2.getChamdiem() + "");
                setHinhAnhBinhLuan(holder.cimgUser2, binhLuanModel2.getThanhVienModel().getHinhanh());
            }
            holder.txtTongbinhluan.setText(quanAnModel.getBinhluanlist().size() + "");
            int tonghinhBL = 0;
            double tongdiem = 0;
            for (BinhLuanModel binhLuanModel1 : quanAnModel.getBinhluanlist()) {
                tonghinhBL += binhLuanModel1.getListhinhanhBL().size();
                tongdiem += binhLuanModel1.getChamdiem();
            }

            double diemTB = tongdiem / quanAnModel.getBinhluanlist().size();
            holder.txtDiemTB.setText(String.format("%.1f", diemTB) + "");

            if (tonghinhBL > 0) {
                holder.txtTonghinhBL.setText(tonghinhBL + "");
            }
        } else {
            holder.containerBinhluan1.setVisibility(View.GONE);
            holder.containerBinhluan2.setVisibility(View.GONE);
        }
        //Lay chi nhánh quán ăn và hiện thị khoảng cách
        if (quanAnModel.getChiNhanhQuanAnModelList().size() > 0) {
            ChiNhanhQuanAnModel chiNhanhQuanAnModelDangKT = quanAnModel.getChiNhanhQuanAnModelList().get(0);
            for (ChiNhanhQuanAnModel chiNhanhQuanAnModel : quanAnModel.getChiNhanhQuanAnModelList()) {
                if (chiNhanhQuanAnModel.getKhoangcach() < chiNhanhQuanAnModelDangKT.getKhoangcach()) {
                    chiNhanhQuanAnModelDangKT = chiNhanhQuanAnModel;
                }
            }
            holder.txtDiachiQA.setText(chiNhanhQuanAnModelDangKT.getDiachi());
            holder.txtKhoangcachdenQA.setText(String.format("%.1f", chiNhanhQuanAnModelDangKT.getKhoangcach()) + " km");
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iChiTietQuanAn = new Intent(context, ChiTietQuanAnActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("quanan", quanAnModel);
                iChiTietQuanAn.putExtra("MY_PARAMS", bundle);
                context.startActivity(iChiTietQuanAn);
            }
        });
    }

    private void setHinhAnhBinhLuan(final CircleImageView circleImageView1, String linkhinh) {
        StorageReference storageHinhanh = FirebaseStorage.getInstance().getReference()
                .child("thanhvien").child(linkhinh);
        GlideApp.with(context).load(storageHinhanh).into(circleImageView1);

    }

    @Override
    public int getItemCount() {
        return quanAnModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTenQAOdau, txtTieudebl1, txtTieudebl2,
                txtNoidungbl1, txtNoidungbl2, txtChamdiemBL1, txtChamdiemBL2,
                txtTongbinhluan, txtTonghinhBL, txtDiemTB,
                txtDiachiQA, txtKhoangcachdenQA;
        CardView cardView;
        Button btnDatmonOdau;
        ImageView imgHinhQAOdau;
        CircleImageView cimgUser1, cimgUser2;
        LinearLayout containerBinhluan1, containerBinhluan2;


        public ViewHolder(View itemView) {
            super(itemView);
            txtTenQAOdau = (TextView) itemView.findViewById(R.id.txtTenQAOdau);
            btnDatmonOdau = (Button) itemView.findViewById(R.id.btnDatmonOdau);
            imgHinhQAOdau = (ImageView) itemView.findViewById(R.id.imgHinhQAODau);
            txtTieudebl1 = (TextView) itemView.findViewById(R.id.txtTieudebl1);
            txtTieudebl2 = (TextView) itemView.findViewById(R.id.txtTieudebl2);
            txtNoidungbl1 = (TextView) itemView.findViewById(R.id.txtNoidungbl1);
            txtNoidungbl2 = (TextView) itemView.findViewById(R.id.txtNoidungbl2);
            txtChamdiemBL1 = (TextView) itemView.findViewById(R.id.txtChamdiemBL1);
            txtChamdiemBL2 = (TextView) itemView.findViewById(R.id.txtChamdiemBL2);
            txtTongbinhluan = (TextView) itemView.findViewById(R.id.txtTongbinhluan);
            cardView = (CardView) itemView.findViewById(R.id.cardViewOdau);
            txtTonghinhBL = (TextView) itemView.findViewById(R.id.txtTonghinhBL);
            txtDiemTB = (TextView) itemView.findViewById(R.id.txtDiemTB);
            txtDiachiQA = (TextView) itemView.findViewById(R.id.txtDiachiQA);
            txtKhoangcachdenQA = (TextView) itemView.findViewById(R.id.txtKhoangcachdenQA);
            cimgUser1 = (CircleImageView) itemView.findViewById(R.id.cimgUser1);
            cimgUser2 = (CircleImageView) itemView.findViewById(R.id.cimgUser2);
            containerBinhluan1 = (LinearLayout) itemView.findViewById(R.id.containerBinhluan1);
            containerBinhluan2 = (LinearLayout) itemView.findViewById(R.id.containerBinhluan2);
        }
    }


}
