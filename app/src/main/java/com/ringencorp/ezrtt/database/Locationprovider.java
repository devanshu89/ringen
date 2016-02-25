package com.ringencorp.ezrtt.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by faraz on 01/02/2016.
 */
public class Locationprovider extends ContentProvider {
    // user location Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_Latitute = "latitute";
    public static final String KEY_Longtute = "longtute";

/*    // fields for the database
    static final String ID = "id";
    static final String NAME = "name";
    static final String BIRTHDAY = "birthday";*/
    public static final String KEY_timestamp = "time";
    public static final String KEY_Adress = "adress";
    public static final String KEY_userid = "userid";
    // fields for my content provider
    static final String PROVIDER_NAME = "com.example.faraz.provider.Location";
    static final String URL = "content://" + PROVIDER_NAME + "/users";
    public static final Uri CONTENT_URI = Uri.parse(URL);
    // integer values used in content URI
    static final int USERS = 1;
    static final int USERS_ID = 2;
    // maps content URI "patterns" to the integer values that were set above
    static final UriMatcher uriMatcher;
    static final String DATABASE_NAME = "Loctiondb.sqlite";
    static final String TABLE_NAME = "userlocation";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_Latitute + " REAL," + KEY_Longtute
                    + " REAL," + KEY_Adress + " TEXT," + KEY_timestamp + " INTEGER," + KEY_userid + " INTEGER" + ")";
    // projection map for a query
    private static HashMap<String, String> LOCATIONMAP;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "users", USERS);
        uriMatcher.addURI(PROVIDER_NAME, "users/#", USERS_ID);
    }

    DBHelper dbHelper;
    // database declarations
    private SQLiteDatabase database;

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO Auto-generated method stub
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        // the TABLE_NAME to query on
        queryBuilder.setTables(TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            // maps all database column names
            case USERS:
                queryBuilder.setProjectionMap(LOCATIONMAP);
                break;
            case USERS_ID:
                queryBuilder.appendWhere(KEY_userid + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (sortOrder == null || sortOrder == "") {
            // No sorting-> sort on names by default
            sortOrder = KEY_timestamp;
        }
        Cursor cursor = queryBuilder.query(database, projection, selection,
                selectionArgs, null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public boolean onCreate() {
        // TODO Auto-generated method stub
        Context context = getContext();
        dbHelper = new DBHelper(context);
        // permissions to be writable
        database = dbHelper.getWritableDatabase();

        if (database == null)
            return false;
        else
            return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO Auto-generated method stub
        long row = database.insert(TABLE_NAME, "", values);

        // If record is added successfully
        if (row > 0) {
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI, row);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("Fail to add a new record into " + uri);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO Auto-generated method stub
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case USERS:
                count = database.update(TABLE_NAME, values, selection, selectionArgs);
                break;
            case USERS_ID:
                count = database.update(TABLE_NAME, values, KEY_userid +
                        " = " + uri.getLastPathSegment() +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case USERS:
                // delete all the records of the table
                count = database.delete(TABLE_NAME, selection, selectionArgs);
                break;
            case USERS_ID:
                String id = uri.getLastPathSegment();    //gets the id
                count = database.delete(TABLE_NAME, KEY_userid + " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;


    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        switch (uriMatcher.match(uri)) {
            // Get all friend-birthday records
            case USERS:
                return "vnd.android.cursor.dir/vnd.example.friends";
            // Get a particular friend
            case USERS_ID:
                return "vnd.android.cursor.item/vnd.example.friends";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    // class that creates and manages the provider's database
    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            Log.w(DBHelper.class.getName(),
                    "Upgrading database from version " + oldVersion + " to "
                            + newVersion + ". Old data will be destroyed");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }

    }


}