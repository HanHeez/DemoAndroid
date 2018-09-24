package com.gtv.hanhee.databinding.ListDataBinding.View;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.gtv.hanhee.databinding.ListDataBinding.Adapter.MyRecycleViewAdapter;
import com.gtv.hanhee.databinding.ListDataBinding.Model.Contact;
import com.gtv.hanhee.databinding.ListDataBinding.Model.ContactInfoList;
import com.gtv.hanhee.databinding.ListDataBinding.Model.ListHeading;
import com.gtv.hanhee.databinding.MainActivity;
import com.gtv.hanhee.databinding.R;
import com.gtv.hanhee.databinding.databinding.ActivityListBinding;


public class ListActivity extends AppCompatActivity {

    MyRecycleViewAdapter myRecycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityListBinding binding = DataBindingUtil.setContentView(ListActivity.this,R.layout.activity_list);
        ListHeading heading = new ListHeading("Danh Sach Contact");
        binding.setListheading(heading);

        ContactInfoList contactInfoList = new ContactInfoList();
        binding.setContactInfoList(contactInfoList);
        myRecycleViewAdapter = new MyRecycleViewAdapter(contactInfoList.list);
        binding.recyclerListContact.setLayoutManager(new LinearLayoutManager(this));
    }
}
