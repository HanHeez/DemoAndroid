package com.gtv.hanhee.dagger2.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.gtv.hanhee.dagger2.Api.RestApi;
import com.gtv.hanhee.dagger2.Model.Responsitory;
import com.gtv.hanhee.dagger2.R;
import com.gtv.hanhee.dagger2._GameOfThrone.Interface.BattleComponent;
import com.gtv.hanhee.dagger2._GameOfThrone.Module.BraavosModule;
import com.gtv.hanhee.dagger2._GameOfThrone.View.War;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MyApplication) getApplication()).getNetComponent().inject(this);

        textView = findViewById(R.id.txtDagger2);
        Call<ArrayList<Responsitory>> callResponsitory = retrofit.create(RestApi.class).getResponsitory("HanHee");

        callResponsitory.enqueue(new Callback<ArrayList<Responsitory>>() {
            @Override
            public void onResponse(Call<ArrayList<Responsitory>> call, Response<ArrayList<Responsitory>> response) {
                textView.setText(response.body().get(0).getFullName());
            }

            @Override
            public void onFailure(Call<ArrayList<Responsitory>> call, Throwable t) {

            }
        });
    }
}
