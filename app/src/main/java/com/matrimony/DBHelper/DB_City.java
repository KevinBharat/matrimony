package com.matrimony.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.matrimony.Bean.Bean_City;
import com.matrimony.Utility.Constatnt;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

/**
 * Created by admin on 5/2/2018.
 */

public class DB_City extends SQLiteAssetHelper{
    public DB_City(Context context) {
        super(context, Constatnt.dbName, null, null, Constatnt.dbVersion);
    }


    public int getCityID(String strCityName){
        strCityName='"'+strCityName+'"';
        SQLiteDatabase db=getReadableDatabase();
        String strQuery="Select CityID from MST_City where CityName="+strCityName;
        Cursor cur=db.rawQuery(strQuery,null);
        int cityID=0;
        if(cur.moveToFirst()){
            cityID=cur.getInt(cur.getColumnIndex("CityID"));
        }
        return cityID;
    }

    public ArrayList<Bean_City> selectAllCity(){

        SQLiteDatabase db=getReadableDatabase();
        String strQuery="Select * from MST_City";
        Cursor cur=db.rawQuery(strQuery,null);
        ArrayList<Bean_City> arrayCity=new ArrayList<Bean_City>();
        if(cur.moveToFirst()){
            do{
                Bean_City bc=new Bean_City();

                bc.setCityID(cur.getInt(cur.getColumnIndex("CityID")));
                bc.setCityName(cur.getString(cur.getColumnIndex("CityName")));
                arrayCity.add(bc);
            }while (cur.moveToNext());

        }
        return arrayCity;
    }
}
