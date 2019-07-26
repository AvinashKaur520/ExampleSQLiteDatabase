package com.example.databaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHandler extends SQLiteOpenHelper
{
    private static final int DB_Version = 1;
    private static final String DB_Name = "userDB";
    private static final String TB_Name = "userDetails";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String LOC = "location";
    private static final String DESC = "description";



    public DBHandler(Context context)
    {
        super(context,DB_Name,null,DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i("TAG","onCreate: "+TB_Name);
        String Create = "CREATE TABLE " + TB_Name + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT," + LOC + " TEXT," + DESC + " TEXT" + ")";
        db.execSQL(Create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TB_Name);
        onCreate(db);
    }

    void insertData(String name,String loc,String des)
    {
        SQLiteDatabase data = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("location",loc);
        values.put("description",des);

        long insrt = data.insert(TB_Name,null,values);

        data.close();
    }

    public ArrayList<HashMap<String,String>> GetData()
    {
        SQLiteDatabase data = this.getWritableDatabase();

        ArrayList<HashMap<String,String>> myArray = new ArrayList<>();

        String query = "SELECT name, location, description FROM "+ TB_Name;

        Cursor cursor = data.rawQuery(query,null);

        while(cursor.moveToNext())
        {
            HashMap<String,String> myMap = new HashMap<>();

            myMap.put("name",cursor.getString(cursor.getColumnIndex(NAME)));
            myMap.put("location",cursor.getString(cursor.getColumnIndex(LOC)));
            myMap.put("description",cursor.getString(cursor.getColumnIndex(DESC)));

            myArray.add(myMap);
        }

        return myArray;
    }

    public ArrayList<HashMap<String,String>> GetDataById(int id)
    {
        SQLiteDatabase data = this.getWritableDatabase();

        ArrayList<HashMap<String,String>> myarray = new ArrayList<>();

        String query = "SELECT name, location, description FROM" + TB_Name;

        Cursor cursor = data.query(TB_Name, new String [] {NAME,LOC,DESC}, ID + "=?" , new String[] {String.valueOf(id)},null,null,null,null);

        if(cursor.moveToNext())
        {
            HashMap<String,String> myMap = new HashMap<>();

            myMap.put("name",cursor.getString(cursor.getColumnIndex(NAME)));
            myMap.put("location",cursor.getString(cursor.getColumnIndex(LOC)));
            myMap.put("description",cursor.getString(cursor.getColumnIndex(DESC)));

            myarray.add(myMap);
        }

        return myarray;
    }

    public void DeleteData(int id)
    {
        SQLiteDatabase data = this.getWritableDatabase();

        data.delete(TB_Name, ID + "=?" , new String[] {String.valueOf(id)});

        data.close();
    }

    public int UpdateData(String location,String description,int id)
    {
        SQLiteDatabase data = this.getWritableDatabase();

        ContentValues content = new ContentValues();

        content.put("location",location);
        content.put("description",description);

        int count = data.update(TB_Name, content, ID + "=?" , new String[] {String.valueOf(id)});

        return count;
    }
}
