package com.gtv.hanhee.projectungdungbanhang.activity;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gtv.hanhee.projectungdungbanhang.R;
import com.gtv.hanhee.projectungdungbanhang.ultil.CheckConnection;
import com.gtv.hanhee.projectungdungbanhang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinKhachHangActivity extends AppCompatActivity {
    EditText txtTenkh, txtSodtKh, txtEmail;
    Button btnXacnhan, btnTrove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        AddControls();
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            ButtonEvent();
        } else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
        }

    }

    private void ButtonEvent() {

        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten = txtTenkh.getText().toString().trim();
                final String sdt = txtSodtKh.getText().toString().trim();
                final String email = txtEmail.getText().toString().trim();
                if (ten.length()>0 && sdt.length()>0 && email.length()>0) {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanDonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {

                            if (Integer.parseInt(madonhang)> 0) {
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(Request.Method.POST, Server.duongdanchitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("1")) {
                                            MainActivity.arrGiohang.clear();
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn đã thêm dữ liệu giỏ hàng thành công");
                                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Mời bạn tiếp tục mua hàng");
                                        } else {
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Dữ liệu giỏ hàng bị lỗi");
                                        }

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for (int i =0; i<MainActivity.arrGiohang.size();i++) {
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("madonhang",madonhang);
                                                jsonObject.put("masanpham",MainActivity.arrGiohang.get(i).getIdsp());
                                                jsonObject.put("tensanpham",MainActivity.arrGiohang.get(i).getTensp());
                                                jsonObject.put("giasanpham",MainActivity.arrGiohang.get(i).getGiasp());
                                                jsonObject.put("soluongsanpham",MainActivity.arrGiohang.get(i).getSoluongsp());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String, String> hashMap = new HashMap<>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("tenkhachhang", ten);
                            hashMap.put("sodienthoai", sdt);
                            hashMap.put("email", email);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);

                } else {
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại đữ liệu");
                }
            }
        });


    }

    private void AddControls() {
        txtTenkh = (EditText) findViewById(R.id.txtTenKh);
        txtSodtKh = (EditText) findViewById(R.id.txtSodtKh);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        btnXacnhan = (Button) findViewById(R.id.btnXacnhan);
        btnTrove = (Button) findViewById(R.id.btnTrove);
    }
}
