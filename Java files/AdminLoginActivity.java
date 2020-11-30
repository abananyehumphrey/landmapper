package com.example.landmapper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AdminLoginActivity extends AppCompatActivity {
    private TextInputLayout textInputLayoutUsername;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextUsername;
    private TextInputEditText textInputEditTextPassword;
    private AppCompatTextView appCompatTextViewOtherLogin;

    private AppCompatButton appCompatButtonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        textInputLayoutUsername = (TextInputLayout)findViewById(R.id.textInputLayoutUsername);
        textInputLayoutPassword = (TextInputLayout)findViewById(R.id.textInputLayoutPassword);
        textInputEditTextUsername = (TextInputEditText)findViewById(R.id.textInputEditTextUsername);
        appCompatTextViewOtherLogin = (AppCompatTextView)findViewById(R.id.textViewLinkOtherLogin);
        textInputEditTextPassword = (TextInputEditText)findViewById(R.id.textInputEditTextPassword);
        appCompatButtonLogin = (AppCompatButton)findViewById(R.id.appCompatButtonLogin);
        appCompatTextViewOtherLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIntent = new Intent(AdminLoginActivity.this,HomeActivity.class);
                startActivity(logIntent);
            }
        });

        appCompatButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }


        });
    }
    public void login(){
        String user = textInputEditTextUsername.getText().toString().trim();
        String pass = textInputEditTextPassword.getText().toString().trim();
        if (user.equals("Humphrey1") && pass.equals("1234")){
            //open the location data activity
            viewDetailsofData();

        }
        else if (user.equals("Arnold1") && pass.equals("Admin@1")){
            //do this
            addLocationData();
        }
        else if (user.equals("Grace1") && pass.equals("pass@2020")){
            //do this
            addLocationData();
        }
        else if (user.equals("Nakato12") && pass.equals("surveyor1")){
            //do this
            addLocationData();
        }
        else
            {
            Toast.makeText(AdminLoginActivity.this,"Invalid login details",Toast.LENGTH_LONG).show();
        }
    }

    private void addLocationData() {
        Intent addLocationIntent = new Intent(AdminLoginActivity.this,LocationActivity.class);
        startActivity(addLocationIntent);
    }

    private void viewDetailsofData() {
        Intent detailsIntent = new Intent(AdminLoginActivity.this,DisplayLocationDataActivity.class);
        startActivity(detailsIntent);
    }
}
