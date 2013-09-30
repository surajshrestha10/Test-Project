package com.example.parakhihotelbookingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database {

static final String KEY_ID = "id";
static final String KEY_NAME = "name";
static final String KEY_ADDRESS = "address";
static final String KEY_NUMBER = "no";
static final String KEY_PRICE = "price";
static final String KEY_SERVICES = "services";
static final String KEY_DESCRIPTION = "description";
static final String KEY_IMAGE = "image";

static final String TAG = "CotactDatabase";

static final String DATABASE_NAME = "CotactV1";
static final String DATABASE_TABLE = "contacts";
static final int DATABASE_VERSION = 1;

static final String DATABASE_CREATE = "create table hotels (id integer primary key autoincrement, "
+ "name text not null,address text not null,number text not null);";

final Context context;

DatabaseHelper DBHelper;
SQLiteDatabase db;

public Database(Context ctx) {
this.context = ctx;
DBHelper = new DatabaseHelper(context);

}

private static class DatabaseHelper extends SQLiteOpenHelper {

DatabaseHelper(Context context) {
super(context, DATABASE_NAME, null, DATABASE_VERSION);

}

@Override
public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub
try {
db.execSQL(DATABASE_CREATE);
} catch (SQLException ex) {
ex.printStackTrace();
}

}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//TODO Auto-generated method stub
Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
+ newVersion + ", which will destroy all old data");
db.execSQL("Drop TABLE IF EXISTS contacts");
onCreate(db);
}
}

public Database open() throws SQLException {
db = DBHelper.getWritableDatabase();
return this;
}

public void close() {
DBHelper.close();
}

public long insertContact(String name, String address, String number) {
ContentValues initialValues = new ContentValues();
initialValues.put(KEY_NAME, name);
initialValues.put(KEY_ADDRESS, address);
initialValues.put(KEY_NUMBER, number);
return db.insert(DATABASE_TABLE, null, initialValues);
}

public Cursor getAllCotacts() {
return db.query(DATABASE_TABLE, new String[] { KEY_ID, KEY_NAME,
KEY_ADDRESS, KEY_NUMBER }, null, null, null, null, null);
}

public Cursor getContact(long id) throws SQLException {
Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] { KEY_ID,
KEY_NAME, KEY_ADDRESS, KEY_NUMBER }, KEY_ID + "=" + id, null,
null, null, null, null);
if (mCursor != null) {
mCursor.moveToFirst();
}
return mCursor;

}
public long deleteRecord(int id)
{
return db.delete(DATABASE_TABLE,KEY_ID + "=" +id,null);
}

public long updateRecord(int id, String name, String address, String number)
{
ContentValues inputValues=new ContentValues();
inputValues.put(KEY_NAME,name);
inputValues.put(KEY_ADDRESS,address);
inputValues.put(KEY_NUMBER,number);
return db.update(DATABASE_TABLE,inputValues,KEY_ID + "=" +id,null);
}

public void insert()
{
insertContact("suraj","anamnagar","9841617014");
}

}