package com.login.address;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<AddressModel> addressModelList = new ArrayList<>();
    private Toolbar toolbar;
    private DatabaseHelper dbHelper;
    private Button add_address;
    SQLiteDatabase db;
    AddressAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_address=findViewById(R.id.add_address);
        dbHelper=new DatabaseHelper(MainActivity.this);
        db = dbHelper.getWritableDatabase();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AddressAdapter(addressModelList);
        recyclerView.setAdapter(adapter);
        initAddress(db);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setClick();
    }
    private void initAddress(SQLiteDatabase db) {
        addressModelList.clear();
        /*for(int i = 0; i < 10; i++) {
            AddressModel address = new AddressModel("姓名", "123456789","公司","上海市松江区");
            addressModelList.add(address);
        }*/
        Cursor cursor=db.rawQuery("select * from Address order by default_address desc",null);
        while (cursor.moveToNext()){
            AddressModel address = new AddressModel((cursor.getString(cursor.getColumnIndex("id"))),
                    cursor.getString(cursor.getColumnIndex("Name"))
                    , format(cursor.getString(cursor.getColumnIndex("Phone"))),
                    cursor.getString(cursor.getColumnIndex("address")),
                    cursor.getString(cursor.getColumnIndex("type")));
            addressModelList.add(address);
        }
        adapter.notifyDataSetChanged();
    }
    String format(String string){
        StringBuffer buffer = new StringBuffer(string);
        buffer.replace(3,7,"****");
        return buffer.toString();
    }
    void setClick(){//添加地址
        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Add_address.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("方法", "方法被执行");
        initAddress(db);
    }
}
