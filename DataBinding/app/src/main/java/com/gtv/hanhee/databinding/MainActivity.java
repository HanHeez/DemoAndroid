package com.gtv.hanhee.databinding;


import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.gtv.hanhee.databinding.Model.Account;
import com.gtv.hanhee.databinding.Presenter.MainActivityPresenter;
import com.gtv.hanhee.databinding.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements MainActivityContact.View {
    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainActivityPresenter presenter = new MainActivityPresenter(MainActivity.this);
        account = new Account();
        account.username.set("HanHee");
        account.password.set("1234");
        binding.setAccount(account);
        binding.setMpresenter(presenter);
    }

    @Override
    public void showData(Account account) {
        Toast.makeText(this, account.username.get(), Toast.LENGTH_SHORT).show();
    }
}
