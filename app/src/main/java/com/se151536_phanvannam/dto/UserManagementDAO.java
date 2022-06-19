package com.se151536_phanvannam.dto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.se151536_phanvannam.helper.DatabaseHelper;
import com.se151536_phanvannam.users.UserManagement;
import com.se151536_phanvannam.users.Users;

import java.util.ArrayList;

public class UserManagementDAO {
    private DatabaseHelper databaseHelper;

    public UserManagementDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public boolean checkUser(Users users) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        Cursor cs = database.rawQuery("Select * from user_management where username = ? and password = ?", new String[]{
                users.getUserName(), users.getPassword()
        });
        return cs.getCount() > 0;
    }

    public ArrayList<UserManagement> readAll() {
        ArrayList<UserManagement> data = new ArrayList<>();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // tao con tro de lay du lieu
        Cursor cs = db.rawQuery("select * from user_management", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            String username = cs.getString(0);
            String password = cs.getString(1);
            data.add(new UserManagement(username, password));
            cs.moveToNext();
        }
        cs.close();
        return data;
    }

    public boolean add(UserManagement userManagement) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", userManagement.getUsername());
        values.put("password", userManagement.getPassword());
        long row = db.insert("user_management", null, values);
        return row > 0;
    }

    public boolean delete(String username) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        ContentValues values = new ContentValues();

        long row = db.delete("users", "username = ?",
                new String[]{username});
        return row > 0;
    }
}
