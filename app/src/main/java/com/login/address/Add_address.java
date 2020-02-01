package com.login.address;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Type;
import java.util.jar.Attributes;

public class Add_address extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private Button btn_address;
    private EditText Name_edit;
    private EditText Phone_edit;
    private EditText Address_edit;
    private EditText Type_edit;
    private boolean isdefault=false;
    private Switch aSwitch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_address);
        aSwitch=findViewById(R.id.default_switch);
        btn_address=findViewById(R.id.btn_address);
        Name_edit=findViewById(R.id.name_edit);
        Phone_edit=findViewById(R.id.phone_edit);
        Address_edit=findViewById(R.id.address_edit);
        Type_edit=findViewById(R.id.type_edit);
        dbHelper=new DatabaseHelper(Add_address.this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isdefault=isChecked;
            }
        });
        btn_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isdefault){//如果添加默认地址
                    db.execSQL("UPDATE Address SET default_address = 0 WHERE default_address = 1");
                    ContentValues values= new ContentValues();
                    values.put("Name",Name_edit.getText().toString());
                    values.put("Phone",Phone_edit.getText().toString());
                    values.put("address",Address_edit.getText().toString());
                    values.put("type",Type_edit.getText().toString());
                    values.put("default_address",1);
                    db.insert("address",null,values);
                    Toast.makeText(Add_address.this, "添加成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{//添加普通地址
                    ContentValues values= new ContentValues();
                    values.put("Name",Name_edit.getText().toString());
                    values.put("Phone",Phone_edit.getText().toString());
                    values.put("address",Address_edit.getText().toString());
                    values.put("type",Type_edit.getText().toString());
                    values.put("default_address",0);
                    db.insert("address",null,values);
                    Toast.makeText(Add_address.this, "添加成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
    }
    public void getType(){

    }
}
