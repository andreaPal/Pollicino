package com.example.pollicino;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PointDAO_DB_impl implements PointDAO {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
									MySQLiteHelper.COLUMN_LAT,
									MySQLiteHelper.COLUMN_LNG};
	
	@Override
	public void open() throws SQLException{
		
			if (dbHelper==null) dbHelper = new MySQLiteHelper(MyApplication.getContext());
			database = dbHelper.getWritableDatabase();
			
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		dbHelper.close();
		// TODO Auto-generated method stub

	}

	@Override
	public Point insertPoint(Point point) {
		long insertId = database.insert(MySQLiteHelper.TABLE_POINTS, null, pointToValues(point));
		Cursor cursor = database.query(MySQLiteHelper.TABLE_POINTS,
				allColumns, MySQLiteHelper.COLUMN_ID + " = ?" ,
				new String[] {""+insertId}, null, null, null);
		cursor.moveToFirst();
		Point p=cursorToPoint(cursor);
		cursor.close();
		return p;
	}
	
	public void deletePoint(Point point) {
		long id = point.getID();
		database.delete(MySQLiteHelper.TABLE_POINTS,
						MySQLiteHelper.COLUMN_ID + " = ?",
						new String[] {""+id});
	}
	
	public void clear() {
		database.delete(MySQLiteHelper.TABLE_POINTS, null, null);
	}

	@Override
	public List<Point> getAllPoint() {

		List<Point> points = new ArrayList<Point>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_POINTS,
		allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Point point = cursorToPoint(cursor);
			points.add(point);
			cursor.moveToNext();
		}
		cursor.close(); 
		return points;
	}

	public Point getLastPoint() {
		Cursor cursor = database.query(MySQLiteHelper.TABLE_POINTS,
		allColumns, null, null, null, null, null);
		if (!cursor.moveToLast())
			return null;
		Point point = cursorToPoint(cursor);
		cursor.close();
		return point;
	}
	
	private ContentValues pointToValues(Point point) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_LAT,
		point.getLat());
		values.put(MySQLiteHelper.COLUMN_LNG,
		point.getLng());
		return values;
		}
	
	private Point cursorToPoint(Cursor cursor) {
		long id = cursor.getLong(0);
		Double lat=cursor.getDouble(1);
		Double lng=cursor.getDouble(2);
		return new Point(id,lat,lng);
		}
}
