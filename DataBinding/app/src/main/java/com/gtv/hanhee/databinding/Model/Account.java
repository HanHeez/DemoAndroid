package com.gtv.hanhee.databinding.Model;

import android.databinding.BaseObservable;
import android.databinding.ObservableDouble;
import android.databinding.ObservableField;

public class Account extends BaseObservable {
    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableDouble birthday = new ObservableDouble();

    public Account() {
    }

    public Account(String username, String password, Double birthday) {
        this.username.set(username);
        this.password.set(password);
        this.birthday.set(birthday);
    }
}
