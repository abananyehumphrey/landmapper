package com.example.landmapper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private AppCompatTextView textViewBuyer;
    private AppCompatTextView textViewSurveyor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textViewBuyer = (AppCompatTextView)findViewById(R.id.textViewBuyer);
        textViewBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(HomeActivity.this,"Not yet ready",Toast.LENGTH_LONG).show();
                Intent buyerIntent = new Intent(HomeActivity.this,UserLoginActivity.class);
                startActivity(buyerIntent);
            }
        });
        textViewSurveyor = (AppCompatTextView)findViewById(R.id.textViewSurveyor);
        textViewSurveyor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent surveyorIntent = new Intent(HomeActivity.this,AdminLoginActivity.class);
                startActivity(surveyorIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HomeActivity.this,HomeActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
