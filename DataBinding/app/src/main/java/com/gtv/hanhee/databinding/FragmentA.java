package com.gtv.hanhee.databinding;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gtv.hanhee.databinding.databinding.FragmentABinding;

import org.w3c.dom.Text;


public class FragmentA extends Fragment {


    public FragmentA() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentABinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_a, container,false);
        View rootView = binding.getRoot();
        return rootView;
    }

}
