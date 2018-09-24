package com.gtv.hanhee.databinding.ListDataBinding.Model;

import android.databinding.ObservableField;

public class ListHeading {
    public ObservableField<String> title = new ObservableField<>();

    public ListHeading(String title) {
        this.title.set(title);
    }
}
