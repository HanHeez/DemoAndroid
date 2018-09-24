package com.gtv.hanhee.databinding.ListDataBinding.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.gtv.hanhee.databinding.ListDataBinding.Model.Contact;
import com.gtv.hanhee.databinding.R;
import com.gtv.hanhee.databinding.databinding.ItemListviewBinding;


public class MyListAdapter extends BaseAdapter {

    private ObservableArrayList<Contact> list;
    private LayoutInflater inflater;
    public MyListAdapter(ObservableArrayList<Contact> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater==null) {
            inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        ItemListviewBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_listview, parent,false);
        binding.setContact(list.get(position));
        return binding.getRoot();
    }
}
