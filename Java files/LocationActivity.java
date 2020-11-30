package com.example.landmapper;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.MANAGE_DOCUMENTS;

public class LocationActivity extends AppCompatActivity implements LocationListener {
    private static final int PERMISSION_CODE = 101;

    Button getLocationl,getLocationB,regButton;
    String[] permissions_all = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};
    LocationManager locationManager;
    Location loc;
    boolean isGpslocation;
    boolean isNetworklocation;
    ProgressDialog progressDialog;
   // String[] districtNameList = {"Kumi", "Jinja", "Kampala", "Gulu", "Mbale", "Arua"};
    EditText fullname,number,plotnumber,district,locationLat, locationLong,locationLatB, locationLongB;
    String latitude,longitude,latitudeB,longitudeB,name,mobilenumber,pnumber,mdistrict,SQLiteDataBaseQueryHolder;
   // SQLiteDatabase sqLitedb;
    Boolean EditTextEmptyHold;
    SQLiteDatabase sqLiteDatabaseObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
       // viewBtn = (Button)findViewById(R.id.viewBtn);
        regButton = (Button)findViewById(R.id.regBtn);
        fullname = (EditText)findViewById(R.id.fullName);
        number = (EditText)findViewById(R.id.mobileNumber);
        plotnumber = (EditText)findViewById(R.id.plotNum);
        district = (EditText)findViewById(R.id.district);
        progressDialog=new ProgressDialog(LocationActivity.this);
        progressDialog.setMessage("Getting location...");
        locationLat = (EditText)findViewById(R.id.locationLatitude);
        locationLong =(EditText)findViewById(R.id.locationLongitude);
        locationLatB = (EditText)findViewById(R.id.locationLatitudeB);
        locationLongB = (EditText)findViewById(R.id.locationLongitudeB);
       // locationText= findViewById(R.id.location);
        getLocationB = findViewById(R.id.getLocationB);

        getLocationl=findViewById(R.id.getLocation);
        getLocationl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                getLocation();
            }


        });

        getLocationB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                getLocation();
                updateUiB(loc);
            }

        });


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDataBaseBuild();

                SQLiteTableBuild();

                CheckEditTextStatus();

                InsertDataIntoSQLiteDatabase();

                EmptyEditTextAfterDataInsert();
            }

            private void SQLiteDataBaseBuild() {
                sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
            }

            private void SQLiteTableBuild() {
                sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS "+SQLiteHelper.TABLE_NAME+"("+SQLiteHelper.Table_Column_ID+" PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        +SQLiteHelper.Table_Column_1_Name+" VARCHAR, "+SQLiteHelper.Table_Column_2_PhoneNumber+
                        " VARCHAR,"+SQLiteHelper.Table_Column_3_District+" VARCHAR,"
                        +SQLiteHelper.Table_Column_4_PlotNumber+"VARCHAR, "+SQLiteHelper.Table_Column_5_Latitude+"VARCHAR, "+SQLiteHelper.Table_Column_6_Longitude+" VARCHAR);");

            }

            private void CheckEditTextStatus() {
                name = fullname.getText().toString() ;
                mobilenumber = number.getText().toString();
                mdistrict = district.getText().toString();
                pnumber = plotnumber.getText().toString();
                latitude = locationLat.getText().toString();
                longitude = locationLong.getText().toString();
                latitudeB = locationLatB.getText().toString();
                longitudeB = locationLongB.getText().toString();


                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(mobilenumber) || TextUtils.isEmpty(mdistrict) || TextUtils.isEmpty(pnumber) || TextUtils.isEmpty(latitude) || TextUtils.isEmpty(longitude) || TextUtils.isEmpty(latitudeB)){

                    EditTextEmptyHold = false ;

                }
                else {

                    EditTextEmptyHold = true ;
                }

            }

            private void InsertDataIntoSQLiteDatabase() {
                if(EditTextEmptyHold == true)
                {

                    SQLiteDataBaseQueryHolder = "INSERT INTO "+SQLiteHelper.TABLE_NAME+" (name,phone_number,mdistrict,pnumber,latitude,longitude,latitudeb) VALUES('"+name+"', '"+mobilenumber+"','"+mdistrict+"','"+pnumber+"','"+latitude+"','"+longitude+"','"+latitudeB+"');";

                    sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

                    Toast.makeText(LocationActivity.this,"Data Inserted Successfully", Toast.LENGTH_LONG).show();
                    //Toast.makeText(LocationActivity.this,"Location :"+longitude+","+latitude,Toast.LENGTH_LONG).show();

                }
                else {

                    Toast.makeText(LocationActivity.this,"Please fill all the required fields.", Toast.LENGTH_LONG).show();

                }

            }

            private void EmptyEditTextAfterDataInsert() {


                    fullname.getText().clear();

                    number.getText().clear();

                    district.getText().clear();

                    plotnumber.getText().clear();

                    locationLat.getText().clear();

                    locationLong.getText().clear();

                    locationLatB.getText().clear();

                    locationLongB.getText().clear();


            }
        });

    }
    /*
    //extracting values from the text fields
    public void registerInfo(View view){

     // gender = selectedRadioButton.getText().toString();
        latitude = locationLat.getText().toString();
        longitude = locationLong.getText().toString();
        name = fullname.getText().toString();
        mobilenumber = number.getText().toString();
        pnumber = plotnumber.getText().toString();
        mdistrict = district.getText().toString();
        */

    private void getLocation() {
        if (Build.VERSION.SDK_INT>=23){
            if (checkPermission()){
                getDeviceLocation();
            }
            else {
                requestPermission();
            }
        }
        else {
            getDeviceLocation();
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(LocationActivity.this,permissions_all,PERMISSION_CODE);
    }

    private boolean checkPermission() {
        for (int i=0;i<permissions_all.length;i++){
            int result = ContextCompat.checkSelfPermission(LocationActivity.this,permissions_all[i]);
            if (result == PackageManager.PERMISSION_GRANTED){
                continue;
            }
            else
                {
                return false;
            }
        }
        return true;
    }

    private void getDeviceLocation() {
        locationManager=(LocationManager)getSystemService(Service.LOCATION_SERVICE);
        isGpslocation=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworklocation=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (!isGpslocation && !isNetworklocation){
            showSettingForLocation();
            getLastlocation();
        }
        else {
            getFinalLocation();
        }
    }

    private void getLastlocation() {
        if (locationManager!=null)
        try {
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria,false);
            Location location= locationManager.getLastKnownLocation(provider);

        }catch (SecurityException e ){
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_CODE:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getFinalLocation();
                }
                else {
                    Toast.makeText(this,"Permission Failed",Toast.LENGTH_LONG).show();
                }
        }
    }

    private void getFinalLocation() {
        try {
            if (isGpslocation){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000*60*1,10,LocationActivity.this);
                if (locationManager!=null){
                    loc=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (loc!=null){
                        updateUi(loc);
                    }

                }
            }else if (isNetworklocation){
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000*60*1,10,LocationActivity.this);
                if (locationManager!=null){
                    loc=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (loc!=null){
                        updateUi(loc);
                    }

                }

            }
            else {
                Toast.makeText(this,"Can't Get Location", Toast.LENGTH_LONG).show();

            }

        }catch (SecurityException e){
            Toast.makeText(this,"Can't Get Location", Toast.LENGTH_LONG).show();

        }
    }

    private void updateUi(Location loc) {
        if (loc.getLatitude()==0 && loc.getLongitude()==0){
            getDeviceLocation();

        }
        else
            {
            progressDialog.dismiss();
         //   locationText.setText("Location :"+loc.getLatitude()+" , "+loc.getLongitude());
            locationLat.setText(""+loc.getLatitude());
            locationLong.setText(""+loc.getLongitude());
           // locationLatB.setText(""+loc.getLatitude());
           // locationLongB.setText(""+loc.getLongitude());
        }

    }
    private void updateUiB(Location loc) {
        if (loc.getLatitude()==0 && loc.getLongitude()==0){
            getDeviceLocation();

        }
        else
        {
            progressDialog.dismiss();
            //   locationText.setText("Location :"+loc.getLatitude()+" , "+loc.getLongitude());
           // locationLat.setText(""+loc.getLatitude());
           // locationLong.setText(""+loc.getLongitude());
            locationLatB.setText(""+loc.getLatitude());
             locationLongB.setText(""+loc.getLongitude());
        }

    }

    private void showSettingForLocation() {
        AlertDialog.Builder al= new AlertDialog.Builder(LocationActivity.this);
        al.setTitle("Location Not Enabled!");
        al.setMessage("Enable Location ?");
        al.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent= new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        });
        al.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        al.show();

    }

    @Override
    public void onLocationChanged(Location location) {
        updateUi(location);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

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
                Intent intent = new Intent(LocationActivity.this,HomeActivity.class);
                startActivity(intent);
                Toast.makeText(LocationActivity.this,"Signout successful",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}


