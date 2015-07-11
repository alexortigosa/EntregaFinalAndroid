package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class AcceptLoggin extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_loggin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accept_loggin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

        Intent intent = new Intent(getApplicationContext(), GPSActivity.class);
        startActivity(intent);
    }
}
