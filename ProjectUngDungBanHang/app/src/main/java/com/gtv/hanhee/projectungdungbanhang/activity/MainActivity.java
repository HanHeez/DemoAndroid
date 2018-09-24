package com.gtv.hanhee.projectungdungbanhang.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.gtv.hanhee.projectungdungbanhang.R;
import com.gtv.hanhee.projectungdungbanhang.adapter.LoaispAdapter;
import com.gtv.hanhee.projectungdungbanhang.adapter.SpAdapter;
import com.gtv.hanhee.projectungdungbanhang.model.Giohang;
import com.gtv.hanhee.projectungdungbanhang.model.Loaisp;
import com.gtv.hanhee.projectungdungbanhang.model.Sanpham;
import com.gtv.hanhee.projectungdungbanhang.model.SpacesItemDecoration;
import com.gtv.hanhee.projectungdungbanhang.ultil.CheckConnection;
import com.gtv.hanhee.projectungdungbanhang.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<Loaisp> arrLoaisp;
    LoaispAdapter loaispadapter;
    int id = 0;
    String tenloaisp="";
    String hinhanhloaisp="";
    ArrayList<Sanpham> arrSanpham;
    SpAdapter spAdapter;
    public static ArrayList<Giohang> arrGiohang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddControls();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            ActionBar();
            ActionViewFlipper();
            GetDuLieuLoaiSanPham();
            GetDuLieuSPMoiNhat();
            CatchOnItemListView();


        } else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
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

    private void CatchOnItemListView() {

        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Ban hay kiem tra lai ket noi");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this,DienThoaiActivity.class);
                            intent.putExtra("idloaisanpham",arrLoaisp.get(position).getId());
                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Ban hay kiem tra lai ket noi");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this,LaptopActivity.class);
                            intent.putExtra("idloaisanpham",arrLoaisp.get(position).getId());
                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Ban hay kiem tra lai ket noi");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this,LienHeActivity.class);

                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Ban hay kiem tra lai ket noi");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this,ThongTinActivity.class);

                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Ban hay kiem tra lai ket noi");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });

    }


    private void GetDuLieuSPMoiNhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanSPmoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int ID = 0;
                    String tensanpham = "";
                    int giasanpham = 0;
                    String hinhanhsanpham = "";
                    String motasanpham = "";
                    int IDsanpham = 0;
                    for (int i = 0; i<response.length();i++) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            tensanpham = jsonObject.getString("tensp");
                            giasanpham = jsonObject.getInt("giasp");
                            hinhanhsanpham = jsonObject.getString("hinhanhsp");
                            motasanpham = jsonObject.getString("motasp");
                            IDsanpham = jsonObject.getInt("idsp");
                            arrSanpham.add(new Sanpham(ID, giasanpham, tensanpham, hinhanhsanpham, motasanpham, IDsanpham));
                            spAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    // Volley
    private void GetDuLieuLoaiSanPham() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdanloaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i = 0; i < response.length();i ++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenloaisp = jsonObject.getString("tenloaisp");
                            hinhanhloaisp = jsonObject.getString("hinhanhloaisp");
                            arrLoaisp.add( new Loaisp(id,tenloaisp,hinhanhloaisp));
                            loaispadapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    arrLoaisp.add(new Loaisp(0,"Liên hệ","http://quanlegging.com/wp-content/uploads/2015/07/Call-icon-blue.png"));
                    arrLoaisp.add(new Loaisp(0,"Thông tin","http://support.casio.com/global/vi/wat/manual/5413_vi/fig/App_icon_02_VPCVILcirwnbhj.png"));
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> arrQuangcao = new ArrayList<>();
        arrQuangcao.add("https://anh.24h.com.vn/upload/4-2014/images/2014-12-20/1419036069-115490111.jpg");
        arrQuangcao.add("https://bachlongmobile.com/media/clnews/1505271725804577786.jpg");
        arrQuangcao.add("https://znews-photo-td.zadn.vn/w660/Uploaded/neg_ysfyrns/2015_09_27/12063772_10203584356848862_9179263578310793718_n_1.jpg");
        arrQuangcao.add("https://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/AnhNQ/2510/4-lam-tuong-ve-iphone-x-ma-nhieu-nguoi-mac-phai-1.jpg");
        for (int i = 0; i < arrQuangcao.size();i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(arrQuangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);

    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void AddControls() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        recyclerViewmanhinhchinh = (RecyclerView) findViewById(R.id.recycleView);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        listViewmanhinhchinh = (ListView) findViewById(R.id.lvManhinhchinh);
        arrLoaisp = new ArrayList<>();
        arrLoaisp.add(0, new Loaisp(0,"Trang chính", "http://icons.iconarchive.com/icons/graphicloads/colorful-long-shadow/256/Home-icon.png"));
        loaispadapter = new LoaispAdapter(arrLoaisp,getApplicationContext());

        listViewmanhinhchinh.setAdapter(loaispadapter);
        arrSanpham = new ArrayList<>();
        spAdapter = new SpAdapter(getApplicationContext(),arrSanpham);
//        recyclerViewmanhinhchinh.setHasFixedSize(true);
        SpacesItemDecoration decoration = new SpacesItemDecoration(10);
        recyclerViewmanhinhchinh.addItemDecoration(decoration);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerViewmanhinhchinh.setLayoutManager(staggeredGridLayoutManager);

        recyclerViewmanhinhchinh.setAdapter(spAdapter);
        if (arrGiohang != null) {

        } else {
            arrGiohang = new ArrayList<>();
        }

    }
}
