package com.saeidpsl.soalvajavab;

import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;   
import android.content.Context;  
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;  
import android.database.sqlite.SQLiteOpenHelper;   
import android.os.Environment;
  
public class DBHelper extends SQLiteOpenHelper {  
  
 public static String DB_NAME = "database"; 
 private final Context context;  
 public static String DB_PATH;  
 public static  SQLiteDatabase db_object;
	
	
 public DBHelper(Context context) {  
  super(context, DB_NAME, null, 1);  
  this.context = context;  
  DB_PATH = Environment.getDataDirectory() + "/data/" + context.getPackageName() + "/" + "databases/";
  
  
	try {
		createDataBase();
	} catch (IOException ioe) {
		throw new Error("Unable to create database");
	}
	try {
		openDataBase();
	} catch (SQLException sqle) {
		throw sqle;
	}
  
 }  
  
	public void openDataBase() throws SQLException {
		String myPath = DB_PATH + DB_NAME;
		db_object = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);
	}
 
 
 public void createDataBase() throws IOException {  
  
  boolean dbExist = checkDataBase();  
  if (dbExist) {  
  
  } else {  
   this.getReadableDatabase();  
   try {  
    copyDataBase();  
   } catch (IOException e) {  
    throw new Error("Error copying database");  
   }  
  }  
 }  
  
 private boolean checkDataBase() {  
  File dbFile = new File(DB_PATH + DB_NAME);  
  return dbFile.exists();  
 }  
  
 private void copyDataBase() throws IOException {  
  
  InputStream myInput = context.getAssets().open(DB_NAME);  
  String outFileName = DB_PATH + DB_NAME;  
  OutputStream myOutput = new FileOutputStream(outFileName);  
  byte[] buffer = new byte[1024];  
  int length;  
  while ((length = myInput.read(buffer)) > 0) {  
   myOutput.write(buffer, 0, length);  
  }  
  
  // Close the streams  
  myOutput.flush();  
  myOutput.close();  
  myInput.close();  
  
 }  
 
 
 @Override  
 public void onCreate(SQLiteDatabase arg0) {  
 
 }  
  
 @Override  
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
 
 }  
 
	@Override
	public synchronized void close() {
		if (db_object != null)
			db_object.close();
		super.close();
	}

 
	
	
	
	
	
 
}  