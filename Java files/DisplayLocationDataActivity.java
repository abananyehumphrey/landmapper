package com.example.landmapper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayLocationDataActivity extends AppCompatActivity {

    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter ;
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
        setContentView(R.layout.activity_display_location_data);
       // getActionBar().setTitle("Registered Coordinates");

        LISTVIEW = (ListView) findViewById(R.id.listView1);
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.listView1) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.operators, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.edit:
                // add stuff here
                return true;
            case R.id.delete:
                // remove stuff here
                return true;
            default:
                return super.onContextItemSelected(item);
        }
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

        listAdapter = new ListAdapter(DisplayLocationDataActivity.this,

                ID_Array,
                NAME_Array,
                PHONE_NUMBER_Array,
                DISTRICT_Array,
                PLOT_NUMBER_Array,
                LATITUDE_Array,
                LONGITUDE_Array



        );

        LISTVIEW.setAdapter(listAdapter);

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
                Intent intent = new Intent(DisplayLocationDataActivity.this,HomeActivity.class);
                startActivity(intent);
                Toast.makeText(DisplayLocationDataActivity.this,"Signout successful",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
