package com.gtv.hanhee.databinding.Presenter;

import com.gtv.hanhee.databinding.MainActivityContact;
import com.gtv.hanhee.databinding.Model.Account;

public class MainActivityPresenter {
    private MainActivityContact.View view;

    public MainActivityPresenter(MainActivityContact.View view) {
        this.view = view;
    }

    public void onShowData(Account account) {
        this.view.showData(account);
    }

}
