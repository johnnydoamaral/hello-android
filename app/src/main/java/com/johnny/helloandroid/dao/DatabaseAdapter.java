package com.johnny.helloandroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private String whereClause;
    private String[] whereArgs;
    private ContentValues values;

    public DatabaseAdapter(Context context) {
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public DatabaseAdapter open() {
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public List<String> getAllNames() {
        List<String> names = new ArrayList<>();
        Cursor cursor = getAllEntries();
        if (cursor.moveToFirst()) {
            do {
                names.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return names;
    }

    public long insertName(String name) {
        values = new ContentValues(); //Basically a Map
        values.put("name", name); //Key-pair: DB column name, value to insert
        return database.insert("names", null, values);
    }

    public int updateName(String newName) {
        whereClause = "name = ?";
        whereArgs = new String[1];
        whereArgs[0] = newName;
        values = new ContentValues();
        values.put("name", newName);
        return database.update("names", values, whereClause, whereArgs);
    }

    public int deleteName(String name) {
        whereClause = "name = ?";
        whereArgs = new String[1];
        whereArgs[0] = name;
        return database.delete("names", whereClause, whereArgs);
    }

    public int deleteAllNames() {
        return database.delete("names", null, null);
    }

    public boolean exists(String name) {
        Cursor cursor = database.rawQuery("SELECT NAME FROM NAMES WHERE NAME=?", new String[]{name});
        boolean result = cursor.getCount() >= 1;
        cursor.close();
        return result;
    }

    private Cursor getAllEntries() {
        String columns[] = new String[1];
        columns[0] = "name";
        return database.query("names", columns, null, null, null, null, null);
    }
}
