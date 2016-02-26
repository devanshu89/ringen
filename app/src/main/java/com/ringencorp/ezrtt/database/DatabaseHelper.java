/**
 *
 */
package com.ringencorp.ezrtt.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Devanshu
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ezrtt.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
     * Called when database is created
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        LocTable.onCreate(database);
    }

    /*
     *
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        LocTable.onUpgrade(database, oldVersion, newVersion);
    }
}
