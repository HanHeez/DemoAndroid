package com.gtv.hanhee.databinding.ListDataBinding.Model;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.gtv.hanhee.databinding.ListDataBinding.Adapter.MyListAdapter;
import com.gtv.hanhee.databinding.ListDataBinding.Adapter.MyRecycleViewAdapter;

public class BindingUtils {
//    @BindingAdapter({"items"})
//    public static void bindList(ListView listView, ObservableArrayList<Contact> list) {
//        MyListAdapter adapter = new MyListAdapter(list);
//        listView.setAdapter(adapter);
//    }

        @BindingAdapter({"items"})
    public static void bindList(Context context, RecyclerView recyclerView, ObservableArrayList<Contact> list) {
        MyRecycleViewAdapter adapter = new MyRecycleViewAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
