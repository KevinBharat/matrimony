package com.matrimony.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.matrimony.Bean.Bean_Registration;
import com.matrimony.Utility.Constatnt;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

/**
 * Created by admin on 5/2/2018.
 */

public class DB_Registration extends SQLiteAssetHelper {
    public DB_Registration(Context context) {
        super(context, Constatnt.dbName, null, null, Constatnt.dbVersion);
    }

    public void insert(Bean_Registration br) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Name", br.getName());
        cv.put("Address", br.getAddress());
        cv.put("Email", br.getEmail());
        cv.put("Mobile", br.getMobile());
        cv.put("DOB", br.getDOB());
        cv.put("Gender", br.getGender());
        cv.put("CityID", br.getCityID());
        cv.put("Hobbies", br.getHobbies());
        cv.put("Age", br.getAge());
        cv.put("IsFavourite", "No");

        db.insert("MAT_Registration", null, cv);
        db.close();

    }

    public void update(Bean_Registration br) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Name", br.getName());
        cv.put("Address", br.getAddress());
        cv.put("Email", br.getEmail());
        cv.put("Mobile", br.getMobile());
        cv.put("DOB", br.getDOB());
        cv.put("Gender", br.getGender());
        cv.put("CityID", br.getCityID());
        cv.put("Hobbies", br.getHobbies());
        cv.put("Age", br.getAge());
        cv.put("IsFavourite", br.getIsFavourite());

        db.update("MAT_Registration", cv, "RegID=" + br.getRegID(), null);
        db.close();

    }

    public ArrayList<Bean_Registration> selectAll(String strFrom) {
        SQLiteDatabase db = getReadableDatabase();
        String strQuery = "";
        if (strFrom.equalsIgnoreCase("Profiles"))
            strQuery = "Select mr.RegID,mr.Name,mr.Gender,mr.CityID,mr.IsFavourite,mc.CityName from MAT_Registration mr inner join MST_City mc on mr.CityID=mc.CityID";
        else
            strQuery = "Select mr.RegID,mr.Name,mr.Gender,mr.CityID,mr.IsFavourite,mc.CityName from MAT_Registration mr inner join MST_City mc on mr.CityID=mc.CityID where mr.IsFavourite='Yes'";
        Cursor cur = db.rawQuery(strQuery, null);

        ArrayList<Bean_Registration> arrayReg = new ArrayList<Bean_Registration>();

        if (cur.moveToFirst()) {
            do {
                Bean_Registration br = new Bean_Registration();

                br.setRegID(cur.getInt(cur.getColumnIndex("RegID")));
                br.setName(cur.getString(cur.getColumnIndex("Name")));
                br.setGender(cur.getString(cur.getColumnIndex("Gender")));
                br.setCityID(cur.getInt(cur.getColumnIndex("CityID")));
                br.setCityName(cur.getString(cur.getColumnIndex("CityName")));
                br.setIsFavourite(cur.getString(cur.getColumnIndex("IsFavourite")));
                arrayReg.add(br);


            } while (cur.moveToNext());


        }

        db.close();
        return arrayReg;
    }

    public Bean_Registration selectByID(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String strQuery = "Select mr.RegID,mr.Name,mr.Address,mr.Email,mr.Mobile,mr.Gender,mr.DOB,mr.Hobbies,mr.CityID,mr.IsFavourite,mc.CityName from MAT_Registration mr inner join MST_City mc on mr.CityID=mc.CityID where mr.RegID=" + id;
        Cursor cur = db.rawQuery(strQuery, null);

        Bean_Registration br = new Bean_Registration();

        if (cur.moveToFirst()) {


            br.setRegID(cur.getInt(cur.getColumnIndex("RegID")));
            br.setName(cur.getString(cur.getColumnIndex("Name")));
            br.setGender(cur.getString(cur.getColumnIndex("Gender")));
            br.setCityID(cur.getInt(cur.getColumnIndex("CityID")));
            br.setCityName(cur.getString(cur.getColumnIndex("CityName")));
            br.setDOB(cur.getString(cur.getColumnIndex("DOB")));
            br.setEmail(cur.getString(cur.getColumnIndex("Email")));
            br.setAddress(cur.getString(cur.getColumnIndex("Address")));
            br.setMobile(cur.getString(cur.getColumnIndex("Mobile")));
            br.setHobbies(cur.getString(cur.getColumnIndex("Hobbies")) + "");
            br.setIsFavourite(cur.getString(cur.getColumnIndex("IsFavourite")));

        }

        db.close();
        return br;
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("MAT_Registration", "RegID=" + id, null);
        db.close();
    }


    public void updateIsFavourite(int id, String strFav) {
        SQLiteDatabase db = getWritableDatabase();
        strFav = '"' + strFav + '"';
        String strQuery = "Update MAT_Registration set IsFavourite=" + strFav + " where RegID=" + id;
        db.execSQL(strQuery);
        db.close();

    }

    public ArrayList<Bean_Registration> selectBySearch(int cityID,String strGender) {
        SQLiteDatabase db = getReadableDatabase();
        strGender='"'+strGender+'"';

        String strQuery = "Select mr.RegID,mr.Name,mr.Gender,mr.CityID,mr.IsFavourite,mc.CityName from MAT_Registration mr inner join MST_City mc on mr.CityID=mc.CityID where mr.CityID="+cityID+" and mr.Gender="+strGender;
        Cursor cur = db.rawQuery(strQuery, null);

        ArrayList<Bean_Registration> arrayReg = new ArrayList<Bean_Registration>();

        if (cur.moveToFirst()) {
            do {
                Bean_Registration br = new Bean_Registration();

                br.setRegID(cur.getInt(cur.getColumnIndex("RegID")));
                br.setName(cur.getString(cur.getColumnIndex("Name")));
                br.setGender(cur.getString(cur.getColumnIndex("Gender")));
                br.setCityID(cur.getInt(cur.getColumnIndex("CityID")));
                br.setCityName(cur.getString(cur.getColumnIndex("CityName")));
                br.setIsFavourite(cur.getString(cur.getColumnIndex("IsFavourite")));
                arrayReg.add(br);


            } while (cur.moveToNext());


        }

        db.close();
        return arrayReg;
    }
}