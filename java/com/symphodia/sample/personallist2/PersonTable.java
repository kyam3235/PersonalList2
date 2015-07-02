package com.symphodia.sample.personallist2;

import android.database.sqlite.SQLiteDatabase;

public class PersonTable {
    public static final String TABLE_PERSON = "person";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_COMPANY_ID = "company_id";

    private static final String STATEMENT_CREATE_DATABASE = "create table "
            + TABLE_PERSON
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_AGE + " integer not null, "
            + COLUMN_COMPANY_ID + " integer not null"
            + ");";

    public static void onCreate(SQLiteDatabase database){
        database.execSQL(STATEMENT_CREATE_DATABASE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
        onCreate(database);
    }
}
