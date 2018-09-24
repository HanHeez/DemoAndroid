package com.gtv.hanhee.webservicedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gtv.hanhee.adapter.SinhVienAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView lvSinhvien;
    ArrayList<SinhVien> dsSinhvien;
    SinhVienAdapter adapterSinhvien;
    String urlDelete = "https://hanhee.000webhostapp.com/delete.php";
    String urlData = "https://hanhee.000webhostapp.com/getdata.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        GetData(urlData);
    }

    private void addControls() {
        lvSinhvien = (ListView) findViewById(R.id.lvSinhvien);
        dsSinhvien = new ArrayList<>();
        adapterSinhvien = new SinhVienAdapter(
                MainActivity.this,R.layout.dong_sinh_vien,
                dsSinhvien
        );
        lvSinhvien.setAdapter(adapterSinhvien);

    }

    private void GetData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                dsSinhvien.clear();
                for (int i=0;i < response.length();i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        dsSinhvien.add(new SinhVien(
                                object.getInt("ID"),
                                object.getString("HoTen"),
                                object.getInt("NamSinh"),
                                object.getString("DiaChi")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                adapterSinhvien.notifyDataSetChanged();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_student, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAddStudent) {
            startActivity(new Intent(MainActivity.this,AddSinhVienActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void DeleteStudent(final int idSV) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlDelete,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            Toast.makeText(MainActivity.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                            GetData(urlData);
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
                param.put("idCuaSV", String.valueOf(idSV));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
}
