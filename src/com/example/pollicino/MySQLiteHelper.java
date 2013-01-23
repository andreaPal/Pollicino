package com.example.pollicino;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_POINTS = "points";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_LAT = "lat";
	public static final String COLUMN_LNG = "lng";
	private static final String DATABASE_NAME = "pollicino.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_POINTS + "( "
			+ COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_LAT + " text not null,"
			+ COLUMN_LNG + " text not null);";
	
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_POINTS);
		onCreate(db);
		// TODO Auto-generated method stub
		
	}

}
