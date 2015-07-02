package com.symphodia.sample.personallist2;

import android.os.Parcel;
import android.os.Parcelable;

public class ListItemPerson implements Parcelable{
    private String Name;
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    private int Age;
    public int getAge() {
        return Age;
    }
    public void setAge(int age) {
        Age = age;
    }
    private String Company;
    public String getCompany() {
        return Company;
    }
    public void setCompany(String company) {
        Company = company;
    }

    public ListItemPerson(String name, int age, String company) {
        this.Name = name;
        this.Age = age;
        this.Company = company;
    }

    public ListItemPerson(Parcel in){
        this.Name = in.readString();
        this.Age = in.readInt();
        this.Company = in.readString();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.Name);
        dest.writeInt(this.Age);
        dest.writeString(this.Company);
    }

    public static final Creator<ListItemPerson> CREATOR = new Creator<ListItemPerson>() {
        @Override
        public ListItemPerson createFromParcel(Parcel source) {
            return new ListItemPerson(source);
        }

        @Override
        public ListItemPerson[] newArray(int size) {
            return new ListItemPerson[size];
        }
    };
}
