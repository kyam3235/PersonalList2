package com.symphodia.sample.personallist2;

import android.database.sqlite.SQLiteDatabase;

public class CompanyTable {
    public static final String TABLE_COMPANY = "company";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COMPANY_NAME = "company_name";

    private static final String STATEMENT_CREATE_DATABASE = "create table "
            + TABLE_COMPANY
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_COMPANY_NAME + " text not null"
            + ");";

    public static void onCreate(SQLiteDatabase database){
        database.execSQL(STATEMENT_CREATE_DATABASE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANY);
        onCreate(database);
    }
}
