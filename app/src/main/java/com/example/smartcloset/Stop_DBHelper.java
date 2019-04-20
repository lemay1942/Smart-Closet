package com.example.smartcloset;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Stop_DBHelper extends SQLiteOpenHelper {

	private static Stop_DBHelper instence;

	public static Stop_DBHelper getinstence(Context context){
		if(instence == null){
			instence = new Stop_DBHelper(context);
		}
		return instence;
	}

	private Stop_DBHelper(Context context) {
		super(context, "User.db", null, 2);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE Clothe(id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT Not Null, Kind TEXT Not Null, information TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS Clothe");
		onCreate(db);
	}

}
