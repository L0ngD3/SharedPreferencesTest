package com.example.a13522.sharedpreferencestest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public TextView tv_show;
    private MyDatabaseHelper dbHelpe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button seave = (Button) findViewById(R.id.seave);
        Button add = (Button) findViewById(R.id.add);
        tv_show = (TextView) findViewById(R.id.tv_show);

        //创建数据库
        dbHelpe = new MyDatabaseHelper(this, "BookStore.db", null, 2);
        seave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建数据库
                dbHelpe.getWritableDatabase();
            }
        });
        //添加内容
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelpe.getReadableDatabase();
                ContentValues values = new ContentValues();
                values.put("auther", "haha");
                values.put("price", 19.9);
                values.put("pages", 90);
                values.put("name", "Tom");
                db.insert("Book", null, values);
                values.clear();

                values.put("auther", "hehe");
                values.put("price", 30.7);
                values.put("pages", 20);
                values.put("name", "LiSi");
                db.insert("Book", null, values);
                Toast.makeText(getApplicationContext(), "Succes", Toast.LENGTH_SHORT).show();
            }
        });

        //更新数据
        Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelpe.getReadableDatabase();
                ContentValues values = new ContentValues();//重新组装
                values.put("auther", "aaaa");
                db.update("Book", values, "name = ?", new String[]{"LiSi"});
            }
        });

        //查询表
        Button find = (Button) findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelpe.getReadableDatabase();
                //查询表中内容
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String auther = cursor.getString(cursor.getColumnIndex("auther"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        Float price = cursor.getFloat(cursor.getColumnIndex("price"));
                        Log.d("aaa", name);
                        Log.d("aaa", auther);
                        Log.d("aaa", String.valueOf(pages));
                        Log.d("aaa", String.valueOf(price));
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }

        });







     /*   seave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data",0).edit();
                //添加内容的类型
                editor.putString("name","Tom");//字符串
                editor.putInt("Phone",110);//int类型
                editor.putBoolean("married",false);//boolean类型
                editor.apply();//调用方法应用数据
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences read = getSharedPreferences("data",0);
             String name= read.getString("name","");
                String name2= read.getString("name2","没有");
              int phone=  read.getInt("Phone",0);
                boolean married = read.getBoolean("married",true);

                Log.d("a","name is "+name);
                Log.d("a","name2 is "+name2);
                Log.d("a","phone is "+phone);
                Log.d("a","married is "+married);
            }
        });*/


    }
}
