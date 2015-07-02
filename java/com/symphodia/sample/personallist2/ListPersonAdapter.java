package com.symphodia.sample.personallist2;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListPersonAdapter extends ArrayAdapter<ListItemPerson>{
    private static final int RESOURCE = R.layout.list_item_person;

    public ListPersonAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(RESOURCE, parent, false);
        }else {
            view = convertView;
        }

        ListItemPerson person = getItem(position);

        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        tvName.setText(person.getName());
        TextView tvAge = (TextView)view.findViewById(R.id.tv_age);
        tvAge.setText(String.valueOf(person.getAge()));
        TextView tvCompany = (TextView)view.findViewById(R.id.tv_company);
        tvCompany.setText(person.getCompany());
        return view;
    }

    public ArrayList<ListItemPerson> getPersonList(){
        int size = getCount();
        ArrayList<ListItemPerson> personList = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            personList.add(getItem(i));
        }
        return personList;
    }

    public void addAll(ArrayList<Parcelable> parcelableArrayList){
        ArrayList<ListItemPerson> personList = new ArrayList<>();
        for(Parcelable person : parcelableArrayList){
            personList.add((ListItemPerson)person);
        }
        super.addAll(personList);
    }
}
