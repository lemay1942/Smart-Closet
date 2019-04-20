package com.example.smartcloset;

import android.database.Cursor;

public interface Stop_DBAction {
	
	void insert(String sql);
	
	Cursor select(String sql);

	void updata(String sql);

	void delet(String sql);
}
