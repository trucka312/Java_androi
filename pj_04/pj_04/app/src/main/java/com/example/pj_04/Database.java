package com.example.pj_04;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public static final String DB_NAME = "StudentDB";
    public static final  int DB_VERSION = 1;
    public static final String TB_NAME = "Student";
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String POINT = "POINT";

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String querry  =
                "CREATE TABLE " + TB_NAME + "(" +
                        ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        NAME +" TEXT NOT NULL, " +
                        POINT + " float )";

        sqLiteDatabase.execSQL(querry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DELETE FROM " + TB_NAME);
        sqLiteDatabase.close();
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TB_NAME);
        db.close();
    }
    public void  deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TB_NAME + " WHERE " + ID + " = " + id);
        db.close();
    }
    public void updateStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, student.getName());
        contentValues.put(POINT, student.getPoint());
        db.update(TB_NAME, contentValues, ID + " = ?" , new String[]{String.valueOf(student.getID())});

        //db.execSQL("UPDATE FROM " + TB_NAME + " SET ("+NAME+","+SINGER+","+TIME+") " + ID + " = " + id);
        db.close();
    }
    public void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, student.getName());
        contentValues.put(POINT, student.getPoint());
        db.insert(TB_NAME, null, contentValues);
        db.close();
    }
    public ArrayList<Student> searchBySinger(String name){
        ArrayList<Student> list = new ArrayList<>();
        String querry = "SELECT * FROM " + TB_NAME + " WHERE " + NAME + "=" + "'" + name + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(querry, null);
        if(cursor.moveToFirst()){
            do{
                Student student = new Student();
                student.setID(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setPoint(cursor.getFloat(2));
                list.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    public ArrayList<Student> getAll(){
        ArrayList<Student> list = new ArrayList<>();
        String querry = "SELECT * FROM " + TB_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(querry, null);
        if(cursor.moveToFirst()){
            do{
                Student student = new Student();
                student.setID(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setPoint(cursor.getFloat(2));
                list.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

}
