package com.gtv.hanhee.databinding.ListDataBinding.Model;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

public class Contact  extends BaseObservable{
    public ObservableField<String> phone = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();;

    public Contact() {
    }

    public Contact(String name, String phone) {
        this.phone.set(phone);
        this.name.set(name);
    }
}
