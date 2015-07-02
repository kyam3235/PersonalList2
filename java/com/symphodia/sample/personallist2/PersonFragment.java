package com.symphodia.sample.personallist2;

import android.database.Cursor;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.Locale;

public class PersonFragment extends ListFragment {
    private ListPersonAdapter mAdapter;

    public PersonFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            mAdapter = new ListPersonAdapter(getActivity());
            setListAdapter(mAdapter);
            return;
        }

        mAdapter = new ListPersonAdapter(getActivity());
        mAdapter.addAll(savedInstanceState.getParcelableArrayList("person_list"));
        setListAdapter(mAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mAdapter = (ListPersonAdapter) getListAdapter();
        outState.putParcelableArrayList("person_list", mAdapter.getPersonList());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mAdapter.remove(mAdapter.getItem(position));
    }

    public void add(String name, int age, String company) {
        ListItemPerson person = new ListItemPerson(name, age, company);
        mAdapter.add(person);

        DbControl dbControl = new DbControl(getActivity());
        dbControl.insert(person.getName(), person.getAge(), person.getCompany());
    }

    public void showAll() {
        DbControl dbControl = new DbControl(getActivity());
        Cursor cursorPerson = dbControl.fetchAllPersons();
        addToListFromCursor(cursorPerson);

        Cursor cursorCompany = dbControl.fetchAllCompanies();
        cursorCompany.moveToFirst();
        for(int i = 0; i < cursorCompany.getCount(); i++){
            Log.d("CompanyTable", "id:"
                    + cursorCompany.getInt(cursorCompany.getColumnIndexOrThrow(CompanyTable.COLUMN_ID))
                    + ",company:"
                    + cursorCompany.getString(cursorCompany.getColumnIndexOrThrow(CompanyTable.COLUMN_COMPANY_NAME)));
            cursorCompany.moveToNext();
        }
    }

    public void searchByName(String name) {
        DbControl dbControl = new DbControl(getActivity());
        Cursor cursor = dbControl.fetchSelectPersonBy(name);
        addToListFromCursor(cursor);
    }

    private void addToListFromCursor(Cursor cursorPerson) {
        DbControl dbControl = new DbControl(getActivity());
        cursorPerson.moveToFirst();
        for (int i = 0; i < cursorPerson.getCount(); i++) {
            String name = cursorPerson.getString(cursorPerson.getColumnIndexOrThrow(PersonTable.COLUMN_NAME));
            int age = cursorPerson.getInt(cursorPerson.getColumnIndexOrThrow(PersonTable.COLUMN_AGE));
            int companyId = cursorPerson.getInt(cursorPerson.getColumnIndexOrThrow(PersonTable.COLUMN_COMPANY_ID));
            String companyName = getCompanyName(companyId);
            ListItemPerson person = new ListItemPerson(name, age, companyName);
            mAdapter.add(person);
            cursorPerson.moveToNext();
        }
        cursorPerson.close();
    }

    private String getCompanyName(int companyId) {
        DbControl dbControl = new DbControl(getActivity());
        Cursor cursor = dbControl.fetchSelectCompanyBy(companyId);
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndexOrThrow(CompanyTable.COLUMN_COMPANY_NAME));
    }
}
