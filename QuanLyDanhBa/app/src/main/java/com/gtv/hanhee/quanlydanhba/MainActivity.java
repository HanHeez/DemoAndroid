package com.gtv.hanhee.quanlydanhba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String DADABASE_NAME="dbContact.sqlite";
    String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database = null;

    ListView lvDanhba;
    ArrayList<String> dsdanhba;
    ArrayAdapter<String> adapterDanhba;
    Button btnThemdanhba,btnChinhsua,btnXoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XuLySaoChepCSDLAssetVaoHeThongMobile();
        
        addControls();
        addEvents();
        ShowAllContactOnListView();
    }

    private void ShowAllContactOnListView() {
        // Buoc1: Mo CSDL truoc
        database = openOrCreateDatabase(DADABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.query("Contact",null,null,null,null,null,null);
        Cursor cursor1 = database.rawQuery("select * from Contact", null);
        dsdanhba.clear();
        while (cursor.moveToNext()) {
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String phone = cursor.getString(2);
            dsdanhba.add(ma+"+"+ten+"\n"+phone);
        }
        cursor.close();
        adapterDanhba.notifyDataSetChanged();
    }

    private void addEvents() {
        btnThemdanhba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyThemdanhba();
            }
        });
        btnChinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyChinhsuadanhba();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyXoaDanhba();
            }
        });
    }

    private void xulyXoaDanhba() {
        database.delete("Contact","ma=?",new String[]{"2"});
        ShowAllContactOnListView();
    }

    private void xulyChinhsuadanhba() {
        ContentValues row = new ContentValues();
        row.put("Ten","Hoang Van Huy");
        database.update("Contact",row,"ten=?", new String[]{"Nguyen Van Teo"});
        ShowAllContactOnListView();
    }

    private void xulyThemdanhba() {
        ContentValues row = new ContentValues();
        row.put("Ma",113);
        row.put("Ten", "Nguyen Huynh Long");
        row.put("Phone", "098723456");
        long r= database.insert("Contact",null,row);

        Toast.makeText(MainActivity.this, "Them moi 1 contact", Toast.LENGTH_SHORT).show();
        ShowAllContactOnListView();

    }

    private void addControls() {
        lvDanhba = (ListView) findViewById(R.id.lvDanhba);
        dsdanhba = new ArrayList<>();
        adapterDanhba = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                dsdanhba
        );
        lvDanhba.setAdapter(adapterDanhba);

        btnThemdanhba = (Button) findViewById(R.id.btnThemdanhba);
        btnChinhsua = (Button) findViewById(R.id.btnChinhsua);
        btnXoa = (Button) findViewById(R.id.btnXoa);
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

}
