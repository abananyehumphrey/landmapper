package com.example.landmapper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LandOnSaleActivity extends AppCompatActivity {


    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter2 listAdapter2 ;
    ListView LISTVIEW;

    ArrayList<String> ID_Array;
    ArrayList<String> NAME_Array;
    ArrayList<String> PHONE_NUMBER_Array;
    ArrayList<String> DISTRICT_Array;
    ArrayList<String> PLOT_NUMBER_Array;
    ArrayList<String> LATITUDE_Array;
    ArrayList<String> LONGITUDE_Array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_on_sale);

        LISTVIEW = (ListView) findViewById(R.id.listViewLandOnSale);
        registerForContextMenu(LISTVIEW );

        ID_Array = new ArrayList<String>();

        NAME_Array = new ArrayList<String>();

        PHONE_NUMBER_Array = new ArrayList<String>();

        DISTRICT_Array = new ArrayList<String>();

        PLOT_NUMBER_Array = new ArrayList<String>();

        LATITUDE_Array = new ArrayList<String>();

        LONGITUDE_Array = new ArrayList<String>();

        sqLiteHelper = new SQLiteHelper(this);
    }
    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;
        super.onResume();
    }

    private void ShowSQLiteDBdata() {
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME+"", null);

        ID_Array.clear();
        NAME_Array.clear();
        PHONE_NUMBER_Array.clear();
        DISTRICT_Array.clear();
        PLOT_NUMBER_Array.clear();
        LATITUDE_Array.clear();
        LONGITUDE_Array.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));

                NAME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name)));

                PHONE_NUMBER_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_PhoneNumber)));

                DISTRICT_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_District)));

                PLOT_NUMBER_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_4_PlotNumber)));

                LATITUDE_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_5_Latitude)));

                LONGITUDE_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_6_Longitude)));

            } while (cursor.moveToNext());
        }

        listAdapter2 = new ListAdapter2(LandOnSaleActivity.this,

                ID_Array,
                NAME_Array,
                PHONE_NUMBER_Array,
                DISTRICT_Array,
                PLOT_NUMBER_Array,
                LATITUDE_Array,
                LONGITUDE_Array

        );

        LISTVIEW.setAdapter(listAdapter2);

        cursor.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                Intent intent = new Intent(LandOnSaleActivity.this,HomeActivity.class);
                startActivity(intent);
                Toast.makeText(LandOnSaleActivity.this,"Signout successful",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
