package com.gtv.hanhee.projectungdungbanhang.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gtv.hanhee.projectungdungbanhang.NonScrollListView;
import com.gtv.hanhee.projectungdungbanhang.R;
import com.gtv.hanhee.projectungdungbanhang.adapter.DienthoaiAdapter;
import com.gtv.hanhee.projectungdungbanhang.adapter.LoaispAdapter;
import com.gtv.hanhee.projectungdungbanhang.adapter.SpAdapter;
import com.gtv.hanhee.projectungdungbanhang.model.Giohang;
import com.gtv.hanhee.projectungdungbanhang.model.Loaisp;
import com.gtv.hanhee.projectungdungbanhang.model.Sanpham;
import com.gtv.hanhee.projectungdungbanhang.ultil.CheckConnection;
import com.gtv.hanhee.projectungdungbanhang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DienThoaiActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbardt;
    NonScrollListView lvDt;
    ArrayList<Sanpham> arrDt;
    DienthoaiAdapter dtadapter;
    int iddt = 1;
    int page = 1;
    View footerview;
    boolean isLoading = false;
    boolean limitData = false;
    MyHandler myHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            DtAddControls();
            GetData(page);
            ActionToolbar();
            LoadMoreData();
        } else {
            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
            finish();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void LoadMoreData() {
        lvDt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ChiTietSanPham.class);
                intent.putExtra("thongtinsanpham",arrDt.get(position));
                startActivity(intent);
            }
        });
        lvDt.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0
                        && isLoading == false && limitData == false) {
                    isLoading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();

                }
            }
        });
    }

    public class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    lvDt.addFooterView(footerview);
                    break;
                case 1:
                    GetData(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    public class ThreadData extends Thread {
        @Override
        public void run() {
            myHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = myHandler.obtainMessage(1);
            myHandler.sendMessage(message);
            super.run();
        }
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbardt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbardt.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdandt = Server.duongdanDT+String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,duongdandt, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                int id = 0;
                String Tendt = "";
                int Giadt = 0;
                String Hinhanhdt = "";
                String Motadt = "";
                int Idspdt = 0;
                if (response != null && response.length() != 2) {
                    lvDt.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            Tendt = jsonObject.getString("tensp");
                            Giadt = jsonObject.getInt("giasp");
                            Hinhanhdt = jsonObject.getString("hinhanhsp");
                            Motadt = jsonObject.getString("motasp");
                            Idspdt = jsonObject.getInt("idsp");
                            arrDt.add(new Sanpham(id,Giadt,Tendt,Hinhanhdt,Motadt,Idspdt));
                            dtadapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    limitData = true;
                    lvDt.removeFooterView(footerview);

            }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("idsp",String.valueOf(iddt));
                return param;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void DtAddControls() {
        toolbardt = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarDt);
        lvDt = (NonScrollListView) findViewById(R.id.lvDt);
        arrDt = new ArrayList<>();
        dtadapter = new DienthoaiAdapter(getApplicationContext(),arrDt);

        lvDt.setAdapter(dtadapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar,null);
        myHandler = new MyHandler();
    }
}
