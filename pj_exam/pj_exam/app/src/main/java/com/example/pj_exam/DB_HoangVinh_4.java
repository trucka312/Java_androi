package com.example.pj_exam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DB_HoangVinh_4 extends SQLiteOpenHelper {
    private static final String TableName = "SinhVien_4";
    private static final String Id = "Id";
    private static final String Sid = "Sid";
    private static final String Name = "Name";
    private static final String Math = "Math";
    private static final String Physical = "Physical";
    private static final String Chemistry = "Chemistry";

    public DB_HoangVinh_4(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreate = "Create table if not exists " + TableName + "(" + Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Sid + " Text," + Name + " Text, " + Math + " double, " + Physical + " double, " + Chemistry + " double)";
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("" + " Drop table if exists " + TableName);
        onCreate(sqLiteDatabase);
    }
    public void addSinhVien(SinhVien sinhVien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Sid, sinhVien.getsId());
        values.put(Name, sinhVien.getName());
        values.put(Math, sinhVien.getMath());
        values.put(Physical, sinhVien.getPhysical());
        values.put(Chemistry, sinhVien.getChemistry());
        db.insert(TableName, null, values);
        db.close();
    }
    public void deleteSinhVien(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from " + TableName + " WHERE Id=" + id;
        db.execSQL(sql);
        db.close();
    }

    public void deleteAllSinhVien(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from " + TableName ;
        db.execSQL(sql);
        db.close();
    }

    public ArrayList<SinhVien> getAllSinhVien() {
        ArrayList<SinhVien> list = new ArrayList<>();
        String sql = "Select * from " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null)
            while (cursor.moveToNext()) {
                SinhVien sinhVien = new SinhVien();
                sinhVien.setId(cursor.getInt(0));
                sinhVien.setsId(cursor.getString(1));
                sinhVien.setName(cursor.getString(2));
                sinhVien.setMath(cursor.getDouble(3));
                sinhVien.setPhysical(cursor.getDouble(4));
                sinhVien.setChemistry(cursor.getDouble(5));
                list.add(sinhVien);
            }
        return list;
    }
}
