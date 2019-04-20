package com.example.smartcloset;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Stop_DBLogin implements Stop_DBAction{

	private SQLiteDatabase db;
	
	public Stop_DBLogin (SQLiteDatabase db){
		this.db = db;
	}

	@Override
	public void insert(String sql) {
		db.execSQL(sql);
	}

	@Override
	public Cursor select(String sql) {
		return db.rawQuery(sql,null);
	}

	@Override
	public void updata(String sql) {
		db.execSQL(sql);
		
	}

	@Override
	public void delet(String sql) {
		db.execSQL(sql);
		
	}
	
}
