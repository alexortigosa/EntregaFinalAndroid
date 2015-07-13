package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class AcceptLoggin extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_loggin);
        String userString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                userString= null;
            } else {
                userString= extras.getString(intentUserString);
            }
        } else {
            userString= (String) savedInstanceState.getSerializable(intentUserString);
        }

        setLoggin(userString);
    }







    public void goToCalc(View v){

            Intent intent = new Intent(getApplicationContext(), CalcActivity.class);
            startActivity(intent);
    }

    public void goToMem(View v){

        Intent intent = new Intent(getApplicationContext(), MemoryActivity.class);
        startActivity(intent);
    }

    public void goToPlayer(View v){

        Intent intent = new Intent(getApplicationContext(), MediaPlayerActivity.class);
        startActivity(intent);
    }

    public void goToGPS(View v){

        Intent intent = new Intent(getApplicationContext(), PerfilActivity.class);
        startActivity(intent);
    }
}
