/**
 * 
 */
package com.ringencorp.ezrtt.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * @author Devanshu
 * 
 */
public class LocTable {
	// Database Table
	public static final String NAME = "empee_loc";

	// Columns
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_LAT = "latitude";
	public static final String COLUMN_LNG = "longitude";
	public static final String COLUMN_DATETIME = "datetime";


	// Database creation SQL statements
	private static final String DATABASE_CREATE_TABLE = "create table"
			+ NAME + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_LAT
			+ " double not null, " + COLUMN_LNG + " double not null, "
			+ COLUMN_DATETIME + " date not null "
			+ ");";

	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE_TABLE);

	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(LocTable.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + NAME);
		onCreate(database);
	}
}
