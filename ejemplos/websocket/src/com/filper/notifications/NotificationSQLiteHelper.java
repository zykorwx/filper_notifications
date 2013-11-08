package com.filper.notifications;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NotificationSQLiteHelper extends SQLiteOpenHelper{

	private static int version = 2;
	private static String dbName = "NotificationDB";
	private static CursorFactory factory = null;
	private static final String TAG = "com.filper.websocketService";
	private static final String sqlCreate = "CREATE TABLE NotificationDB(" +
	          " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
	          " userName TEXT NOT NULL," +
	          " wsuri TEXT NOT NULL) ";
	
	public NotificationSQLiteHelper(Context context){
		super(context, dbName, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		
		db.execSQL( "CREATE TABLE NotificationDB(" +
		          " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
		          " userName TEXT NOT NULL," +
		          " wsuri TEXT NOT NULL) " );
		db.execSQL( "CREATE UNIQUE INDEX userName ON NotificationDB (userName ASC)" );
		 Log.d(TAG, "Table create");	
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		db.execSQL("DROP TABLE IF EXISTS NotificationDB");
		
		//Se crea la nueva versión de la tabla
		db.execSQL(sqlCreate);
		db.execSQL( "CREATE UNIQUE INDEX userName ON NotificationDB (userName ASC)" );
	}
}