package com.gtv.hanhee.projectungdungbanhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gtv.hanhee.projectungdungbanhang.R;
import com.gtv.hanhee.projectungdungbanhang.activity.ChiTietSanPham;
import com.gtv.hanhee.projectungdungbanhang.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SpAdapter extends RecyclerView.Adapter<SpAdapter.ItemHolder> {

    Context context;
    ArrayList<Sanpham> arrSanpham;

    public SpAdapter(Context context, ArrayList<Sanpham> arrSanpham) {
        this.context = context;
        this.arrSanpham = arrSanpham;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanphammoinhat, null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Sanpham sanpham = arrSanpham.get(position);
        holder.txttensanpham.setText(sanpham.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgiasanpham.setText("Giá: "+decimalFormat.format(sanpham.getGiasp())+" Đ");
        Picasso.get().load(sanpham.getHinhanhsp())
                .placeholder(R.drawable.common_full_open_on_phone)
                .error(R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.imghinhsp);

    }

    @Override
    public int getItemCount() {
        return arrSanpham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView imghinhsp;
        public TextView txttensanpham;
        public TextView txtgiasanpham;

        public ItemHolder(final View itemView) {
            super(itemView);
            imghinhsp = (ImageView) itemView.findViewById(R.id.imgSanpham);
            txttensanpham = (TextView) itemView.findViewById(R.id.txtTensp);
            txtgiasanpham = (TextView) itemView.findViewById(R.id.txtGiasp);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChiTietSanPham.class);
                    intent.putExtra("thongtinsanpham",arrSanpham.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

}
