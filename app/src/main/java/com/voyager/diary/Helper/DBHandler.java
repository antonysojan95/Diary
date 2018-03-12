package com.voyager.diary.Helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 08-Mar-18.
 */

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "UserDetailsList";
    // Contacts table name
    private static final String TABLE_USERS = "users";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHNO = "phno";
    private static final String KEY_CITY = "city";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_PASS = "Password";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // !!!!! Waring The Space Between these Syntax is critical so be sure to place it properly !!!!!!
        String CREATE_USER_TABLE =  "CREATE TABLE "+
                TABLE_USERS + "(" +
                KEY_ID + " INTEGER PRIMARY KEY,"+
                KEY_NAME + " TEXT," +
                KEY_PASS + " TEXT," +
                KEY_EMAIL + " TEXT," +
                KEY_PHNO + " TEXT," +
                KEY_CITY + " TEXT," +
                KEY_COUNTRY + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USERS + "'");
        // Creating tables again
        onCreate(db);
    }


    // Adding new User
    public void addUser(UserDetails userDetails) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, userDetails.getFName()); // User Name
        values.put(KEY_PASS, userDetails.getPswd()); // User pass
        values.put(KEY_EMAIL, userDetails.getEmail()); // User email
        values.put(KEY_PHNO, userDetails.getPhno()); // User phno
        values.put(KEY_CITY, userDetails.getCity()); // User city
        values.put(KEY_COUNTRY, userDetails.getCountry()); // User country

        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }


    // Getting one user
    public UserDetails getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[]{KEY_ID,
                KEY_NAME,KEY_PASS,KEY_EMAIL,KEY_PHNO,KEY_CITY,KEY_COUNTRY}, KEY_ID + "=?",
        new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        UserDetails userDetails = new UserDetails(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6));
        // return Users
        return userDetails;
    }


    // Getting All Users
    public List<UserDetails> getAllUsers() {
        List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserDetails userDetails = new UserDetails();
                userDetails.setUserID(Integer.parseInt(cursor.getString(0)));
                userDetails.setFName(cursor.getString(1));
                userDetails.setPswd(cursor.getString(2));
                userDetails.setEmail(cursor.getString(3));
                userDetails.setPhno(cursor.getString(4));
                userDetails.setCity(cursor.getString(5));
                userDetails.setCountry(cursor.getString(6));
                // Adding User to list
                userDetailsList.add(userDetails);
            } while (cursor.moveToNext());
        }

            // return User list
        return userDetailsList;
    }


    // Getting Users Count
    public int getShopsCount() {
        String countQuery = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


    // Updating a User
    public int updateShop(UserDetails userDetails) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, userDetails.getFName()); // User Name
        values.put(KEY_EMAIL, userDetails.getFName()); // User email
        values.put(KEY_PHNO, userDetails.getFName()); // User phno
        values.put(KEY_CITY, userDetails.getFName()); // User city
        values.put(KEY_COUNTRY, userDetails.getFName()); // User country
        values.put(KEY_PASS, userDetails.getFName()); // User pass

        // updating row
        return db.update(TABLE_USERS, values, KEY_ID + " = ?",
        new String[]{String.valueOf(userDetails.getUserID())});
    }


    // Deleting a User
    public void deleteUser(UserDetails userDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?",
        new String[] { String.valueOf(userDetails.getUserID()) });
        db.close();
    }

}