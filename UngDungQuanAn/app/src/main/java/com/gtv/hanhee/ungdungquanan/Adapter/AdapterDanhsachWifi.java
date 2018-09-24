package com.gtv.hanhee.ungdungquanan.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gtv.hanhee.ungdungquanan.Model.WifiQuanAnModel;
import com.gtv.hanhee.ungdungquanan.R;

import java.util.List;

public class AdapterDanhsachWifi extends RecyclerView.Adapter<AdapterDanhsachWifi.ViewHolder> {

    Context context;
    int resource;
    List<WifiQuanAnModel> wifiQuanAnModelList;

    public AdapterDanhsachWifi(Context context, int resource, List<WifiQuanAnModel> wifiQuanAnModelList) {
        this.context = context;
        this.resource = resource;
        this.wifiQuanAnModelList = wifiQuanAnModelList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTenWifi, txtMatkhauWifi, txtNgaydangWifi;

        public ViewHolder(View itemView) {
            super(itemView);

            txtTenWifi = (TextView) itemView.findViewById(R.id.txtTenWifi);
            txtMatkhauWifi = (TextView) itemView.findViewById(R.id.txtMatkhauWifi);
            txtNgaydangWifi = (TextView) itemView.findViewById(R.id.txtNgaydangWifi);

        }
    }

    @NonNull
    @Override
    public AdapterDanhsachWifi.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(resource,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDanhsachWifi.ViewHolder holder, int position) {
        WifiQuanAnModel wifiQuanAnModel = wifiQuanAnModelList.get(position);
        holder.txtTenWifi.setText(wifiQuanAnModel.getTen());
        holder.txtMatkhauWifi.setText(wifiQuanAnModel.getMatkhau());
        holder.txtNgaydangWifi.setText(wifiQuanAnModel.getNgaydang());

    }

    @Override
    public int getItemCount() {
        return wifiQuanAnModelList.size();
    }


}
