package com.gtv.hanhee.webservicedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {

    EditText txtHotenEdit, txtDiachiEdit, txtNamsinhEdit;
    Button btnCapnhat, btnHuy;

    int id = 1;
    String urlUpdate = "https://hanhee.000webhostapp.com/update.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();
        SinhVien sinhVien1 = (SinhVien) intent.getSerializableExtra("dataSinhVien");
        Toast.makeText(this, sinhVien1.getHoten(), Toast.LENGTH_SHORT).show();

        AddControls();

        id = sinhVien1.getId();
        txtHotenEdit.setText(sinhVien1.getHoten());
        txtDiachiEdit.setText(sinhVien1.getDiachi());
        txtNamsinhEdit.setText(sinhVien1.getNamsinh()+"");

        btnCapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = txtHotenEdit.getText().toString().trim();
                String namsinh = txtNamsinhEdit.getText().toString().trim();
                String diachi = txtDiachiEdit.getText().toString();
                if (hoten.matches("")|| namsinh.equals("")|| diachi.length()==0) {
                    Toast.makeText(UpdateActivity.this, "Vui Long nhap du thong tin", Toast.LENGTH_SHORT).show();
                } else {
                    CapNhatSinhVien(urlUpdate);
                }
            }
        });
    }

    private  void CapNhatSinhVien(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            Toast.makeText(UpdateActivity.this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(UpdateActivity.this, "Loi cap nhat", Toast.LENGTH_SHORT).show();
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> param = new HashMap<>();
                param.put("idSV", String.valueOf(id));
                param.put("hotenSV", txtHotenEdit.getText().toString().trim());
                param.put("namsinhSV", txtNamsinhEdit.getText().toString().trim());
                param.put("diachiSV", txtDiachiEdit.getText().toString().trim());
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AddControls() {
        txtHotenEdit = (EditText) findViewById(R.id.txtHotenedit);
        txtDiachiEdit = (EditText) findViewById(R.id.txtDiachiedit);
        txtNamsinhEdit = (EditText) findViewById(R.id.txtNamsinhedit);
        btnCapnhat = (Button) findViewById(R.id.btnCapnhat);
        btnHuy = (Button) findViewById(R.id.btnHuy);
    }
}
