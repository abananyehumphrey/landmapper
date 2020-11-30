package com.example.landmapper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    static String DATABASE_NAME="AndroidJSonDataBase";

    public static final String TABLE_NAME="AndroidJSonTable";

    public static final String Table_Column_ID="id";

    public static final String Table_Column_1_Name="name";

    public static final String Table_Column_2_PhoneNumber="phone_number";

    public static final String Table_Column_3_District="mdistrict";

    public static final String Table_Column_4_PlotNumber="pnumber";

    public static final String Table_Column_5_Latitude="latitude";

    public static final String Table_Column_6_Longitude="longitude";




    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_PhoneNumber+" " +
                "VARCHAR,"+Table_Column_3_District+" VARCHAR,"+Table_Column_4_PlotNumber+" VARCHAR, "+Table_Column_5_Latitude+" VARCHAR, " +
                ""+Table_Column_6_Longitude+" VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
}
