package com.filper.notifications;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


public class NotificationsDBManager{
	private NotificationDBAdapter notificationAdapter;
	private Cursor cursor;
	
	
	
	
	public NotificationsDBManager(Context context){
		notificationAdapter = new NotificationDBAdapter(context);
		notificationAdapter.open();		
	}
	
	public void close(){
		notificationAdapter.close();
	}
	
	
	/**
	 *  Retorna Si el usuario se encuentra conectado
	 *  
	 * @param userName : Get the user name as String
	 * @return  0 - If the user is not connected,
	 * 			1 - If the user is connected
	 */
	public int getStatusUser(String userName){
		cursor = notificationAdapter.getUser(userName);
		if (cursor == null)
			return 0;
		else
			return 1;
	}

	/**
	 * Regresa un arreglo de los datos del usuario conectado, pasando como
	 * parametro el nombre del usuario
	 * @param userName : Get the user name as String
	 * @return null - If the user no exist, 
	 * 		   String with userName - If the user exist	
	 */			
	public String getInfoUser(String userName){
		cursor = notificationAdapter.getUser(userName);
		if (cursor != null){
			String auxUserName = cursor.getString(cursor.getColumnIndex(notificationAdapter.C_COLUMNA_USERNAME));		
			return auxUserName;
		}
		return null;
	}

	
	/**
	 * Regresa un arreglo de los datos del usuario conectado
	 * @return null - If the user no exist, 
	 * 		   String with userName - If the user exist	
	 */			
	public String[] getInfoUser(){
		cursor = notificationAdapter.getUser();
		if (cursor != null){
			String[] infoUser = new String[2];
			infoUser[0] = cursor.getString(cursor.getColumnIndex(notificationAdapter.C_COLUMNA_USERNAME));
			infoUser[1] = cursor.getString(cursor.getColumnIndex(notificationAdapter.C_COLUMNA_WSURI));
			return infoUser;
		}
		return null;
	}	
	
	
	
	/**
	 * 
	 * @param userName debe ser un String,
	 * @return Retorna 0 si no se ha podido guardar el usuario
	 * 	o retorna 1 si es que el valor ha sido guardado.
	 * Si la base de datos ya cuenta con un usuario no podra gudardarse un 
	 * nuevo usuario
	 */
	public int setUser(String userName, String wsuri){
		cursor = notificationAdapter.getCursor();
		int count = cursor.getCount();
		if (count > 0)
			return 0;
		else{
			ContentValues reg = new ContentValues();
			reg.put(notificationAdapter.C_COLUMNA_USERNAME, userName);
			reg.put(notificationAdapter.C_COLUMNA_WSURI, wsuri);
			notificationAdapter.insert(reg);
		}
		return 0;
	}

	/**
	 * 
	 * @param userName : debe ser un String
	 * @return si regresa 0 es que no elimino nada, y si regresa 1 es que si se elimino
	 */
	public int deleteUser(String userName){
		int auxLong = (int) notificationAdapter.delete(userName);
		return auxLong;
	}
	
	public int deleteUser(){
		int auxLong = (int) notificationAdapter.delete();
		return auxLong;
	}
	

}