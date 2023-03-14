package com.example.mbtifriends;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.text.Editable;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DatabaseManager extends SQLiteOpenHelper {

    public DatabaseManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE people (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'first_name' TEXT, 'last_name' TEXT, 'type' TEXT, 'gender' TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS people");
        onCreate(sqLiteDatabase);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("Range")
    public ArrayList<Person> getPeopleByType(String type){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor result = db.rawQuery("SELECT * FROM people WHERE type=?" ,  new String [] {type});

        ArrayList<Person> people = new ArrayList<>();

        while(result.moveToNext()){
            people.add( new Person(
                    result.getString(result.getColumnIndex("first_name")),
                    result.getString(result.getColumnIndex("last_name")),
                    result.getString(result.getColumnIndex("type")),
                    result.getString(result.getColumnIndex("gender")),
                    result.getString(result.getColumnIndex("_id"))
            ));
        }

        return people;

    }

    public boolean checkIfPersonExists(String first_name, String last_name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM people WHERE first_name=? AND last_name=?" ,  new String [] {first_name, last_name});
        if(result.getCount() > 0) return true;
        else return false;
    }

    public boolean updatePerson(Person person){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", person.first_name);
        contentValues.put("last_name", person.last_name);
        contentValues.put("type", person.type);
        contentValues.put("gender", person.gender);

         return db.update("people", contentValues, "_id = ?", new String[]{person.getId()}) > 0;
    }

    public boolean deletePerson(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete("people", "_id=?", new String[]{id}) > 0;
    }


    public Boolean insertPerson(Person person){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", person.first_name);
        contentValues.put("last_name", person.last_name);
        contentValues.put("type", person.type);
        contentValues.put("gender", person.gender);

        long result = db.insert("people", null, contentValues);
        db.close();

        if(result == -1) return false;
        else return true;
    }

    @SuppressLint("Range")
    public ArrayList<Person> getPeople(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM people", null);

        ArrayList<Person> people = new ArrayList<>();

        while(result.moveToNext()){
            people.add( new Person(
                    result.getString(result.getColumnIndex("first_name")),
                    result.getString(result.getColumnIndex("last_name")),
                    result.getString(result.getColumnIndex("type")),
                    result.getString(result.getColumnIndex("gender")),
                    result.getString(result.getColumnIndex("_id"))
            ));
        }
        return people;
    }

}
