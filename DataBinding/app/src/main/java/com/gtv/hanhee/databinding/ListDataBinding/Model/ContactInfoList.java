package com.gtv.hanhee.databinding.ListDataBinding.Model;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.view.View;

import java.util.Random;

public class ContactInfoList extends BaseObservable {
    public ObservableArrayList<Contact> list = new ObservableArrayList<>();
    private int totalContact = 100;

    public ContactInfoList() {
        Random random = new Random();
        for (int i=0; i<totalContact; i++) {
            Contact c = new Contact();
            String ten = "Ten "+i;
            String phone = "098";
            for (int p=0; p<7; p++) {
                phone += random.nextInt(10);
            }
            c.name.set(ten);
            c.phone.set(phone);
            list.add(c);
        }
    }



    public void add(View view) {
        Contact c = new Contact("Ten "+(++totalContact),"0972097443");
        list.add(c);
    }

    public void remove(View view) {
        if (!list.isEmpty()) {
            list.remove(0);
        }
    }
}
