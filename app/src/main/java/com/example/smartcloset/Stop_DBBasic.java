package com.example.smartcloset;

import android.database.Cursor;

public class Stop_DBBasic {
	
	public void insert(Stop_DBAction db,String sql){
		db.insert(sql);
	}
	
	public Cursor select(Stop_DBAction db, String sql){
		return db.select(sql);
	}
	
	public void updata(Stop_DBAction db, String sql){
		db.updata(sql);
	}
	public void deletdata(Stop_DBAction db, String sql){
		db.delet(sql);
	}
}
