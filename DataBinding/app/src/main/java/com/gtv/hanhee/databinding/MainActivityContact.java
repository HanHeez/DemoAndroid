package com.gtv.hanhee.databinding;

import com.gtv.hanhee.databinding.Model.Account;

public interface MainActivityContact {
    public interface Presenter {
        void onShowData(Account account);
    }

    public interface View {
        void showData(Account account);
    }
}
