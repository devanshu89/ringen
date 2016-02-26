package com.ringencorp.ezrtt.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.ringencorp.ezrtt.database.DatabaseHelper;
import com.ringencorp.ezrtt.database.LocTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Locationprovider extends ContentProvider {

    // database
    private DatabaseHelper dbHelper;

    private SQLiteDatabase database;

    // used for matching Uri
    private static final UriMatcher sUriMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);


    static final int LOCATIONS = 1;
    static final int LOCATION_ID = 2;

    private static final String AUTHORITY = "com.ringencorp.ezrtt.locprovider";
    private static final String BASE_PATH = "locations";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH);

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/location";

    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/location";

    // projection map for a query
    private static HashMap<String, String> LOCATIONMAP;

    static {
        sUriMatcher.addURI(AUTHORITY, "location", LOCATIONS);
        sUriMatcher.addURI(AUTHORITY, "location/#", LOCATION_ID);
    }


    @Override
    public boolean onCreate() {
        dbHelper = new DatabaseHelper(getContext());
        database = dbHelper.getWritableDatabase();
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO Auto-generated method stub
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        checkColumns(projection);

        // the NAME to query on
        queryBuilder.setTables(LocTable.NAME);

        switch (sUriMatcher.match(uri)) {
            // maps all database column names
            case LOCATIONS:
                queryBuilder.setProjectionMap(LOCATIONMAP);
                break;
            case LOCATION_ID:
                queryBuilder.appendWhere(LocTable.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
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
    public Uri insert(Uri uri, ContentValues values) {

        long row = database.insert(LocTable.NAME, "", values);

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

        switch (sUriMatcher.match(uri)) {
            case LOCATIONS:
                count = database.update(LocTable.NAME, values, selection, selectionArgs);
                break;
            case LOCATION_ID:
                count = database.update(LocTable.NAME, values, LocTable.COLUMN_ID +
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

        switch (sUriMatcher.match(uri)) {
            case LOCATIONS:
                // delete all the records of the table
                count = database.delete(LocTable.NAME, selection, selectionArgs);
                break;
            case LOCATION_ID:
                String id = uri.getLastPathSegment();    //gets the id
                count = database.delete(LocTable.NAME, LocTable.COLUMN_ID + " = " + id +
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
        switch (sUriMatcher.match(uri)) {
            // Get all friend-birthday records
            case LOCATIONS:
                return CONTENT_TYPE;
            // Get a particular friend
            case LOCATION_ID:
                return CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    private void checkColumns(String[] projection) {
        String[] available = {LocTable.COLUMN_ID, LocTable.COLUMN_LAT, LocTable.COLUMN_LNG, LocTable.COLUMN_DATETIME};

        if (projection != null) {
            HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));

            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException("Unknown columns in projection.");
            }

        }

    }

}