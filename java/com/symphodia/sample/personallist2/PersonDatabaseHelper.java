package com.symphodia.sample.personallist2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonDatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "person.db";
    private static final int DATABASE_VERSION = 1;

    public PersonDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        PersonTable.onCreate(database);
        CompanyTable.onCreate(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        PersonTable.onUpgrade(database, oldVersion, newVersion);
        CompanyTable.onUpgrade(database, oldVersion, newVersion);
    }
}
