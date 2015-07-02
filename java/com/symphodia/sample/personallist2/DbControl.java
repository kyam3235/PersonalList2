package com.symphodia.sample.personallist2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DbControl {
    PersonDatabaseHelper mHelper;

    public DbControl(Context context) {
        mHelper = new PersonDatabaseHelper(context);
    }

    public long insert(String name, int age, String company){
        SQLiteDatabase database = mHelper.getWritableDatabase();
        long companyId = insertCompany(database, company);

        //If inserting company table is failed, return this method.
        if(companyId < 0){
            return companyId;
        }

        ContentValues values = new ContentValues();
        values.put(PersonTable.COLUMN_NAME, name);
        values.put(PersonTable.COLUMN_AGE, age);
        values.put(PersonTable.COLUMN_COMPANY_ID, companyId);
        Log.d("Inserted to Person", values.get(PersonTable.COLUMN_NAME) + "," + String.valueOf(values.get(PersonTable.COLUMN_AGE)) + "," + values.get(PersonTable.COLUMN_COMPANY_ID));
        return database.insert(PersonTable.TABLE_PERSON, null, values);
    }

    //Use to insert to company table.
    private long insertCompany(SQLiteDatabase database, String company) {
        ContentValues values = new ContentValues();
        values.put(CompanyTable.COLUMN_COMPANY_NAME, company);
        Log.d("Inserted to Company", values.get(CompanyTable.COLUMN_COMPANY_NAME).toString());
        return database.insert(CompanyTable.TABLE_COMPANY, null, values);
    }

    //TODO:How delete a company table record.
    public boolean delete(long id){
        SQLiteDatabase database = mHelper.getWritableDatabase();
        return database.delete(PersonTable.TABLE_PERSON, PersonTable.COLUMN_ID + "=" + id, null) > 0;
    }

    public Cursor fetchSelectPersonBy(String name) {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        Cursor cursor = database.query(PersonTable.TABLE_PERSON, new String[]{PersonTable.COLUMN_ID, PersonTable.COLUMN_NAME, PersonTable.COLUMN_AGE, PersonTable.COLUMN_COMPANY_ID},
                "name like ?",
                new String[]{"%" + name + "%"},
                null, null, null);
        return cursor;
    }

    public Cursor fetchSelectCompanyBy(int companyId){
        SQLiteDatabase database = mHelper.getWritableDatabase();
        Cursor cursor = database.query(CompanyTable.TABLE_COMPANY, new String[]{CompanyTable.COLUMN_ID, CompanyTable.COLUMN_COMPANY_NAME},
                CompanyTable.COLUMN_ID + " == ?",
                new String[]{String.valueOf(companyId)},
                null, null, null);
        return cursor;
    }

    public Cursor fetchAllPersons(){
        SQLiteDatabase database = mHelper.getWritableDatabase();
        String sql = "select "
                + PersonTable.TABLE_PERSON + "." + PersonTable.COLUMN_ID + ", "
                + PersonTable.TABLE_PERSON + "." + PersonTable.COLUMN_NAME + ", "
                + PersonTable.TABLE_PERSON + "." + PersonTable.COLUMN_AGE + ", "
                + PersonTable.TABLE_PERSON + "." + PersonTable.COLUMN_COMPANY_ID
                + " from "
                + PersonTable.TABLE_PERSON + ";";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor;
    }

    public Cursor fetchAllCompanies(){
        SQLiteDatabase database = mHelper.getWritableDatabase();
        String sql = "select "
                + CompanyTable.TABLE_COMPANY + "." + CompanyTable.COLUMN_ID + ", "
                + CompanyTable.TABLE_COMPANY + "." + CompanyTable.COLUMN_COMPANY_NAME
                + " from "
                + CompanyTable.TABLE_COMPANY + ";";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor;
    }
}
