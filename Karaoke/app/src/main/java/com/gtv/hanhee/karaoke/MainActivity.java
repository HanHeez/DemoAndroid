package com.gtv.hanhee.karaoke;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.gtv.hanhee.adapter.MusicAdapter;
import com.gtv.hanhee.model.Music;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvBaihatgoc;
    public static ArrayList<Music> dsBaihatgoc;
    public static MusicAdapter adapterBaihatgoc;

     ListView lvBaihatyeuthich;
    public static ArrayList<Music> dsBaihatyeuthich;
    public static MusicAdapter adapterBaihatyeuthich;

    TabHost tabHost;

    public static String DADABASE_NAME="Arirang.sqlite";
    String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XuLySaoChepCSDLAssetVaoHeThongMobile();

        addControls();
        addEvents();
    }

    private void XuLySaoChepCSDLAssetVaoHeThongMobile() {
        File dbFile = getDatabasePath(DADABASE_NAME);
        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAssets();
                Toast.makeText(this, "Sao chep CDSL thanh cong", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void CopyDataBaseFromAssets() {
        try {
            InputStream myInput = getAssets().open(DADABASE_NAME);
            String outFileName = LayDuongDanLuuTru();
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists()) {
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(outFileName);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer,0,length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();

        }
        catch (Exception e) {
            Log.e("Loi sao chep",e.toString());
        }
    }


    private String LayDuongDanLuuTru() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DADABASE_NAME;
    }

    private void addEvents() {
        xulyhienthibaihatgoc();
        xulyhienthibaihatyeuthich();
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if (s.equalsIgnoreCase("t1")) {
                    xulyhienthibaihatgoc();
                } else if (s.equalsIgnoreCase("t2")) {
                    xulyhienthibaihatyeuthich();
                }
            }
        });

    }

    private void xulyhienthibaihatyeuthich() {
//        dsBaihatyeuthich.clear();
//        for (Music music: dsBaihatgoc) {
//            if (music.isThich()) dsBaihatyeuthich.add(music);
//        }
//        adapterBaihatyeuthich.notifyDataSetChanged();
        database = openOrCreateDatabase(DADABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.query("ArirangSongList",null,"YEUTHICH=?",
                new String[]{"1"},null,null,null);

        dsBaihatyeuthich.clear();
        while (cursor.moveToNext()) {
            String mabh = cursor.getString(0);
            String tenbh = cursor.getString(1);
            String casi = cursor.getString(3);
            int yeuthich = cursor.getInt(5);
            Music music = new Music();
            music.setMa(mabh);
            music.setTen(tenbh);
            music.setCasi(casi);
            music.setThich(yeuthich==1);
            dsBaihatyeuthich.add(music);
        }
        cursor.close();
        adapterBaihatyeuthich.notifyDataSetChanged();
    }

    private void xulyhienthibaihatgoc() {
        database = openOrCreateDatabase(DADABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.query("ArirangSongList",null,null,null,null,null,null);

        dsBaihatgoc.clear();
        while (cursor.moveToNext()) {
            String mabh = cursor.getString(0);
            String tenbh = cursor.getString(1);
            String casi = cursor.getString(3);
            int yeuthich = cursor.getInt(5);
            Music music = new Music();
            music.setMa(mabh);
            music.setTen(tenbh);
            music.setCasi(casi);
            music.setThich(yeuthich==1);
            dsBaihatgoc.add(music);
        }
        cursor.close();
        adapterBaihatgoc.notifyDataSetChanged();
    }

    private void addControls() {

        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("",getResources().getDrawable(R.drawable.music1));
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("",getResources().getDrawable(R.drawable.musicfavorite));
        tabHost.addTab(tab2);


        lvBaihatgoc = (ListView) findViewById(R.id.lvBaihatgoc);
        lvBaihatyeuthich = (ListView) findViewById(R.id.lvBaihatyeuthich);
        dsBaihatgoc = new ArrayList<>();
        adapterBaihatgoc = new MusicAdapter(
                MainActivity.this,
                R.layout.item, dsBaihatgoc
        );
        lvBaihatgoc.setAdapter(adapterBaihatgoc);

        dsBaihatyeuthich = new ArrayList<>();
        adapterBaihatyeuthich = new MusicAdapter(
                MainActivity.this,
                R.layout.item, dsBaihatyeuthich
        );
        lvBaihatyeuthich.setAdapter(adapterBaihatyeuthich);

        //giaLapBaiHat();

    }

//    private void giaLapBaiHat() {
//        dsBaihatgoc.add(new Music("55555","Khong yeu dung noi loi cay dang","Ngot Ngao",true));
//        dsBaihatgoc.add(new Music("66666","Long Me","ABC",true));
//        dsBaihatgoc.add(new Music("22222","Lac Troi","Tung Son",false));
//        adapterBaihatgoc.notifyDataSetChanged();
//
//    }
}
