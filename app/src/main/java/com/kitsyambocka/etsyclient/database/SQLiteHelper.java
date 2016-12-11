package com.kitsyambocka.etsyclient.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Developer on 11.12.2016.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "GoodsDB";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_BOOK_TABLE = "CREATE TABLE goods ( " +
                "_id INTEGER PRIMARY KEY, " +
                "title TEXT, "+
                "price TEXT, "+
                "currency TEXT, "+
                "smallpic TEXT, "+
                "bigpic TEXT, "+
                "description TEXT )";

        // create books table
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older goods table if existed
        db.execSQL("DROP TABLE IF EXISTS goods");

        // create fresh goods table
        this.onCreate(db);
    }


}
