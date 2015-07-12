package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class BaseActivity extends ActionBarActivity {

    protected String dbName="DBUsuarios";
    protected String prefName="myCustomApp";
    protected String playBool="playerConnected";
    protected SharedPreferences settings;
    protected String userLoggin ="user_loggin";
    protected String Loggin = "bolLoggin";
    protected String intentUserString = "_USER_STRING_";

    protected void setLoggin(String userString){
        //Instanciamos el SharedPreferences
        settings = getSharedPreferences(prefName, 0);
        //Obtenemos el editor
        SharedPreferences.Editor editor = settings.edit();
        //Editamos
        editor.putString(this.userLoggin, userString);
        editor.putBoolean(this.Loggin, true);

        //Guardamos los cambios
        editor.apply();
    }

    protected boolean isLogged(){
        //Instanciamos el SharedPreferences
        settings = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        //Consultamos
        return settings.getBoolean(this.Loggin,false);
    }
    protected void logout(){
        //Instanciamos el SharedPreferences
        settings = getSharedPreferences(prefName, 0);
        //Obtenemos el editor
        SharedPreferences.Editor editor = settings.edit();
        //Editamos
        editor.putString(this.userLoggin, "");
        editor.putBoolean(this.Loggin, false);

        //Guardamos los cambios
        editor.apply();

    }

    protected void goToLog(){
        Intent intent = new Intent(getApplicationContext(), LoggActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_exit:
                logout();
                goToLog();
                break;
            default:
                return true;

        }
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
