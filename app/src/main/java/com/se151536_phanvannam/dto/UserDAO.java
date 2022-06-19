package com.se151536_phanvannam.dto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.se151536_phanvannam.helper.DatabaseHelper;
import com.se151536_phanvannam.users.Users;

public class UserDAO {
    private DatabaseHelper databaseHelper;

    public UserDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public boolean checkUser (Users users) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        Cursor cs = database.rawQuery("Select * from users where username = ? and password = ?", new String[]{
                users.getUserName(), users.getPassword()
        });
        return cs.getCount() > 0;
    }



    //delete
    public boolean delete(String  username){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        ContentValues values = new ContentValues();

        long row = db.delete("users","username = ?",
                new String[]{username});
        return row>0;
    }
}
