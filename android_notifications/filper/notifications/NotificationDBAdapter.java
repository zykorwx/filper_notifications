package com.filper.notifications;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NotificationDBAdapter{
	public static final String C_TABLA = "NotificationDB";
	
	public static final String C_COLUMNA_ID = "_id";
	public static final String C_COLUMNA_USERNAME = "userName";
	public static final String C_COLUMNA_WSURI = "wsuri";
	
	private Context contexto;
	private NotificationSQLiteHelper dbHelper;
	private SQLiteDatabase db;
	
	private String[] colums = new String[]{ C_COLUMNA_ID, C_COLUMNA_USERNAME, C_COLUMNA_WSURI};
	
	public NotificationDBAdapter(Context context){
		this.contexto = context;
	}
	
	public NotificationDBAdapter open() throws SQLException
	{
		dbHelper = new  NotificationSQLiteHelper(contexto);
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public Cursor getCursor() throws SQLException
	{
		Cursor c = db.query(true, C_TABLA, colums, null, null, null, null, null, null);
		return c;		
	}



	public Cursor getUser(String userName) throws SQLException
	   {
	      Cursor c = db.query( true, C_TABLA, colums, C_COLUMNA_USERNAME + "=\"" + userName + "\"", null, null, null, null, null);
	 
	      //Nos movemos al primer registro de la consulta
	      if (c != null) {
	         c.moveToFirst();
	      }
	      
	      return c;
	   }

	public Cursor getUser() throws SQLException
	   {
	      Cursor c = db.query( true, C_TABLA, colums,  null, null, null, null, null, null);
	 
	      //Nos movemos al primer registro de la consulta
	      if (c != null) {
	         c.moveToFirst();
	      }
	      
	      return c;
	   }

	
	
	
	
	public long insert(ContentValues reg)
	{
	   if (db == null)
	      open();
	    
	   return db.insert(C_TABLA, null, reg);
	}

	

	public long delete(String userName)
	   {
	      if (db == null)
	         open();
	       
	      return db.delete(C_TABLA, C_COLUMNA_USERNAME + "=\"" + userName + "\"", null);
	   }
	
	public long delete()
	   {
	      if (db == null)
	         open();
	       
	      return db.delete(C_TABLA, null, null);
	   }
	
	
	
	public long update(ContentValues reg)
	{
	   long result = 0;
	    
	   if (db == null)
	      open();
	    
	   if (reg.containsKey(C_COLUMNA_ID))
	   {
	      //
	      // Obtenemos el id y lo borramos de los valores
	      //
	      long id = reg.getAsLong(C_COLUMNA_ID);
	       
	      reg.remove(C_COLUMNA_ID);
	       
	      //
	      // Actualizamos el registro con el identificador que hemos extraido 
	      //
	      result = db.update(C_TABLA, reg, "_id=" + id, null); 
	   }
	   return result;
	}
	
}