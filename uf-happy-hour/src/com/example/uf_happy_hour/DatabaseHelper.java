package com.example.uf_happy_hour;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "UFHappyHourDB";
 
    // Contacts table name
    private static final String TABLE_BARS = "BarsTable";
    private static final String TABLE_SPECIALS = "SpecialsTable";
 
    // Table Columns names
    
	static final String colName = "BarName";
	static final String colAddress = "BarAddress";
	static final String colPhone = "PhoneNumber";
	static final String colType = "TypeOfVenue";
	static final String colHoursOpen = "HoursOpen";
	static final String colAlcohol = "Alcohol";
	static final String colSpecials = "Specials";
	static final String colAgeLimit = "AgeLimit";
	static final String colFood = "Food";
	
	static final String colNameb = "BarName";
	static final String colLoc = "BarLoc";
	static final String colDay = "Day";
	static final String colHHbegin = "HappyHourBegin";
	static final String colHHend = "HappyHourEnd";
	static final String colSpec = "Special";
	static final String colId = "ID";
	
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BARS_TABLE = "CREATE TABLE " + TABLE_BARS + "("
                + colName + " TEXT PRIMARY KEY," + colAddress + " TEXT,"
                + colPhone + " TEXT," + colType + " TEXT," 
                + colHoursOpen + " TEXT," + colAlcohol + " TEXT,"
                + colSpecials + " TEXT," + colAgeLimit + " INTEGER," 
                + colFood + " INTEGER"
                + ")";
        db.execSQL(CREATE_BARS_TABLE);
        
        String CREATE_SPECIALS_TABLE = "CREATE TABLE " + TABLE_SPECIALS + "("
        		+ colId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        		+ colNameb + " TEXT," + colLoc + " TEXT," + colDay + " TEXT,"
        		+ colHHbegin + " INTEGER," + colHHend + " INTEGER," + colSpec + " TEXT"
        		+ ")";
        db.execSQL(CREATE_SPECIALS_TABLE);
        
        String CREATE_VERSION_TABLE = "CREATE TABLE VERSION(VER INTEGER PRIMARY KEY)";
        db.execSQL(CREATE_VERSION_TABLE);
        ContentValues v = new ContentValues();
        v.put("VER",DATABASE_VERSION);
        db.insert("VERSION", null, v);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BARS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPECIALS);
        // Create tables again
        onCreate(db);
    }
    
    public void upgrade(int cur, int late){
    	SQLiteDatabase db = this.getWritableDatabase();
    	onUpgrade(db, cur, late);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new bar
    void addBar(Bar bar) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(colName, bar.getName()); // Bar Name
        values.put(colAddress, bar.getAddress());
        values.put(colPhone, bar.getPhoneNumber()); 
        values.put(colType, bar.getVenue());
        values.put(colHoursOpen, bar.getHoursOpen());
        values.put(colAlcohol, bar.getAlcohol());
        values.put(colSpecials, bar.getSpecials());
        values.put(colAgeLimit, bar.getAgeReq());
        values.put(colFood, bar.getFood());
 
        // Inserting Row
        db.insert(TABLE_BARS, null, values);
        db.close(); // Closing database connection
    }
    
 // Adding new special
    void addSpecial(String name, String loc, String day, String hhb, String hhe, String spec) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(colNameb, name); // Bar Name
        values.put(colLoc, loc);
        values.put(colDay, day); 
        values.put(colHHbegin, hhb);
        values.put(colHHend, hhe);
        values.put(colSpec, spec);
 
        // Inserting Row
        db.insert(TABLE_SPECIALS, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single contact
    Bar getBar(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_BARS, new String[] { colName,
                colAddress, colPhone, colType, colHoursOpen, colAlcohol, 
                colSpecials, colAgeLimit, colFood}, colName + "=?",
                new String[] { String.valueOf(name) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Bar bar = new Bar(cursor.getString(0), cursor.getString(1), cursor.getString(2),
        		cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), 
        		Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)));       
        
        // return bar
        return bar;
    }
 
    // Getting All Bars
    public List<Bar> getAllBars() {
        List<Bar> barList = new ArrayList<Bar>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BARS + " ORDER BY " + colName;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Bar bar = new Bar();
                bar.setName(cursor.getString(0));
                bar.setAddress(cursor.getString(1));
                bar.setPhoneNumber(cursor.getString(2));
                bar.setVenue(cursor.getString(3));
                bar.setHoursOpen(cursor.getString(4));
                bar.setAlcohol(cursor.getString(5));
                bar.setSpecials(cursor.getString(6));
                bar.setAgeReq(Integer.parseInt(cursor.getString(7)));
                bar.setFood(Integer.parseInt(cursor.getString(8)));
                // Adding bar to list
                barList.add(bar);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return barList;
    }
    
    
 // Getting Bars NOW
    // will need to figure out how to make time hour into an integer or string
    public List<String> getBarsNow(String day, String hour) {
        List<String> barList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT " + colNameb + " FROM " + TABLE_SPECIALS + " AS A "
                + " WHERE ((((" + hour + " BETWEEN " + colHHbegin + " AND 24) OR (" + hour + " BETWEEN 0 AND " + colHHend + ")) AND (" + colHHbegin + ">" + colHHend + ") AND (A." + colDay + "='" + day + "'))"
            	+ " OR (("+ hour + " BETWEEN " + colHHbegin + " AND " + colHHend + ") AND (" + colHHbegin + "<" + colHHend + ") AND (A." + colDay + "='" + day + "')))"
            + " ORDER BY " + colNameb;
        
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	String bar = cursor.getString(0);
                // Adding bar to list
                barList.add(bar);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return barList;
    }
    
 // Getting Bars by Day
    public List<String> getBarsByDay(String day) {
        List<String> barList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT " + colNameb + " FROM " + TABLE_SPECIALS
        		+ " WHERE " + colDay + "= '" + day + "' ORDER BY " + colNameb;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String bar = cursor.getString(0);
                // Adding bar to list
                barList.add(bar);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return barList;
    }
    
 // Getting Bars by Location
    public List<String> getBarsByLoc(String location) {
        List<String> barList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT " + colNameb + " FROM " + TABLE_SPECIALS
        		+  " WHERE " + colLoc + "='" + location + "' ORDER BY " + colNameb;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	 String bar = cursor.getString(0);
                 // Adding bar to list
                 barList.add(bar);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return barList;
    }
 
    // Updating single bar
    public int updateBar(Bar bar) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(colName, bar.getName()); // Bar Name
        values.put(colAddress, bar.getAddress());
        values.put(colPhone, bar.getPhoneNumber()); 
        values.put(colType, bar.getVenue());
        values.put(colHoursOpen, bar.getHoursOpen());
        values.put(colAlcohol, bar.getAlcohol());
        values.put(colSpecials, bar.getSpecials());
        values.put(colAgeLimit, bar.getAgeReq());
        values.put(colFood, bar.getFood());
 
        // updating row
        return db.update(TABLE_BARS, values, colName + " = ?",
                new String[] { String.valueOf(bar.getName()) });
    }
 
    // Deleting single bar
    public void deleteBar(Bar bar) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BARS, colName + " = ?",
                new String[] { String.valueOf(bar.getName()) });
        db.delete(TABLE_SPECIALS, colNameb + " = ?", new String[] {String.valueOf(bar.getName())});
        db.close();
    }
 
    // Getting bars Count
    public int getAllBarsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_BARS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
    
 // Getting All Specials FOR DEBUGGING
    public List<String> getAllSpecials() {
        List<String> specialsList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SPECIALS + " ORDER BY " + colNameb;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	String s = cursor.getString(0) + cursor.getString(1) + cursor.getString(2) 
            			+ cursor.getString(3) + cursor.getString(4) + cursor.getString(5);
                // Adding bar to list
                specialsList.add(s);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return specialsList;
    }
    
    public int latestDBVersion() {
    	return DATABASE_VERSION;
    }

    public int getLocalDBVer() {
    	SQLiteDatabase db = null;
        try
        {
            db = this.getReadableDatabase();

            String queryString = new String("Select ver From Version;");

            Cursor answer = db.rawQuery(queryString, null);
            answer.moveToFirst();
            int version = answer.getInt(0);
            answer.close();
            db.close();
            return version;
        }
        catch(Exception ex)
        {
        	return 0;
        }
    }
    
    public boolean isEmpty() {
    	List<Bar> bars = getAllBars();    
    	int i = 0;
        for (Bar b: bars) {
        	i++;
        }
        if (i > 0) {
        	return false;
        }
        else {
        	return true;
        }
    }
    
}