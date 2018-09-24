package com.gtv.hanhee.databinding.ListDataBinding.Adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gtv.hanhee.databinding.ListDataBinding.Model.Contact;
import com.gtv.hanhee.databinding.ListDataBinding.Model.ContactInfoList;
import com.gtv.hanhee.databinding.R;
import com.gtv.hanhee.databinding.databinding.ItemListviewBinding;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder>{
    private ObservableArrayList<Contact> list;
    private LayoutInflater layoutInflater;

    public MyRecycleViewAdapter(ObservableArrayList<Contact> list) {
        this.list = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ObservableField<String> name = new ObservableField<>();
        public ObservableField<String> phone = new ObservableField<>();
        public ItemListviewBinding itemListviewBinding;

        public MyViewHolder(ItemListviewBinding itemView) {
            super(itemView.getRoot());
            this.itemListviewBinding = itemView;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemListviewBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_listview, parent,false);

        return new MyViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Contact contact = list.get(position);
        holder.itemListviewBinding.setContact(contact);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
