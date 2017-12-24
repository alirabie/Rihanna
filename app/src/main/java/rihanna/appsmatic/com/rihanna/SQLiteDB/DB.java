package rihanna.appsmatic.com.rihanna.SQLiteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import rihanna.appsmatic.com.rihanna.SQLiteDB.DB_Models.ExpertTime;

/**
 * Created by Eng Ali on 12/23/2017.
 */
public class DB extends SQLiteOpenHelper {

    final static String DBNAME = "rihanna.db";
    final static int Version = 1;

    public DB(Context context) {
        super(context, DBNAME, null, Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table ExpertDate (day INTEGER,from_time TEXT,to_tome TEXT)");
        //db.execSQL("Create table IF NOT EXISTS Status (id INTEGER primary key,p_Id INTEGER,s_date TEXT,s_time TEXT,blood_pressure TEXT,diabetes_rate TEXT,weight TEXT,temp TEXT,doctor_notes TEXT,required_analyzes TEXT,required_xray TEXT,drug TEXT,next_visit TEXT,FOREIGN KEY(p_Id) REFERENCES Patients(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table ExpertDate ");
        onCreate(db);

    }


    //Insert Expert Time
    public void insertExpertTime(ExpertTime expertTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("day",expertTime.getDay());
        contentValues.put("from_time",expertTime.getFrom());
        contentValues.put("to_tome", expertTime.getTo());
        db.insert("ExpertDate", null, contentValues);
    }





    //get day times by day num
    public List<ExpertTime>getDayTimes(int day){
        List<ExpertTime>expertTimes=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from ExpertDate where day="+day, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            ExpertTime expertTime=new ExpertTime();
            expertTime.setFrom(cursor.getString(cursor.getColumnIndex("from_time")));
            expertTime.setTo(cursor.getString(cursor.getColumnIndex("to_tome")));
            expertTimes.add(expertTime);
            cursor.moveToNext();
        }
        return expertTimes;
    }


}
