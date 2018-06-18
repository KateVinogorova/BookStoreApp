package com.vinogorova.bookstoreapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.vinogorova.bookstoreapp.data.BookContract.BookEntry;

public class BookDbHelper extends SQLiteOpenHelper {

    private static  final String LOG_TAG = BookDbHelper.class.getSimpleName();

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /** Name of the database file */
    private static final String DATABASE_NAME = "book_store.db";

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //SQL query for creating new table
    private static String SQL_CREATE_BOOKS_TABLE = "CREATE TABLE " + BookEntry.TABLE_NAME + "(" +
            BookEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BookEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
            BookEntry.COLUMN_AUTHOR + " TEXT NOT NULL, " +
            BookEntry.COLUMN_PRICE + " INTEGER NOT NULL, " +
            BookEntry.COLUMN_QUANTITY + " INTEGER NOT NULL Default 0, " +
            BookEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, " +
            BookEntry.COLUMN_SUPPLIER_PHONE + " TEXT );";

    /**
     * This is called when the database is created for the first time.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Execute the SQL statement
        db.execSQL(SQL_CREATE_BOOKS_TABLE);
    }

    /**
     * This is called when the database needs to be updated
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop existing table
        db.execSQL("DROP TABLE " + BookEntry.TABLE_NAME + ";");
        //create new table
        db.execSQL(SQL_CREATE_BOOKS_TABLE);
    }



}
