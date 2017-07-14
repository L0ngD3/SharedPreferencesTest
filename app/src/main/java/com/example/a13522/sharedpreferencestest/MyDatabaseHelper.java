package com.example.a13522.sharedpreferencestest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by 13522 on 2017/7/14.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREAT_BOOK = "create table Book( auther text," +
            "price real ," +
            "pages integer," +
            "name text)";
    public static final String CREAT_CATEGORY = "create table Category(id integer primary key autoincrement," +
            "category_name text," +
            "category_code integer)";
    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_BOOK);
        db.execSQL(CREAT_CATEGORY);
        Toast.makeText(mContext,"成功",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //如果有重名的数据库就删除
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }
}
