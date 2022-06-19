package com.se151536_phanvannam.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "sqllite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE user_management(username Text primary key not null," +
                " password Text)";
        db.execSQL(sql);
        db.execSQL("INSERT INTO user_management values('admin','admin')");
        db.execSQL("INSERT INTO user_management values('nampv','123456')");

        sql = "CREATE TABLE users(id int primary key not null," +
                " username Text not null," +
                " first_name Text not null," +
                " last_name Text not null," +
                " email Text not null," +
                " password Text)";
        db.execSQL(sql);
        db.execSQL("INSERT INTO users values(1,'admin', 'Admin', 'Management', 'admin@domain.com', 'admin')");
        db.execSQL("INSERT INTO users values(2,'nampv', 'Nam', 'Phan', 'nam@domain.com', '123456')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS user_management");
        onCreate(db);
    }
}
