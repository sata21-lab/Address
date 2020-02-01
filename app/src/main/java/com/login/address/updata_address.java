package com.login.address;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class updata_address extends AppCompatActivity {
    private int position=0;
    private int Address_id=0;
    private Button btn_address;
    private EditText Name_edit;
    private EditText Phone_edit;
    private EditText Address_edit;
    private EditText Type_edit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_address);
        DatabaseHelper dbHelper=new DatabaseHelper(updata_address.this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        btn_address=findViewById(R.id.btn_address);
        Name_edit=findViewById(R.id.name_edit);
        Phone_edit=findViewById(R.id.phone_edit);
        Address_edit=findViewById(R.id.address_edit);
        Type_edit=findViewById(R.id.type_edit);
        Intent intent = getIntent();
        //id=Integer.parseInt(intent.getStringExtra("position"));
        position=intent.getIntExtra("position",1);
        selectAddress(db,position);
        btn_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAddress(db,Name_edit.getText().toString(),
                        Phone_edit.getText().toString(),
                        Address_edit.getText().toString(),
                        Type_edit.getText().toString());
            }
        });
    }
    void updateAddress(SQLiteDatabase db,String name,String phone,String address,String type){
        db.execSQL("UPDATE Address SET Name = '"+name+"', " +
                "Phone = '"+phone+"' ,address='"+address+"'," +
                "type='"+type+"'WHERE id = '"+Address_id+"'");
        Toast.makeText(updata_address.this, "修改成功", Toast.LENGTH_SHORT).show();
        finish();
    }
    void selectAddress(SQLiteDatabase db,int position){
        Cursor cursor=db.rawQuery("select * from Address limit '"+position+"',1",null);
        while (cursor.moveToNext()){
            Name_edit.setText(cursor.getString(cursor.getColumnIndex("Name")));
            Phone_edit.setText(cursor.getString(cursor.getColumnIndex("Phone")));
            Address_edit.setText(cursor.getString(cursor.getColumnIndex("address")));
            Type_edit.setText(cursor.getString(cursor.getColumnIndex("type")));
            Address_id=cursor.getInt(cursor.getColumnIndex("id"));
        }
    }
}
